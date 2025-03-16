package ru.developeerz.user_service.core.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private final PasswordEncoder passwordEncoder;

    public PasswordUtil() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public String getHashed(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean isMatch(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}
