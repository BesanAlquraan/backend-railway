package com.project.backend.dto;

import java.time.LocalDateTime;

public class TransactionResponseDTO {

    private Long id;
    private String title;
    private double amount;
    private LocalDateTime date;
    private String type; // "EXPENSE" أو "INCOME"
    private CategoryDTO expenseCategory; // null إذا INCOME
    private CategoryDTO incomeCategory;  // null إذا EXPENSE

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public CategoryDTO getExpenseCategory() { return expenseCategory; }
    public void setExpenseCategory(CategoryDTO expenseCategory) { this.expenseCategory = expenseCategory; }

    public CategoryDTO getIncomeCategory() { return incomeCategory; }
    public void setIncomeCategory(CategoryDTO incomeCategory) { this.incomeCategory = incomeCategory; }

    // ===== Nested DTO for categories =====
    public static class CategoryDTO {
        private Long id;
        private String name;

        public CategoryDTO() {}
        public CategoryDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
