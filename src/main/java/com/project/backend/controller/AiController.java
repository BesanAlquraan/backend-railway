package com.project.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.backend.service.AiService;
import com.project.backend.dto.GeminiResponseDTO;
import com.project.backend.dto.GeminiRequestDTO;
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    // 👇 constructor يدوي
    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/chat")
    public ResponseEntity<GeminiResponseDTO> chat(
            @RequestBody GeminiRequestDTO request
    ) {
        String reply = aiService.askGemini(request.getMessage());
        return ResponseEntity.ok(new GeminiResponseDTO(reply));
    }
}
