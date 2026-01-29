package com.project.backend.dto;

public class PasswordResetResponseDTO {
    private String message;

    public PasswordResetResponseDTO() {}

    public PasswordResetResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
