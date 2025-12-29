package com.recipechatbot.repository;

import com.recipechatbot.model.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository
        extends JpaRepository<ChatHistory, Long> {
}
