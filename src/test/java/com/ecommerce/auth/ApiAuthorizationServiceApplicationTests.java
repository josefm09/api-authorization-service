package com.ecommerce.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(properties = "spring.profiles.active=test")
@ContextConfiguration(classes = ApiAuthorizationServiceApplication.class)
class ApiAuthorizationServiceApplicationTests {

    @Test
    void contextLoads() {
    }
} 