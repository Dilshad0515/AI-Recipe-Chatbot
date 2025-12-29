package com.recipechatbot.controller;

import com.recipechatbot.dto.RecipeRequest;
import com.recipechatbot.dto.RecipeResponse;
import com.recipechatbot.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public RecipeResponse chat(@RequestBody RecipeRequest request) {
        return recipeService.handleUserMessage(request.getMessage());
    }



    @GetMapping("/test")
    public String test() {
        return "Backend is working!";
    }
}
