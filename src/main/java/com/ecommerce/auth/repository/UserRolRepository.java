package com.ecommerce.auth.repository;

import com.ecommerce.auth.entity.UserRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolRepository extends JpaRepository<UserRol, Integer> {
} 