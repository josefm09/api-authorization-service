package com.ecommerce.auth.service;

import com.ecommerce.auth.dto.LoginRequestDTO;
import com.ecommerce.auth.dto.LoginResponseDTO;
import com.ecommerce.auth.exception.AuthenticationException;
import com.ecommerce.auth.entity.Rol;
import com.ecommerce.auth.entity.User;
import com.ecommerce.auth.repository.RolRepository;
import com.ecommerce.auth.repository.UserRepository;
import com.ecommerce.auth.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RolRepository rolRepository;
    
    @Autowired
    public AuthService(UserRepository userRepository, 
                      PasswordEncoder passwordEncoder,
                      JwtTokenProvider jwtTokenProvider,
                      RolRepository rolRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.rolRepository = rolRepository;
    }
    
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new AuthenticationException("Usuario o contraseña inválidos"));
            
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Usuario o contraseña inválidos");
        }
        
        if (!"ACTIVE".equals(user.getStatus())) {
            throw new AuthenticationException("Usuario inactivo");
        }
        
        List<String> roles = rolRepository.findRolesByUserId(user.getUserId())
            .stream()
            .map(Rol::getName)
            .collect(Collectors.toList());
            
        String token = jwtTokenProvider.generateToken(user.getEmail(), roles);
        
        return new LoginResponseDTO(token, user.getEmail(), roles);
    }
} 