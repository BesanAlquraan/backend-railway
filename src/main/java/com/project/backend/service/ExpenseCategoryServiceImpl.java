package com.project.backend.service;

import com.project.backend.dto.ExpenseCategoryRequestDTO;
import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.model.ExpenseCategory;
import com.project.backend.model.User;
import com.project.backend.repository.ExpenseCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    private final ExpenseCategoryRepository repository;

    // ===== Constructor يدوي =====
    public ExpenseCategoryServiceImpl(ExpenseCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ExpenseCategory> getAll(User user) {
        return repository.findByUserId(user.getId());
    }

    @Override
    public ExpenseCategory create(User user, ExpenseCategoryRequestDTO dto) {
        // ===== إنشاء Category يدوي بدون Builder =====
        ExpenseCategory category = new ExpenseCategory();
        category.setUser(user);
        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setColor(dto.getColor());
        category.setIcon(dto.getIcon());
        category.setCreatedAt(LocalDateTime.now());

        return repository.save(category);
    }

    @Override
    public ExpenseCategory update(Long id, User user, ExpenseCategoryRequestDTO dto) {
        ExpenseCategory category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (!category.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized access");

        category.setName(dto.getName());
        category.setType(dto.getType());
        category.setColor(dto.getColor());
        category.setIcon(dto.getIcon());

        return repository.save(category);
    }

    @Override
    public void delete(Long id, User user) {
        ExpenseCategory category = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (!category.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized access");

        repository.delete(category);
    }
}
