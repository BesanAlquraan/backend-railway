package com.project.backend.controller;

import com.project.backend.dto.TransactionRequestDTO;
import com.project.backend.model.Transaction;
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
    public ResponseEntity<List<Transaction>> getAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.getAllTransactions(user));
    }

    // =================== CREATE ===================
    @PostMapping
    public ResponseEntity<Transaction> create(@AuthenticationPrincipal User user,
                                              @RequestBody TransactionRequestDTO dto) {
        return ResponseEntity.ok(service.createTransaction(user, dto));
    }

    // =================== UPDATE ===================
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable Long id,
                                              @AuthenticationPrincipal User user,
                                              @RequestBody TransactionRequestDTO dto) {
        return ResponseEntity.ok(service.updateTransaction(id, user, dto));
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
    public ResponseEntity<List<Transaction>> bankFeed(@AuthenticationPrincipal User user,
                                                      @RequestBody List<TransactionRequestDTO> transactions) {
        List<Transaction> savedTransactions = transactions.stream()
                .map(dto -> service.createTransaction(user, dto))
                .toList();
        return ResponseEntity.ok(savedTransactions);
    }
}
