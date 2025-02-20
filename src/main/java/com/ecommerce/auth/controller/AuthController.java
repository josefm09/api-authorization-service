package com.ecommerce.auth.controller;

import com.ecommerce.auth.dto.LoginRequestDTO;
import com.ecommerce.auth.dto.LoginResponseDTO;
import com.ecommerce.auth.service.AuthService;
import com.ecommerce.e_utils.model.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {
    
    private final AuthService authService;
    
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @Operation(summary = "Login user", description = "Authenticates a user and returns a JWT token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully authenticated"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials"),
        @ApiResponse(responseCode = "403", description = "Account not active")
    })
    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequest) {
        Response<LoginResponseDTO> response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}