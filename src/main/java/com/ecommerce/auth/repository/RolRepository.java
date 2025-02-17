package com.ecommerce.auth.repository;

import com.ecommerce.auth.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    @Query("SELECT r FROM Rol r JOIN UserRol ur ON r.rolId = ur.rolId WHERE ur.userId = :userId")
    List<Rol> findRolesByUserId(@Param("userId") Integer userId);
} 