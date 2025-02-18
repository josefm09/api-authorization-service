package com.ecommerce.auth.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    @Test
    void passwordEncoder_ShouldEncodeAndVerifyPassword() {
        // Given
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "testPassword123";

        // When
        String encodedPassword = encoder.encode(rawPassword);

        // Then
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertFalse(encoder.matches("wrongPassword", encodedPassword));
    }
} 