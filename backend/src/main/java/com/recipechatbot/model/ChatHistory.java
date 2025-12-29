package com.recipechatbot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String userMessage;

    @Column(length = 3000)
    private String botReply;
}
