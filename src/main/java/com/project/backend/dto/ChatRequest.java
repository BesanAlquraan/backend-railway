package com.project.backend.dto;

public class ChatRequest {

    private String message;

    public ChatRequest() {} // Default constructor مهم للـ JSON mapping

    public ChatRequest(String message) { // Optional constructor
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter
    public void setMessage(String message) {
        this.message = message;
    }
}
