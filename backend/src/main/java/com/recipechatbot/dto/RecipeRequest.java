package com.recipechatbot.dto;

import com.recipechatbot.model.ChatStage;
import lombok.Data;

@Data
public class RecipeRequest {
    private String message;
    private ChatStage stage;
}
