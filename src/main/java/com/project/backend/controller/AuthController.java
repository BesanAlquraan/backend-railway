package com.project.backend.controller;

import com.project.backend.config.JwtUtil;
import com.project.backend.dto.LoginRequestDTO;
import com.project.backend.dto.LoginResponseDTO;
import com.project.backend.model.User;
import com.project.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
      //  يرجع Optional لتجنب NullPointerException,يبحث عن المستخدم باستخدام الإيميل
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("{\"message\":\"Invalid email or password\"}");
        }

        User user = userOpt.get();

        // تحقق من الباسورد المشفر
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(401).body("{\"message\":\"Invalid email or password\"}");
        }

        // توليد توكن JWT
        String token = jwtUtil.generateToken(user.getEmail());

        // تحديث last_login
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        // رجع الرد مع البيانات
        LoginResponseDTO response = new LoginResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                token
        );

        return ResponseEntity.ok(response);
    }
}
