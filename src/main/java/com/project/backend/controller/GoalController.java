package com.project.backend.controller;

import com.project.backend.dto.GoalRequestDTO;
import com.project.backend.dto.GoalResponseDTO;
import com.project.backend.service.GoalService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public GoalResponseDTO createGoal(@AuthenticationPrincipal(expression = "id") Long userId,
                                      @RequestBody GoalRequestDTO dto) {
        return goalService.createGoal(userId, dto);
    }

    @GetMapping
    public List<GoalResponseDTO> getGoals(@AuthenticationPrincipal(expression = "id") Long userId) {
        return goalService.getGoals(userId);
    }

    @PutMapping("/{id}")
    public GoalResponseDTO updateGoal(@PathVariable Long id,
                                      @AuthenticationPrincipal(expression = "id") Long userId,
                                      @RequestBody GoalRequestDTO dto) {
        return goalService.updateGoal(id, userId, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable Long id,
                           @AuthenticationPrincipal(expression = "id") Long userId) {
        goalService.deleteGoal(id, userId);
    }
}
