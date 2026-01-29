package com.project.backend.controller;

import com.project.backend.dto.PasswordResetRequestDTO;
import com.project.backend.dto.PasswordResetResponseDTO;
import com.project.backend.service.PasswordResetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/request")
    public ResponseEntity<PasswordResetResponseDTO> requestReset(
            @Valid @RequestBody PasswordResetRequestDTO dto) {
        PasswordResetResponseDTO response = passwordResetService.requestPasswordReset(dto);
        return ResponseEntity.ok(response);
    }
}
