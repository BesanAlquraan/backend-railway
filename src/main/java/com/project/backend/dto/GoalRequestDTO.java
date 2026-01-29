package com.project.backend.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GoalRequestDTO {
    private String title;
    private String description;
    private String category;
    private String status;
    private String priority;
    private LocalDateTime dueDate;
    private String recurrence;
    private List<String> tags = new ArrayList<>();
    private List<String> comments = new ArrayList<>();
    private List<String> history = new ArrayList<>();
    private String icon;

    // ================= Getters & Setters =================

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getRecurrence() {
        return recurrence;
    }
    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags) {
        this.tags = tags != null ? tags : new ArrayList<>();
    }

    public List<String> getComments() {
        return comments;
    }
    public void setComments(List<String> comments) {
        this.comments = comments != null ? comments : new ArrayList<>();
    }

    public List<String> getHistory() {
        return history;
    }
    public void setHistory(List<String> history) {
        this.history = history != null ? history : new ArrayList<>();
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon != null ? icon : "flag";
    }
}
