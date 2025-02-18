package com.ecommerce.auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name_1")
    private String lastName1;

    @Column(name = "last_name_2")
    private String lastName2;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
}