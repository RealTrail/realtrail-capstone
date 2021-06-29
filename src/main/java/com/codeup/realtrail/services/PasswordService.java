package com.codeup.realtrail.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private static final int ROUNDS = 12;

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(ROUNDS));
    }

    public static boolean check(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
