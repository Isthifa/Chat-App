package com.example.chatapp.config;

import com.example.chatapp.entity.Message;
import com.example.chatapp.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private KafkaService kafkaService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received new WebSocket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String room = (String) headerAccessor.getSessionAttributes().get("room");

        if (username != null && room != null) {
            logger.info("User disconnected: {}", username);

            Message chatMessage = new Message();
            chatMessage.setSender(username);
            chatMessage.setRoom(room);
            chatMessage.setType(Message.MessageType.LEAVE);
            chatMessage.setContent(username + " left the chat!");

            kafkaService.sendMessage(chatMessage);
        }
    }
}