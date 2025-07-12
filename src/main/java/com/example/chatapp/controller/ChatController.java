package com.example.chatapp.controller;
import com.example.chatapp.entity.Message;
import com.example.chatapp.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private KafkaService kafkaService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message Message) {
        logger.info("Received message from client: {}", Message);
        Message.setType(com.example.chatapp.entity.Message.MessageType.CHAT);
        kafkaService.sendMessage(Message);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload Message Message, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("User joining: {}", Message.getSender());

        // Add username in WebSocket session
        headerAccessor.getSessionAttributes().put("username", Message.getSender());
        headerAccessor.getSessionAttributes().put("room", Message.getRoom());
        Message.setType(com.example.chatapp.entity.Message.MessageType.JOIN);
        Message.setContent(Message.getSender() + " joined the chat!");
        kafkaService.sendMessage(Message);
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}