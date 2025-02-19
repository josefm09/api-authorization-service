package com.ecommerce.auth.service;

import com.ecommerce.auth.entity.Rol;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.RolRepository;
import com.ecommerce.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    private static final String TEST_EMAIL = "test@example.com";
    private static final String TEST_PASSWORD = "password123";
    private static final Integer TEST_USER_ID = 1;

    private User testUser;
    private List<Rol> testRoles;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUserId(TEST_USER_ID);
        testUser.setEmail(TEST_EMAIL);
        testUser.setPassword(TEST_PASSWORD);
        testUser.setStatus("active");

        Rol userRole = new Rol();
        userRole.setRolId(1);
        userRole.setName("USER");

        Rol adminRole = new Rol();
        adminRole.setRolId(2);
        adminRole.setName("ADMIN");

        testRoles = Arrays.asList(userRole, adminRole);
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {
        // Given
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(testUser));
        when(rolRepository.findRolesByUserId(TEST_USER_ID)).thenReturn(testRoles);

        // When
        UserDetails userDetails = userDetailsService.loadUserByUsername(TEST_EMAIL);

        // Then
        assertNotNull(userDetails);
        assertEquals(TEST_EMAIL, userDetails.getUsername());
        assertEquals(TEST_PASSWORD, userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    void loadUserByUsername_ShouldThrowException_WhenUserNotFound() {
        // Given
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> 
            userDetailsService.loadUserByUsername(TEST_EMAIL)
        );

        assertEquals("Usuario no encontrado con email: " + TEST_EMAIL, exception.getMessage());
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails_WhenUserExistsWithNoRoles() {
        // Given
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(testUser));
        when(rolRepository.findRolesByUserId(TEST_USER_ID)).thenReturn(List.of());

        // When
        UserDetails userDetails = userDetailsService.loadUserByUsername(TEST_EMAIL);

        // Then
        assertNotNull(userDetails);
        assertEquals(TEST_EMAIL, userDetails.getUsername());
        assertEquals(TEST_PASSWORD, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
    }

    @Test
    void loadUserByUsername_ShouldHandleNullEmail() {
        // When/Then
        assertThrows(UsernameNotFoundException.class, () -> 
            userDetailsService.loadUserByUsername(null)
        );
    }

    @Test
    void loadUserByUsername_ShouldHandleEmptyEmail() {
        // When/Then
        assertThrows(UsernameNotFoundException.class, () -> 
            userDetailsService.loadUserByUsername("")
        );
    }
} 