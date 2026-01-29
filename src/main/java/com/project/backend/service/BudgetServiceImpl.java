package com.project.backend.service;

import com.project.backend.dto.BudgetRequestDTO;
import com.project.backend.dto.BudgetResponseDTO;
import com.project.backend.model.Budget;
import com.project.backend.repository.BudgetRepository;
import com.project.backend.service.BudgetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public BudgetResponseDTO createBudget(Long userId, BudgetRequestDTO dto) {
        Budget budget = new Budget();
        budget.setUserId(userId);
        budget.setCategoryId(dto.getCategoryId());
        budget.setLimitAmount(dto.getLimitAmount());
        budget.setIsMonthly(dto.getIsMonthly());
        budget.setPeriod(dto.getIsMonthly() ? "Monthly" : "Weekly");

        budgetRepository.save(budget);
        return mapToDTO(budget);
    }

    @Override
    public List<BudgetResponseDTO> getBudgets(Long userId) {
        return budgetRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBudget(Long id, Long userId) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        if (!budget.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        budgetRepository.delete(budget);
    }

    private BudgetResponseDTO mapToDTO(Budget b) {
        BudgetResponseDTO dto = new BudgetResponseDTO();
        dto.setId(b.getId());
        dto.setCategoryId(b.getCategoryId());
        dto.setLimitAmount(b.getLimitAmount());
        dto.setSpentAmount(b.getSpentAmount());

        double spent = b.getSpentAmount().doubleValue();
        double limit = b.getLimitAmount().doubleValue();
        int health = limit == 0 ? 100 : (int) ((1 - (spent / limit)) * 100);

        dto.setHealthScore(Math.max(0, Math.min(health, 100)));
        dto.setIsMonthly(b.getIsMonthly());
        return dto;
    }
    @Override
    public BudgetResponseDTO updateBudget(Long id, Long userId, BudgetRequestDTO dto) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        if (!budget.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        budget.setCategoryId(dto.getCategoryId());
        budget.setLimitAmount(dto.getLimitAmount());
        budget.setIsMonthly(dto.getIsMonthly());
        budget.setPeriod(dto.getIsMonthly() ? "Monthly" : "Weekly");

        budgetRepository.save(budget);

        return mapToDTO(budget);
    }

}
