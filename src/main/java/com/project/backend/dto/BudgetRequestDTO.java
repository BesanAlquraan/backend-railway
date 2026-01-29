package com.project.backend.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class BudgetRequestDTO {

    private Long categoryId;
    private BigDecimal limitAmount;
    private Boolean isMonthly;

    // ===================== Constructors =====================
    public BudgetRequestDTO() {
    }

    public BudgetRequestDTO(Long categoryId, BigDecimal limitAmount, Boolean isMonthly) {
        this.categoryId = categoryId;
        this.limitAmount = limitAmount;
        this.isMonthly = isMonthly;
    }

    // ===================== Getters & Setters =====================
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Boolean getIsMonthly() {
        return isMonthly;
    }

    public void setIsMonthly(Boolean isMonthly) {
        this.isMonthly = isMonthly;
    }

    // ===================== Utility Methods =====================
    @Override
    public String toString() {
        return "BudgetRequestDTO{" +
                "categoryId=" + categoryId +
                ", limitAmount=" + limitAmount +
                ", isMonthly=" + isMonthly +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BudgetRequestDTO)) return false;
        BudgetRequestDTO that = (BudgetRequestDTO) o;
        return Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(limitAmount, that.limitAmount) &&
                Objects.equals(isMonthly, that.isMonthly);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, limitAmount, isMonthly);
    }
}
