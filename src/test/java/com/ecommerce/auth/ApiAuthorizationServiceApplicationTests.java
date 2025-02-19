package com.ecommerce.auth;

import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = "spring.profiles.active=test")
@ContextConfiguration(classes = ApiAuthorizationServiceApplication.class)
class ApiAuthorizationServiceApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "Application context should not be null");
    }

    @Test
    void main() {
        // Test that the main method runs without throwing exceptions
        ApiAuthorizationServiceApplication.main(new String[]{});
    }

    @Test
    void verifyEntityScanConfiguration() {
        // Verify that entity scanning is working by checking if a repository bean is created
        assertNotNull(applicationContext.getBean("userRepository"),
                "UserRepository bean should be created");
    }

    @Test
    void verifyRepositoryScanConfiguration() {
        // Verify that repository scanning is working
        assertNotNull(applicationContext.getBean("rolRepository"),
                "RolRepository bean should be created");
    }

    @Test
    void verifyServiceScanConfiguration() {
        // Verify that service scanning is working
        assertNotNull(applicationContext.getBean("authService"),
                "AuthService bean should be created");
    }
}