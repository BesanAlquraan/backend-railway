package com.project.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetPasswordEmail(String toEmail, String webLink ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("besanalqraan29@gmail.com"); // البريد اللي عملت له App Password
        helper.setTo(toEmail);
        helper.setSubject("Password Reset Request");

        String htmlMsg = "<p>Hi!</p>"
                + "<p>Click the link below to reset your password:</p>"
                + "<a href=\"" + webLink + "\">Reset Password</a>"
                + "<p>This link will expire in 1 hour.</p>";
 helper.setText(htmlMsg, true);

       
        mailSender.send(message);
    }
}
