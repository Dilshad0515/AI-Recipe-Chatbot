package com.recipechatbot.dto;

import com.recipechatbot.model.ChatStage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecipeResponse {

    private ChatStage stage;
    private String message;
    private List<String> dishes;
    private RecipeDetails recipe;
}
