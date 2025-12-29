package com.recipechatbot.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class GroqClient {

    @Value("${groq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generate(String prompt) {

        String url = "https://api.groq.com/openai/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "model", "llama-3.1-8b-instant",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

        Map response = restTemplate.postForObject(url, entity, Map.class);

        List choices = (List) response.get("choices");
        Map message = (Map) ((Map) choices.get(0)).get("message");

        return message.get("content").toString();
    }
}
