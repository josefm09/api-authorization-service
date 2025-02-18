package com.ecommerce.auth.service;

import com.ecommerce.auth.dto.LoginRequestDTO;
import com.ecommerce.auth.dto.LoginResponseDTO;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.auth.repository.RolRepository;
import com.ecommerce.auth.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository,
                      RolRepository rolRepository,
                      PasswordEncoder passwordEncoder,
                      JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!"active".equalsIgnoreCase(user.getStatus())) {
            throw new RuntimeException("User is not active");
        }

        List<String> roles = rolRepository.findRolesByUserId(user.getUserId())
                .stream()
                .map(rol -> rol.getName())
                .toList();

        String token = jwtTokenProvider.generateToken(user.getEmail(), roles);

        return new LoginResponseDTO(token, user.getEmail());
    }
} 