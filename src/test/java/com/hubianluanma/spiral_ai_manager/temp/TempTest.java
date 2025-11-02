package com.hubianluanma.spiral_ai_manager.temp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author huhailong
 * @version 1.0
 * @description: TODO
 * @date 2025/11/2 15:19
 */
public class TempTest {

    public static void main(String[] args) {
        String raw = "password";
        String encoded = "$2a$10$Gr7rAdLRpcxqMt6T1dsqFeP0mlKOTqsrj38AO2WKiI7XIWCOdiKxS";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.matches(raw, encoded));
//        System.out.println(encoder.encode(raw));
    }
}
