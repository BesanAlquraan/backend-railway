package com.project.backend.controller;

import com.project.backend.dto.BankLinkRequest;
import com.project.backend.service.BankIntegrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mock-bank")
@CrossOrigin(origins = "*")
public class MockBankController {

    private final BankIntegrationService bankService;

    public MockBankController(BankIntegrationService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/link/{userId}")
    public ResponseEntity<String> linkBank(
            @PathVariable Long userId,
            @RequestBody BankLinkRequest request
    ) {
        try {
            boolean exists = bankService.checkAccountExists(request.getAccountNumber());
            if (!exists) {
                return ResponseEntity.status(404).body("Account number not found");
            }
            bankService.linkAccount(userId, request.getBankName(), request.getAccountNumber());
            return ResponseEntity.ok("Bank account linked successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
