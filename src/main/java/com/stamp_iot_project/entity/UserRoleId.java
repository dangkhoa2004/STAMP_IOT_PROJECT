package com.stamp_iot_project.entity;

import lombok.Data;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class UserRoleId implements Serializable {
    private Integer userId;
    private Integer roleId;

    // Bạn có thể tạo constructor, equals và hashCode (Lombok @Data đã hỗ trợ một phần)
}