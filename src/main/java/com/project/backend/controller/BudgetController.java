package com.project.backend.controller;

import com.project.backend.dto.BudgetRequestDTO;
import com.project.backend.dto.BudgetResponseDTO;
import com.project.backend.service.BudgetService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public BudgetResponseDTO create(
            @AuthenticationPrincipal(expression = "id") Long userId,
            @RequestBody BudgetRequestDTO dto) {
        return budgetService.createBudget(userId, dto);
    }

    @GetMapping
    public List<BudgetResponseDTO> getAll(
            @AuthenticationPrincipal(expression = "id") Long userId) {
        return budgetService.getBudgets(userId);
    }

    @PutMapping("/{id}")
    public BudgetResponseDTO update(
            @PathVariable Long id,
            @AuthenticationPrincipal(expression = "id") Long userId,
            @RequestBody BudgetRequestDTO dto) {
        return budgetService.updateBudget(id, userId, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal(expression = "id") Long userId) {
        budgetService.deleteBudget(id, userId);
    }
}
