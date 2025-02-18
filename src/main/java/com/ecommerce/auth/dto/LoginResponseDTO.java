package com.ecommerce.auth.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String email;
    private List<String> roles;

    public LoginResponseDTO(String token, String email) {
        this.token = token;
        this.email = email;
    }
}