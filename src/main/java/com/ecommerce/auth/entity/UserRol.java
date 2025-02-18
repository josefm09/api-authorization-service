package com.ecommerce.auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;

@Entity
@Table(name = "tbl_user_rol")
@Data
public class UserRol {
    @EmbeddedId
    private UserRolId id;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
    
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;
}

