package com.project.backend.service;

import com.project.backend.dto.CreateIncomeCategoryRequest;
import com.project.backend.dto.IncomeCategoryResponse;
import com.project.backend.model.IncomeCategory;
import com.project.backend.model.User;
import com.project.backend.repository.IncomeCategoryRepository;
import com.project.backend.service.IncomeCategoryService;
import com.project.backend.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeCategoryServiceImpl implements IncomeCategoryService {

    private final IncomeCategoryRepository repository;

    public IncomeCategoryServiceImpl(IncomeCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<IncomeCategoryResponse> getAll() {
        User user = SecurityUtils.getCurrentUser();

        return repository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public IncomeCategoryResponse create(CreateIncomeCategoryRequest request) {
        User user = SecurityUtils.getCurrentUser();

        IncomeCategory category = new IncomeCategory();
        category.setName(request.getName());
        category.setType(request.getType());
        category.setColor(request.getColor());
        category.setIcon(request.getIcon());
        category.setUser(user);
        category.setCreatedAt(LocalDateTime.now());

        repository.save(category);
        return toResponse(category);
    }

    @Override
    public IncomeCategoryResponse update(Long id, CreateIncomeCategoryRequest request) {
        User user = SecurityUtils.getCurrentUser();

        IncomeCategory category = repository.findById(id)
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Income category not found"));

        category.setName(request.getName());
        category.setType(request.getType());
        category.setColor(request.getColor());
        category.setIcon(request.getIcon());

        repository.save(category);
        return toResponse(category);
    }

    @Override
    public void delete(Long id) {
        User user = SecurityUtils.getCurrentUser();

        IncomeCategory category = repository.findById(id)
                .filter(c -> c.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Income category not found"));

        repository.delete(category);
    }

    private IncomeCategoryResponse toResponse(IncomeCategory c) {
        IncomeCategoryResponse r = new IncomeCategoryResponse();
        r.setId(c.getId());
        r.setName(c.getName());
        r.setType(c.getType());
        r.setColor(c.getColor());
        r.setIcon(c.getIcon());
        return r;
    }
}
