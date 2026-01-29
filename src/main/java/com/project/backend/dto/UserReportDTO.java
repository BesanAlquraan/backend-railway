package com.project.backend.dto;

import com.project.backend.model.Transaction;
import com.project.backend.model.ExpenseCategory;
import com.project.backend.model.IncomeCategory;

import java.util.List;

public class UserReportDTO {

    private Long userId;
    private List<Transaction> transactions;
    private List<ExpenseCategory> expenseCategories;
    private List<IncomeCategory> incomeCategories;
    private double totalIncome;
    private double totalExpense;
    private double netBalance;

    public UserReportDTO(
            Long userId,
            List<Transaction> transactions,
            List<ExpenseCategory> expenseCategories,
            List<IncomeCategory> incomeCategories,
            double totalIncome,
            double totalExpense,
            double netBalance
    ) {
        this.userId = userId;
        this.transactions = transactions;
        this.expenseCategories = expenseCategories;
        this.incomeCategories = incomeCategories;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.netBalance = netBalance;
    }

    // getters فقط (JSON يحتاجهم)
    public Long getUserId() { return userId; }
    public List<Transaction> getTransactions() { return transactions; }
    public List<ExpenseCategory> getExpenseCategories() { return expenseCategories; }
    public List<IncomeCategory> getIncomeCategories() { return incomeCategories; }
    public double getTotalIncome() { return totalIncome; }
    public double getTotalExpense() { return totalExpense; }
    public double getNetBalance() { return netBalance; }
}
