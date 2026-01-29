package com.project.backend.dto;

import java.time.LocalDateTime;

public class TransactionRequestDTO {

    private String title;
    private double amount;
    private LocalDateTime date;
    private String type; // "EXPENSE" أو "INCOME"
    private Long categoryId; // ID لفئة الدخل أو المصاريف

    // ===== Getters & Setters =====
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}
