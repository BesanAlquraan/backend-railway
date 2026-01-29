package com.project.backend.service;

import com.project.backend.dto.TransactionRequestDTO;
import com.project.backend.model.Transaction;
import com.project.backend.model.User;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions(User user);

    Transaction createTransaction(User user, TransactionRequestDTO dto);

    Transaction updateTransaction(Long id, User user, TransactionRequestDTO dto);

    void deleteTransaction(Long id, User user);
}
