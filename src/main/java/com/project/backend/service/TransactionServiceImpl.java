package com.project.backend.service;

import com.project.backend.dto.TransactionRequestDTO;
import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.model.*;
import com.project.backend.repository.TransactionRepository;
import com.project.backend.repository.ExpenseCategoryRepository;
import com.project.backend.repository.IncomeCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final IncomeCategoryRepository incomeCategoryRepository;

    public TransactionServiceImpl(TransactionRepository repository,
                                  ExpenseCategoryRepository expenseCategoryRepository,
                                  IncomeCategoryRepository incomeCategoryRepository) {
        this.repository = repository;
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.incomeCategoryRepository = incomeCategoryRepository;
    }

    @Override
    public List<Transaction> getAllTransactions(User user) {
        return repository.findByUserId(user.getId());
    }

    @Override
    public Transaction createTransaction(User user, TransactionRequestDTO dto) {
        Transaction t = new Transaction();
        t.setTitle(dto.getTitle());
        t.setAmount(dto.getAmount());
        t.setDate(dto.getDate());
        t.setType(TransactionType.valueOf(dto.getType())); // ✅ استخدام TransactionType
        t.setUser(user);

        if (t.getType() == TransactionType.EXPENSE) {
            ExpenseCategory cat = expenseCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Expense category not found"));
            t.setExpenseCategory(cat);
        } else { // INCOME
            IncomeCategory cat = incomeCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Income category not found"));
            t.setIncomeCategory(cat);
        }

        return repository.save(t);
    }

    @Override
    public Transaction updateTransaction(Long id, User user, TransactionRequestDTO dto) {
        Transaction t = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        if (!t.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized access");

        t.setTitle(dto.getTitle());
        t.setAmount(dto.getAmount());
        t.setDate(dto.getDate());
        t.setType(TransactionType.valueOf(dto.getType())); // ✅ استخدام TransactionType

        if (t.getType() == TransactionType.EXPENSE) {
            ExpenseCategory cat = expenseCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Expense category not found"));
            t.setExpenseCategory(cat);
            t.setIncomeCategory(null); // إزالة أي دخل سابق
        } else {
            IncomeCategory cat = incomeCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Income category not found"));
            t.setIncomeCategory(cat);
            t.setExpenseCategory(null); // إزالة أي مصروف سابق
        }

        return repository.save(t);
    }

    @Override
    public void deleteTransaction(Long id, User user) {
        Transaction t = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        if (!t.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized access");

        repository.delete(t);
    }
}
