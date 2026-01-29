package com.project.backend.controller;

import com.project.backend.dto.ExpenseCategoryRequestDTO;
import com.project.backend.dto.ExpenseCategoryResponseDTO;
import com.project.backend.model.ExpenseCategory;
import com.project.backend.model.User;
import com.project.backend.service.ExpenseCategoryService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense-categories")
public class ExpenseCategoryController {

    private final ExpenseCategoryService service;

    public ExpenseCategoryController(ExpenseCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<ExpenseCategoryResponseDTO> getAll(@AuthenticationPrincipal User user) {
        return service.getAll(user).stream().map(this::map).toList();
    }

    @PostMapping
    public ExpenseCategoryResponseDTO create(@AuthenticationPrincipal User user,
                                             @Valid @RequestBody ExpenseCategoryRequestDTO dto) {
        return map(service.create(user, dto));
    }

    @PutMapping("/{id}")
    public ExpenseCategoryResponseDTO update(@PathVariable Long id,
                                             @AuthenticationPrincipal User user,
                                             @Valid @RequestBody ExpenseCategoryRequestDTO dto) {
        return map(service.update(id, user, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @AuthenticationPrincipal User user) {
        service.delete(id, user);
    }

    private ExpenseCategoryResponseDTO map(ExpenseCategory c) {
        return ExpenseCategoryResponseDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .type(c.getType())
                .color(c.getColor())
                .icon(c.getIcon())
                .createdAt(c.getCreatedAt())
                .build();
    }
}
