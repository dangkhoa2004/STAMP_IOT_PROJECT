package com.stamp_iot_project.repository;

import com.stamp_iot_project.entity.UserRole;
import com.stamp_iot_project.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
