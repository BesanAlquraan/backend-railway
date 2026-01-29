package com.project.backend.dto;

public class GeminiRequestDTO {

    private String message;

    // Default constructor
    public GeminiRequestDTO() {
    }

    // Constructor with message
    public GeminiRequestDTO(String message) {
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
