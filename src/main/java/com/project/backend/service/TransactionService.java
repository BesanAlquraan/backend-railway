package com.project.backend.service;

import com.project.backend.dto.TransactionRequestDTO;
import com.project.backend.dto.TransactionResponseDTO;
import com.project.backend.model.User;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDTO> getAllTransactions(User user);

    TransactionResponseDTO createTransaction(User user, TransactionRequestDTO dto);

    TransactionResponseDTO updateTransaction(Long id, User user, TransactionRequestDTO dto);

    void deleteTransaction(Long id, User user);
}
