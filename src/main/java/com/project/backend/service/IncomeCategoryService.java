package com.project.backend.service;

import com.project.backend.dto.CreateIncomeCategoryRequest;
import com.project.backend.dto.IncomeCategoryResponse;

import java.util.List;

public interface IncomeCategoryService {

    List<IncomeCategoryResponse> getAll();

    IncomeCategoryResponse create(CreateIncomeCategoryRequest request);

    IncomeCategoryResponse update(Long id, CreateIncomeCategoryRequest request);

    void delete(Long id);
}
