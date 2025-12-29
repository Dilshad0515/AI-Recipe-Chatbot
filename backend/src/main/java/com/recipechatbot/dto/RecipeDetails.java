package com.recipechatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecipeDetails {
    private List<String> ingredients;
    private List<String> steps;
}
