package com.project.lingo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String raw = "password";
        String encodedPassword = encoder.encode(raw);

        System.out.println(encodedPassword);
    }
}
