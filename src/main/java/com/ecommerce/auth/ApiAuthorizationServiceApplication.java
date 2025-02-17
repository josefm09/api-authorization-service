package com.ecommerce.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ecommerce.auth")
@EntityScan("com.ecommerce.auth.entity")
@EnableJpaRepositories("com.ecommerce.auth.repository")
public class ApiAuthorizationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiAuthorizationServiceApplication.class, args);
    }
} 