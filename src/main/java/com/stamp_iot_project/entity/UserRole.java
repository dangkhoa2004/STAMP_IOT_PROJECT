package com.stamp_iot_project.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "UserRole")
public class UserRole {
    
    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;
}