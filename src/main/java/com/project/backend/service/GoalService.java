package com.project.backend.service;

import com.project.backend.dto.GoalRequestDTO;
import com.project.backend.dto.GoalResponseDTO;

import java.util.List;

public interface GoalService {
    GoalResponseDTO createGoal(Long userId, GoalRequestDTO dto);
    List<GoalResponseDTO> getGoals(Long userId);
    GoalResponseDTO updateGoal(Long id, Long userId, GoalRequestDTO dto);
    void deleteGoal(Long id, Long userId);
}
