package com.project.backend.controller;

import com.project.backend.dto.TransactionRequestDTO;
import com.project.backend.dto.TransactionResponseDTO;
import com.project.backend.model.User;
import com.project.backend.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // =================== GET ALL ===================
    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAll(@AuthenticationPrincipal User user) {
        List<TransactionResponseDTO> transactions = service.getAllTransactions(user);
        return ResponseEntity.ok(transactions);
    }

    // =================== CREATE ===================
    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@AuthenticationPrincipal User user,
                                                         @RequestBody TransactionRequestDTO dto) {
        TransactionResponseDTO created = service.createTransaction(user, dto);
        return ResponseEntity.ok(created);
    }

    // =================== UPDATE ===================
    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> update(@PathVariable Long id,
                                                         @AuthenticationPrincipal User user,
                                                         @RequestBody TransactionRequestDTO dto) {
        TransactionResponseDTO updated = service.updateTransaction(id, user, dto);
        return ResponseEntity.ok(updated);
    }

    // =================== DELETE ===================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       @AuthenticationPrincipal User user) {
        service.deleteTransaction(id, user);
        return ResponseEntity.noContent().build();
    }

    // =================== BANK FEED ===================
    @PostMapping("/bank-feed")
    public ResponseEntity<List<TransactionResponseDTO>> bankFeed(@AuthenticationPrincipal User user,
                                                                 @RequestBody List<TransactionRequestDTO> transactions) {
        List<TransactionResponseDTO> savedTransactions = transactions.stream()
                .map(dto -> service.createTransaction(user, dto))
                .toList();
        return ResponseEntity.ok(savedTransactions);
    }
}
