package com.project.backend.service;

import com.project.backend.dto.ExpenseCategoryRequestDTO;
import com.project.backend.model.ExpenseCategory;
import com.project.backend.model.User;

import java.util.List;

public interface ExpenseCategoryService {

    List<ExpenseCategory> getAll(User user);

    ExpenseCategory create(User user, ExpenseCategoryRequestDTO dto);

    ExpenseCategory update(Long id, User user, ExpenseCategoryRequestDTO dto);

    void delete(Long id, User user);
}
