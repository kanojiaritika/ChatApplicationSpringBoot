package com.chat.chatApp.controller;

// Going to handle all the incoming messages from the frontend and is going to broadcast it to all the other clients

import com.chat.chatApp.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    // When any msg is received on /app/sendMessage it will be received by all other clients on /topic/messages
    @MessageMapping("/sendMessage") // maps web socket messages to destination
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        return message;
    }

    @GetMapping("/chat") // Whoever is on /chat URL will get this "chat: thymeleaf template
    public String chat() {
        return "chat";
    }
}
