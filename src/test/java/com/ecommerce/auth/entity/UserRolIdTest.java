package com.ecommerce.auth.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRolIdTest {

    @Test
    void testNoArgsConstructor() {
        // When
        UserRolId userRolId = new UserRolId();

        // Then
        assertNull(userRolId.getRolId());
        assertNull(userRolId.getUserId());
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        Integer rolId = 1;
        Integer userId = 1;

        // When
        UserRolId userRolId = new UserRolId(rolId, userId);

        // Then
        assertEquals(rolId, userRolId.getRolId());
        assertEquals(userId, userRolId.getUserId());
    }

    @Test
    void testEquals_WhenSameObject() {
        // Given
        UserRolId userRolId = new UserRolId(1, 1);

        // When/Then
        assertTrue(userRolId.equals(userRolId));
    }

    @Test
    void testEquals_WhenNull() {
        // Given
        UserRolId userRolId = new UserRolId(1, 1);

        // When/Then
        assertFalse(userRolId.equals(null));
    }

    @Test
    void testEquals_WhenDifferentClass() {
        // Given
        UserRolId userRolId = new UserRolId(1, 1);
        Object differentObject = new Object();

        // When/Then
        assertFalse(userRolId.equals(differentObject));
    }

    @Test
    void testEquals_WhenSameValues() {
        // Given
        UserRolId userRolId1 = new UserRolId(1, 1);
        UserRolId userRolId2 = new UserRolId(1, 1);

        // When/Then
        assertTrue(userRolId1.equals(userRolId2));
        assertTrue(userRolId2.equals(userRolId1));
    }

    @Test
    void testEquals_WhenDifferentValues() {
        // Given
        UserRolId userRolId1 = new UserRolId(1, 1);
        UserRolId userRolId2 = new UserRolId(2, 2);

        // When/Then
        assertFalse(userRolId1.equals(userRolId2));
        assertFalse(userRolId2.equals(userRolId1));
    }

    @Test
    void testHashCode_WhenSameValues() {
        // Given
        UserRolId userRolId1 = new UserRolId(1, 1);
        UserRolId userRolId2 = new UserRolId(1, 1);

        // When/Then
        assertEquals(userRolId1.hashCode(), userRolId2.hashCode());
    }

    @Test
    void testHashCode_WhenDifferentValues() {
        // Given
        UserRolId userRolId1 = new UserRolId(1, 1);
        UserRolId userRolId2 = new UserRolId(2, 2);

        // When/Then
        assertNotEquals(userRolId1.hashCode(), userRolId2.hashCode());
    }

    @Test
    void testSettersAndGetters() {
        // Given
        UserRolId userRolId = new UserRolId();

        // When
        userRolId.setRolId(1);
        userRolId.setUserId(1);

        // Then
        assertEquals(1, userRolId.getRolId());
        assertEquals(1, userRolId.getUserId());
    }

    @Test
    void testNullValues() {
        // Given
        UserRolId userRolId1 = new UserRolId(null, null);
        UserRolId userRolId2 = new UserRolId(null, null);

        // When/Then
        assertEquals(userRolId1, userRolId2);
        assertEquals(userRolId1.hashCode(), userRolId2.hashCode());
    }
} 