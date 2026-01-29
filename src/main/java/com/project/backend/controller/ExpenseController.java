package com.project.backend.controller;

import com.project.backend.model.Expense;
import com.project.backend.model.ExpenseCategory;
import com.project.backend.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // كل المصاريف لمستخدم
    @GetMapping("/{userId}")
    public List<Expense> getAllExpenses(@PathVariable Long userId) {
        return expenseService.getAllExpenses(userId);
    }

    // فلترة
    @GetMapping("/filter/{userId}")
    public List<Expense> filterExpenses(
            @PathVariable Long userId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount,
            @RequestParam(required = false) String startDate, // بصيغة ISO
            @RequestParam(required = false) String endDate
    ) {
        LocalDateTime start = (startDate != null) ? LocalDateTime.parse(startDate) : null;
        LocalDateTime end = (endDate != null) ? LocalDateTime.parse(endDate) : null;

        return expenseService.filterExpenses(userId, categoryId, minAmount, maxAmount, start, end);
    }

    // كل الفئات
    @GetMapping("/categories/{userId}")
    public List<ExpenseCategory> getCategories(@PathVariable Long userId) {
        return expenseService.getCategories(userId);
    }

    // إجماليات اليوم
    @GetMapping("/total/today/{userId}")
    public double totalToday(@PathVariable Long userId) {
        return expenseService.totalToday(userId);
    }

    // إجماليات هذا الشهر
    @GetMapping("/total/month/{userId}")
    public double totalThisMonth(@PathVariable Long userId) {
        return expenseService.totalThisMonth(userId);
    }

    // أكبر مصروف
    @GetMapping("/largest/{userId}")
    public double largestExpense(@PathVariable Long userId) {
        return expenseService.largestExpense(userId);
    }
}
