package com.project.backend.service;

import com.project.backend.dto.PasswordResetRequestDTO;
import com.project.backend.dto.PasswordResetResponseDTO;
import com.project.backend.model.PasswordReset;
import com.project.backend.model.User;
import com.project.backend.repository.PasswordResetRepository;
import com.project.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    @Autowired
    private EmailService emailService;

    public PasswordResetResponseDTO requestPasswordReset(PasswordResetRequestDTO dto) {
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());
        if (!optionalUser.isPresent()) {
            return new PasswordResetResponseDTO("Email not found");
        }

        User user = optionalUser.get();

        // إنشاء توكن جديد
        PasswordReset reset = new PasswordReset();
        reset.setUserId(user.getId());
        reset.setResetToken(UUID.randomUUID().toString());
        reset.setExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusHours(1)));
        reset.setUsed(false);
        reset.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        reset.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        passwordResetRepository.save(reset);

        // رابط الـ deep link للتطبيق
     // رابط الـ web reset page على Netlify
        String resetLink = "https://gilded-starship-c552b2.netlify.app/reset.html?token=" 
                            + reset.getResetToken();



        	

        try {
            emailService.sendResetPasswordEmail(user.getEmail(),resetLink );
        } catch (Exception e) {
            return new PasswordResetResponseDTO("Failed to send email: " + e.getMessage());
        }

        return new PasswordResetResponseDTO("Reset link sent successfully");
    }
}
