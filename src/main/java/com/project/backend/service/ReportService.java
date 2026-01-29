package com.project.backend.service;

import com.project.backend.dto.UserReportDTO;
import com.project.backend.model.Transaction;
import com.project.backend.model.ExpenseCategory;
import com.project.backend.model.IncomeCategory;
import com.project.backend.repository.TransactionRepository;
import com.project.backend.repository.ExpenseCategoryRepository;
import com.project.backend.repository.IncomeCategoryRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportService {

    private final TransactionRepository transactionRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final IncomeCategoryRepository incomeCategoryRepository;

    public ReportService(TransactionRepository transactionRepository,
                         ExpenseCategoryRepository expenseCategoryRepository,
                         IncomeCategoryRepository incomeCategoryRepository) {
        this.transactionRepository = transactionRepository;
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.incomeCategoryRepository = incomeCategoryRepository;
    }

    public UserReportDTO generateReport(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        List<ExpenseCategory> expenseCategories = expenseCategoryRepository.findByUserId(userId);
        List<IncomeCategory> incomeCategories = incomeCategoryRepository.findByUserId(userId);

        double totalIncome = transactions.stream()
                .filter(t -> t.getType().equals("income"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpense = transactions.stream()
                .filter(t -> t.getType().equals("expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        double netBalance = totalIncome - totalExpense;

        return new UserReportDTO(userId, transactions, expenseCategories, incomeCategories,
                totalIncome, totalExpense, netBalance);
    }
}
