package com.ecommerce.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;
    private static final String TEST_EMAIL = "test@example.com";
    private static final List<String> TEST_ROLES = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
    private static final String SECRET = "c2VjcmV0LWtleS1mb3ItandALXRva2VuLWdlbmVyYXRpb24tYW5kLXZlcmlmaWNhdGlvbg==";
    private static final int EXPIRATION = 3600000; // 1 hour

    @BeforeEach
    void setUp() {
        jwtTokenProvider = new JwtTokenProvider(SECRET);
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtExpiration", EXPIRATION);
    }

    @Test
    void generateToken_ShouldCreateValidToken() {
        // When
        String token = jwtTokenProvider.generateToken(TEST_EMAIL, TEST_ROLES);

        // Then
        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token));
    }

    @Test
    void getEmailFromToken_ShouldReturnCorrectEmail() {
        // Given
        String token = jwtTokenProvider.generateToken(TEST_EMAIL, TEST_ROLES);

        // When
        String email = jwtTokenProvider.getEmailFromToken(token);

        // Then
        assertEquals(TEST_EMAIL, email);
    }

    @Test
    void validateToken_ShouldReturnTrueForValidToken() {
        // Given
        String token = jwtTokenProvider.generateToken(TEST_EMAIL, TEST_ROLES);

        // When
        boolean isValid = jwtTokenProvider.validateToken(token);

        // Then
        assertTrue(isValid);
    }

    @Test
    void validateToken_ShouldReturnFalseForInvalidToken() {
        // Given
        String invalidToken = "invalid.token.string";

        // When
        boolean isValid = jwtTokenProvider.validateToken(invalidToken);

        // Then
        assertFalse(isValid);
    }

    @Test
    void validateToken_ShouldReturnFalseForExpiredToken() throws InterruptedException {
        // Given
        ReflectionTestUtils.setField(jwtTokenProvider, "jwtExpiration", 1); // 1ms expiration
        String token = jwtTokenProvider.generateToken(TEST_EMAIL, TEST_ROLES);
        
        // When
        Thread.sleep(2); // Wait for token to expire
        boolean isValid = jwtTokenProvider.validateToken(token);

        // Then
        assertFalse(isValid);
    }
} 