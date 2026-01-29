package com.project.backend.service;

import com.project.backend.dto.BudgetRequestDTO;
import com.project.backend.dto.BudgetResponseDTO;
import java.util.List;

public interface BudgetService {

    BudgetResponseDTO createBudget(Long userId, BudgetRequestDTO dto);
    List<BudgetResponseDTO> getBudgets(Long userId);
    void deleteBudget(Long id, Long userId);

    // إضافة تحديث الباجيت
    BudgetResponseDTO updateBudget(Long id, Long userId, BudgetRequestDTO dto);
}
