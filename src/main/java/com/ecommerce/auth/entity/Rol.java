package com.ecommerce.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TBL_ROL")
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

} 