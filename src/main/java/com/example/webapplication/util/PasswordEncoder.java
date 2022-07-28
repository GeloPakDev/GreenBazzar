package com.example.webapplication.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
    private PasswordEncoder() {
    }
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}