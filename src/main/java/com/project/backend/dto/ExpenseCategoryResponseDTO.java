package com.project.backend.dto;

import com.project.backend.model.ExpenseType;
import java.time.LocalDateTime;

public class ExpenseCategoryResponseDTO {

    private Long id;
    private String name;
    private ExpenseType type;
    private String color;
    private String icon;
    private LocalDateTime createdAt;

    // ======= Constructor كامل =======
    public ExpenseCategoryResponseDTO(Long id, String name, ExpenseType type, String color, String icon, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.icon = icon;
        this.createdAt = createdAt;
    }

    // ======= Getters =======
    public Long getId() { return id; }
    public String getName() { return name; }
    public ExpenseType getType() { return type; }
    public String getColor() { return color; }
    public String getIcon() { return icon; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // ======= Builder يدوي (اختياري) =======
    public static class Builder {
        private Long id;
        private String name;
        private ExpenseType type;
        private String color;
        private String icon;
        private LocalDateTime createdAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder type(ExpenseType type) { this.type = type; return this; }
        public Builder color(String color) { this.color = color; return this; }
        public Builder icon(String icon) { this.icon = icon; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public ExpenseCategoryResponseDTO build() {
            return new ExpenseCategoryResponseDTO(id, name, type, color, icon, createdAt);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
