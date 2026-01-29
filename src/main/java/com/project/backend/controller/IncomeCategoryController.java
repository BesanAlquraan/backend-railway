package com.project.backend.controller;


import com.project.backend.dto.CreateIncomeCategoryRequest;
import com.project.backend.dto.IncomeCategoryResponse;
import com.project.backend.service.IncomeCategoryService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income-categories")
public class IncomeCategoryController {

    private final IncomeCategoryService service;

    public IncomeCategoryController(IncomeCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<IncomeCategoryResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<IncomeCategoryResponse> create(
            @RequestBody @Valid CreateIncomeCategoryRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeCategoryResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid CreateIncomeCategoryRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
