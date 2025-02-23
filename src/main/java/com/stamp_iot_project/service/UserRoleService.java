package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Role;
import com.stamp_iot_project.entity.User;
import com.stamp_iot_project.entity.UserRole;
import com.stamp_iot_project.entity.UserRoleId;
import com.stamp_iot_project.repository.RoleRepository;
import com.stamp_iot_project.repository.UserRepository;
import com.stamp_iot_project.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public ApiResponse<UserRole> createUserRole(Integer userId, Integer roleId) {
        UserRole userRole = new UserRole();
        UserRoleId id = new UserRoleId();
        id.setUserId(userId);
        id.setRoleId(roleId);
        userRole.setId(id);
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Role> roleOpt = roleRepository.findById(roleId);
        if (userOpt.isPresent() && roleOpt.isPresent()) {
            userRole.setUser(userOpt.get());
            userRole.setRole(roleOpt.get());
            UserRole saved = userRoleRepository.save(userRole);
            return new ApiResponse<>("success", "UserRole created", saved, 201, new Date(), null);
        }
        return new ApiResponse<>("error", "User or Role không tìm thấy", null, 400, new Date(), null);
    }

    public ApiResponse<Void> deleteUserRole(Integer userId, Integer roleId) {
        UserRoleId id = new UserRoleId();
        id.setUserId(userId);
        id.setRoleId(roleId);
        userRoleRepository.deleteById(id);
        return new ApiResponse<>("success", "UserRole deleted", null, 200, new Date(), null);
    }
}

