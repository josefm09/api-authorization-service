package com.ecommerce.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String email;
    private List<String> roles;

} 