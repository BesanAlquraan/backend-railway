package com.project.backend.dto;

public class ChatResponse {

    private String reply;

    // Constructor
    public ChatResponse(String reply) {
        this.reply = reply;
    }

    // Getter & Setter
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
