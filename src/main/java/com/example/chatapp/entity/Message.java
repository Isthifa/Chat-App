package com.example.chatapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Message {
    private String id;
    private String sender;
    private String content;
    private String room;
    private MessageType type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    public Message() {
        this.timestamp = LocalDateTime.now();
    }

    public Message(String sender, String content, String room, MessageType type) {
        this();
        this.sender = sender;
        this.content = content;
        this.room = room;
        this.type = type;
        this.id = generateId();
    }

    private String generateId() {
        return System.currentTimeMillis() + "_" + sender + "_" + Math.random();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id='" + id + '\'' +
                ", sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", room='" + room + '\'' +
                ", type=" + type +
                ", timestamp=" + timestamp +
                '}';
    }
}