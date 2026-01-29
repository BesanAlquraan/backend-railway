package com.project.backend.service;

import com.project.backend.dto.GoalRequestDTO;
import com.project.backend.dto.GoalResponseDTO;
import com.project.backend.model.Goal;
import com.project.backend.model.GoalPriority;
import com.project.backend.model.GoalStatus;
import com.project.backend.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public GoalResponseDTO createGoal(Long userId, GoalRequestDTO dto) {
        Goal goal = new Goal();
        goal.setUserId(userId);
        goal.setTitle(dto.getTitle());
        goal.setDescription(dto.getDescription());
        goal.setCategory(dto.getCategory());
        goal.setDueDate(dto.getDueDate());

        // حماية من null: قيم افتراضية
        goal.setStatus(dto.getStatus() != null
                ? GoalStatus.valueOf(dto.getStatus().toUpperCase())
                : GoalStatus.NOT_STARTED);

        goal.setPriority(dto.getPriority() != null
                ? GoalPriority.valueOf(dto.getPriority().toUpperCase())
                : GoalPriority.MEDIUM);

        goal.setTags(dto.getTags() != null
                ? dto.getTags().stream().filter(tag -> tag != null).collect(Collectors.toList())
                : new ArrayList<>());

        goal.setComments(dto.getComments() != null ? dto.getComments() : new ArrayList<>());
        goal.setHistory(dto.getHistory() != null ? dto.getHistory() : new ArrayList<>());
        goal.setRecurrence(dto.getRecurrence() != null ? dto.getRecurrence() : "");
        goal.setIcon(dto.getIcon() != null ? dto.getIcon() : "flag");

        goalRepository.save(goal);
        return mapToDTO(goal);
    }

    @Override
    public List<GoalResponseDTO> getGoals(Long userId) {
        return goalRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GoalResponseDTO updateGoal(Long id, Long userId, GoalRequestDTO dto) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        if (!goal.getUserId().equals(userId))
            throw new RuntimeException("Unauthorized");

        goal.setTitle(dto.getTitle());
        goal.setDescription(dto.getDescription());
        goal.setCategory(dto.getCategory());
        goal.setDueDate(dto.getDueDate());

        goal.setStatus(dto.getStatus() != null
                ? GoalStatus.valueOf(dto.getStatus().toUpperCase())
                : goal.getStatus());

        goal.setPriority(dto.getPriority() != null
                ? GoalPriority.valueOf(dto.getPriority().toUpperCase())
                : goal.getPriority());

        goal.setTags(dto.getTags() != null
                ? dto.getTags().stream().filter(tag -> tag != null).collect(Collectors.toList())
                : goal.getTags());

        goal.setComments(dto.getComments() != null ? dto.getComments() : goal.getComments());
        goal.setHistory(dto.getHistory() != null ? dto.getHistory() : goal.getHistory());
        goal.setRecurrence(dto.getRecurrence() != null ? dto.getRecurrence() : goal.getRecurrence());
        goal.setIcon(dto.getIcon() != null ? dto.getIcon() : goal.getIcon());

        goalRepository.save(goal);
        return mapToDTO(goal);
    }

    @Override
    public void deleteGoal(Long id, Long userId) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));
        if (!goal.getUserId().equals(userId))
            throw new RuntimeException("Unauthorized");

        goalRepository.delete(goal);
    }

    private GoalResponseDTO mapToDTO(Goal goal) {
        GoalResponseDTO dto = new GoalResponseDTO();
        dto.setId(goal.getId());
        dto.setTitle(goal.getTitle());
        dto.setDescription(goal.getDescription());
        dto.setCategory(goal.getCategory());
        dto.setStatus(goal.getStatus().name());
        dto.setPriority(goal.getPriority().name());
        dto.setProgress(goal.getProgress());
        dto.setDueDate(goal.getDueDate());
        dto.setTags(goal.getTags());
        dto.setComments(goal.getComments());
        dto.setHistory(goal.getHistory());
        dto.setRecurrence(goal.getRecurrence());
        dto.setIcon(goal.getIcon());
        return dto;
    }
}
