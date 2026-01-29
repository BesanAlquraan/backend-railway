package com.project.backend.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class BudgetResponseDTO {

    private Long id;
    private Long categoryId;
    private BigDecimal limitAmount;
    private BigDecimal spentAmount;
    private Boolean isMonthly;
    private Integer healthScore;

    // ===================== Constructors =====================
    public BudgetResponseDTO() {
    }

    public BudgetResponseDTO(Long id, Long categoryId, BigDecimal limitAmount,
                             BigDecimal spentAmount, Boolean isMonthly, Integer healthScore) {
        this.id = id;
        this.categoryId = categoryId;
        this.limitAmount = limitAmount;
        this.spentAmount = spentAmount;
        this.isMonthly = isMonthly;
        this.healthScore = healthScore;
    }

    // ===================== Getters & Setters =====================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(BigDecimal spentAmount) {
        this.spentAmount = spentAmount;
    }

    public Boolean getIsMonthly() {
        return isMonthly;
    }

    public void setIsMonthly(Boolean isMonthly) {
        this.isMonthly = isMonthly;
    }

    public Integer getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(Integer healthScore) {
        this.healthScore = healthScore;
    }

    // ===================== Utility Methods =====================
    @Override
    public String toString() {
        return "BudgetResponseDTO{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", limitAmount=" + limitAmount +
                ", spentAmount=" + spentAmount +
                ", isMonthly=" + isMonthly +
                ", healthScore=" + healthScore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BudgetResponseDTO)) return false;
        BudgetResponseDTO that = (BudgetResponseDTO) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(categoryId, that.categoryId) &&
               Objects.equals(limitAmount, that.limitAmount) &&
               Objects.equals(spentAmount, that.spentAmount) &&
               Objects.equals(isMonthly, that.isMonthly) &&
               Objects.equals(healthScore, that.healthScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, limitAmount, spentAmount, isMonthly, healthScore);
    }
}
