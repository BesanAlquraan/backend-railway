package com.project.backend.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔐 SECRET ثابت (لا تغير عند restart)
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("MySuperSecretKeyForJWT1234567890MySuperSecretKey".getBytes());

    // صلاحية التوكن 24 ساعة
    private final long EXPIRATION = 1000 * 60 * 60 * 24;

    // توليد توكن جديد
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SECRET_KEY)
                .compact();
    }

    // استخراج الايميل من التوكن
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // التحقق من صلاحية التوكن
    public boolean isTokenValid(String token) {
        try {
            String email = extractEmail(token);
            return email != null && !email.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
