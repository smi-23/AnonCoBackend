package com.AnonCoBackend.domain.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PwEncoder {
    public static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encodePw(String pw) {
        return encoder.encode(pw);
    }
}
