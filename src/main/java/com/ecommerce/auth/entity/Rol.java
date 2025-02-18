package com.ecommerce.auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_rol")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer rolId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
    
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
} 