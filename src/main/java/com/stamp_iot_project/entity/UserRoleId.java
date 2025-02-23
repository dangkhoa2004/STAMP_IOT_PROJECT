package com.stamp_iot_project.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class UserRoleId implements Serializable {
    private Integer userId;
    private Integer roleId;
}