package com.recipechatbot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipechatbot.ai.GroqClient;
import com.recipechatbot.dto.RecipeDetails;
import com.recipechatbot.dto.RecipeResponse;
import com.recipechatbot.model.ChatHistory;
import com.recipechatbot.model.ChatStage;
import com.recipechatbot.repository.ChatHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecipeService {

    private final GroqClient groqClient;
    private final ChatHistoryRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // ✅ STATE (single user session)
    private String selectedDish;
    private List<String> lastSuggestedDishes;

    public RecipeService(GroqClient groqClient,
                         ChatHistoryRepository repository) {
        this.groqClient = groqClient;
        this.repository = repository;
    }

    public RecipeResponse handleUserMessage(String message) {

        String normalized = message.trim().toLowerCase();

        // ✅ User confirmed recipe
        if (normalized.equals("yes") && selectedDish != null) {
            return generateRecipeForSelectedDish();
        }

        // ✅ User selected dish (ONLY if matches last suggestions)
        if (isDishSelection(message)) {
            selectedDish = message.trim();
            return askConfirmation(selectedDish);
        }

        // ✅ Otherwise treat input as ingredients
        return suggestDishes(message);
    }

    // ------------------ SUGGEST DISHES ------------------

    private RecipeResponse suggestDishes(String ingredients) {

        String prompt = """
        You are a cooking assistant.

        STRICT RULES:
        - Respond ONLY in valid JSON
        - Suggest exactly 3 dishes
        - Do NOT ask for confirmation
        - Dish names must be unique

        JSON format:
        {
          "stage": "SUGGESTIONS",
          "message": "Here are dishes you can make",
          "dishes": ["Dish 1", "Dish 2", "Dish 3"]
        }

        Ingredients:
        "%s"
        """.formatted(ingredients);

        try {
            String aiReply = groqClient.generate(prompt);

            Map<String, Object> parsed =
                    objectMapper.readValue(extractJson(aiReply), Map.class);

            // ✅ Remove duplicates if AI sends any
            lastSuggestedDishes = ((List<String>) parsed.get("dishes"))
                    .stream()
                    .distinct()
                    .toList();

            saveChat(ingredients, aiReply);

            return new RecipeResponse(
                    ChatStage.SUGGESTIONS,
                    parsed.get("message").toString(),
                    lastSuggestedDishes,
                    null
            );

        } catch (Exception e) {
            return errorResponse();
        }
    }

    // ------------------ CONFIRMATION ------------------

    private RecipeResponse askConfirmation(String dish) {

        String reply = "Do you want the full recipe for " + dish + "?";

        saveChat(dish, reply);

        return new RecipeResponse(
                ChatStage.CONFIRMATION,
                reply,
                null,
                null
        );
    }

    // ------------------ GENERATE RECIPE ------------------

    private RecipeResponse generateRecipeForSelectedDish() {

        String prompt = """
        You are a cooking assistant.

        Provide FULL recipe ONLY for:
        "%s"

        Respond ONLY in valid JSON.

        JSON format:
        {
          "stage": "RECIPE",
          "message": "Here is the recipe for %s",
          "recipe": {
            "ingredients": ["Item 1", "Item 2"],
            "steps": ["Step 1", "Step 2"]
          }
        }
        """.formatted(selectedDish, selectedDish);

        try {
            String aiReply = groqClient.generate(prompt);

            Map<String, Object> parsed =
                    objectMapper.readValue(extractJson(aiReply), Map.class);

            Map<String, Object> r =
                    (Map<String, Object>) parsed.get("recipe");

            RecipeDetails recipe = new RecipeDetails(
                    (List<String>) r.get("ingredients"),
                    (List<String>) r.get("steps")
            );

            saveChat("yes", aiReply);

            // ✅ CLEAR STATE AFTER USE
            selectedDish = null;
            lastSuggestedDishes = null;

            return new RecipeResponse(
                    ChatStage.RECIPE,
                    parsed.get("message").toString(),
                    null,
                    recipe
            );

        } catch (Exception e) {
            return errorResponse();
        }
    }

    // ------------------ HELPERS ------------------

    // ✅ Accept dish ONLY if it matches last suggestions
    private boolean isDishSelection(String message) {
        if (lastSuggestedDishes == null) return false;

        return lastSuggestedDishes.stream()
                .anyMatch(d -> d.equalsIgnoreCase(message.trim()));
    }

    // ✅ Extract pure JSON from AI response
    private String extractJson(String aiReply) {
        return aiReply.substring(
                aiReply.indexOf("{"),
                aiReply.lastIndexOf("}") + 1
        );
    }

    private RecipeResponse errorResponse() {
        return new RecipeResponse(
                ChatStage.SUGGESTIONS,
                "Sorry, something went wrong. Please try again.",
                null,
                null
        );
    }

    private void saveChat(String userMessage, String botReply) {
        ChatHistory chat = new ChatHistory();
        chat.setUserMessage(userMessage);
        chat.setBotReply(botReply);
        repository.save(chat);
    }
}
