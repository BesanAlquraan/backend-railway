package com.project.backend.dto;

public class GeminiResponseDTO {

    private String reply;

    // Default constructor
    public GeminiResponseDTO() {
    }

    // Constructor يستقبل reply
    public GeminiResponseDTO(String reply) {
        this.reply = reply;
    }

    // Getter
    public String getReply() {
        return reply;
    }

    // Setter
    public void setReply(String reply) {
        this.reply = reply;
    }
}
