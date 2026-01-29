package com.project.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "monthly_budget_health")
public class MonthlyBudgetHealth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "month_year", nullable = false)
    private LocalDate monthYear;

    @Column(name = "health_score", nullable = false)
    private Integer healthScore;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ===================== Constructors =====================
    public MonthlyBudgetHealth() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public MonthlyBudgetHealth(Long userId, LocalDate monthYear, Integer healthScore) {
        this.userId = userId;
        this.monthYear = monthYear;
        this.healthScore = healthScore;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public MonthlyBudgetHealth(Long id, Long userId, LocalDate monthYear, Integer healthScore,
                               LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.monthYear = monthYear;
        this.healthScore = healthScore;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ===================== Getters & Setters =====================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(LocalDate monthYear) {
        this.monthYear = monthYear;
    }

    public Integer getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(Integer healthScore) {
        this.healthScore = healthScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ===================== Utility Methods =====================
    @Override
    public String toString() {
        return "MonthlyBudgetHealth{" +
                "id=" + id +
                ", userId=" + userId +
                ", monthYear=" + monthYear +
                ", healthScore=" + healthScore +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonthlyBudgetHealth)) return false;
        MonthlyBudgetHealth that = (MonthlyBudgetHealth) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(monthYear, that.monthYear) &&
                Objects.equals(healthScore, that.healthScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, monthYear, healthScore);
    }
}
