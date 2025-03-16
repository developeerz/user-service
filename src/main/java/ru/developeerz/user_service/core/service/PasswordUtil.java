package ru.developeerz.user_service.core.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    public static String getHashed(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean isMatch(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
