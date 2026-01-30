package com.project.backend.service;

import com.project.backend.dto.TransactionRequestDTO;
import com.project.backend.dto.TransactionResponseDTO;
import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.model.*;
import com.project.backend.repository.TransactionRepository;
import com.project.backend.repository.ExpenseCategoryRepository;
import com.project.backend.repository.IncomeCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TransactionResponseDTO> getAllTransactions(User user) {
        List<Transaction> transactions = repository.findByUserId(user.getId());
        return transactions.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public TransactionResponseDTO createTransaction(User user, TransactionRequestDTO dto) {
        Transaction t = mapRequestToEntity(user, dto);
        Transaction saved = repository.save(t);
        return toResponseDTO(saved);
    }

    @Override
    public TransactionResponseDTO updateTransaction(Long id, User user, TransactionRequestDTO dto) {
        Transaction t = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        if (!t.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized access");

        t = mapRequestToEntity(user, dto, t); // تحديث الـ entity
        Transaction updated = repository.save(t);
        return toResponseDTO(updated);
    }

    @Override
    public void deleteTransaction(Long id, User user) {
        Transaction t = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        if (!t.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Unauthorized access");

        repository.delete(t);
    }

    // ===== Helper Methods =====
    private Transaction mapRequestToEntity(User user, TransactionRequestDTO dto) {
        return mapRequestToEntity(user, dto, new Transaction());
    }

    private Transaction mapRequestToEntity(User user, TransactionRequestDTO dto, Transaction t) {
        t.setTitle(dto.getTitle());
        t.setAmount(dto.getAmount());
        t.setDate(dto.getDate());
        t.setType(TransactionType.valueOf(dto.getType()));
        t.setUser(user);

        if (t.getType() == TransactionType.EXPENSE) {
            ExpenseCategory cat = expenseCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Expense category not found"));
            t.setExpenseCategory(cat);
            t.setIncomeCategory(null);
        } else {
            IncomeCategory cat = incomeCategoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Income category not found"));
            t.setIncomeCategory(cat);
            t.setExpenseCategory(null);
        }

        return t;
    }

    private TransactionResponseDTO toResponseDTO(Transaction t) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setId(t.getId());
        dto.setTitle(t.getTitle());
        dto.setAmount(t.getAmount());
        dto.setDate(t.getDate());
        dto.setType(t.getType().name());

        if (t.getType() == TransactionType.EXPENSE && t.getExpenseCategory() != null) {
            dto.setExpenseCategory(new TransactionResponseDTO.CategoryDTO(
                    t.getExpenseCategory().getId(),
                    t.getExpenseCategory().getName()
            ));
        } else if (t.getType() == TransactionType.INCOME && t.getIncomeCategory() != null) {
            dto.setIncomeCategory(new TransactionResponseDTO.CategoryDTO(
                    t.getIncomeCategory().getId(),
                    t.getIncomeCategory().getName()
            ));
        }

        return dto;
    }
}
