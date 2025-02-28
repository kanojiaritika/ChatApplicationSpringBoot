package com.chat.chatApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Tells Spring that this app will use web sockets for real time communication
// If there is a msg in which chatroom the msg should be Message Broker decides that
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:5173") // Allow request from only this website. This prevents the entering of unknown web URLs sending requests
                .withSockJS(); // provides a fallback option for clients that do not support native WebSockets by using alternative transport mechanisms like AJAX long polling or HTTP streaming.
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // set message broker
        registry.enableSimpleBroker("/topic"); // set message broker, handles message for "topic"

        // expect message with /app
        registry.setApplicationDestinationPrefixes("/app"); // Tells server if you get any message with this prefix process it
    }
}
