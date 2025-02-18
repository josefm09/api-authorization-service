package com.ecommerce.auth.service;

import com.ecommerce.auth.config.TestSecurityConfig;
import com.ecommerce.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void verifyUserExists() {
        // When
        var user = userRepository.findByEmail("test@example.com");

        // Then
        assertTrue(user.isPresent(), "User should exist in database");
        System.out.println("Found user: " + user.get().getEmail());
        assertEquals("active", user.get().getStatus());
        assertFalse(user.get().getDeleted());
    }
}