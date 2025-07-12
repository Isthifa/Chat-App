package com.example.chatapp.service;

import com.example.chatapp.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);
    private static final String TOPIC = "chat-messages";

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendMessage(Message message) {
        logger.info("Sending message to Kafka: {}", message);
        kafkaTemplate.send(TOPIC, message.getRoom(), message);
    }

    @KafkaListener(topics = TOPIC, groupId = "chat-group")
    public void receiveMessage(Message
 message) {
        logger.info("Received message from Kafka: {}", message);

        // Send message to WebSocket subscribers
        messagingTemplate.convertAndSend("/topic/room/" + message.getRoom(), message);
    }
}

