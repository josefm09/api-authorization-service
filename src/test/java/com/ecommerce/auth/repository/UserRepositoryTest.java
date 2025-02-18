package com.ecommerce.auth.repository;

import com.ecommerce.auth.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_ShouldReturnUser() {
        // When
        Optional<User> userOpt = userRepository.findByEmail("test@example.com");

        // Then
        assertTrue(userOpt.isPresent());
        User user = userOpt.get();
        assertEquals("test@example.com", user.getEmail());
        assertEquals("active", user.getStatus());
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        // When
        long count = userRepository.count();

        // Then
        assertEquals(1, count);
    }
} 