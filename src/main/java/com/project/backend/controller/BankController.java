package com.project.backend.controller;

import com.project.backend.model.BankAccount;
import com.project.backend.service.BankIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin(origins = "*")
public class BankController {

    private final BankIntegrationService bankService;

    public BankController(BankIntegrationService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/user/{id}/accounts")
    public ResponseEntity<?> getUserAccounts(@PathVariable Long id) {
        try {
            List<BankAccount> accounts = bankService.getUserAccounts(id);
            return ResponseEntity.ok(accounts);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
