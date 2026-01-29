package com.project.backend.service;

import com.project.backend.model.Expense;
import com.project.backend.model.ExpenseCategory;
import com.project.backend.model.User;
import com.project.backend.repository.ExpenseRepository;
import com.project.backend.repository.ExpenseCategoryRepository;
import com.project.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          ExpenseCategoryRepository categoryRepository,
                          UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    // جلب كل المصاريف لمستخدم
    public List<Expense> getAllExpenses(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    // فلترة حسب فئة، مبلغ، تاريخ
    public List<Expense> filterExpenses(Long userId, Long categoryId,
                                        Double minAmount, Double maxAmount,
                                        LocalDateTime startDate, LocalDateTime endDate) {

        List<Expense> expenses = expenseRepository.findByUserId(userId);

        if (categoryId != null) {
            expenses = expenses.stream()
                    .filter(e -> e.getCategory().getId().equals(categoryId))
                    .collect(Collectors.toList());
        }

        if (minAmount != null) {
            expenses = expenses.stream()
                    .filter(e -> e.getAmount() >= minAmount)
                    .collect(Collectors.toList());
        }

        if (maxAmount != null) {
            expenses = expenses.stream()
                    .filter(e -> e.getAmount() <= maxAmount)
                    .collect(Collectors.toList());
        }

        if (startDate != null) {
            expenses = expenses.stream()
                    .filter(e -> !e.getDate().isBefore(startDate))
                    .collect(Collectors.toList());
        }

        if (endDate != null) {
            expenses = expenses.stream()
                    .filter(e -> !e.getDate().isAfter(endDate))
                    .collect(Collectors.toList());
        }

        return expenses;
    }

    // جلب كل فئات المصاريف لمستخدم
    public List<ExpenseCategory> getCategories(Long userId) {
        return categoryRepository.findByUserId(userId);
    }

    // إجمالي اليوم
    public double totalToday(Long userId) {
        LocalDateTime start = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime end = LocalDateTime.now();
        return getAllExpenses(userId).stream()
                .filter(e -> !e.getDate().isBefore(start) && !e.getDate().isAfter(end))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // إجمالي هذا الشهر
    public double totalThisMonth(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime end = now;
        return getAllExpenses(userId).stream()
                .filter(e -> !e.getDate().isBefore(start) && !e.getDate().isAfter(end))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // أكبر مصروف
    public double largestExpense(Long userId) {
        return getAllExpenses(userId).stream()
                .mapToDouble(Expense::getAmount)
                .max()
                .orElse(0);
    }
}
