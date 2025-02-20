package com.ecommerce.auth.service;

import com.ecommerce.auth.dto.LoginRequestDTO;
import com.ecommerce.auth.dto.LoginResponseDTO;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.auth.repository.RolRepository;
import com.ecommerce.auth.security.JwtTokenProvider;
import com.ecommerce.e_utils.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.ecommerce.e_utils.model.Error;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

    public Response<LoginResponseDTO> login(LoginRequestDTO loginRequest) {
        Response<LoginResponseDTO> response = new Response<>();

        try {
            User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElse(null);

            if (user == null) {
                response.addError(new Error("User not found with email: " + loginRequest.getEmail()));
                return response;
            }

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                response.addError(new Error("Invalid password"));
                return response;
            }

            if (!"active".equalsIgnoreCase(user.getStatus())) {
                response.addError(new Error("User account is not active"));
                return response;
            }

            List<String> roles = rolRepository.findRolesByUserId(user.getUserId())
                    .stream()
                    .map(rol -> rol.getName())
                    .toList();

            String token = jwtTokenProvider.generateToken(user.getEmail(), roles);
            response.setData(new LoginResponseDTO(token, user.getEmail()));

        } catch (Exception e) {
            log.error("Error during login for email: {}", loginRequest.getEmail(), e);
            response.addError(new Error("GENERIC_ERROR: " + e.getMessage()));
        }

        return response;
    }
}