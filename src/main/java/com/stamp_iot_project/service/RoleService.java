package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Role;
import com.stamp_iot_project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ApiResponse<List<Role>> getAllRoles() {
        List<Role> list = roleRepository.findAll();
        return new ApiResponse<>("success", "Đã tìm nạp roles thành công", list, 200, new Date(), null);
    }

    public ApiResponse<Role> getRoleById(Integer id) {
        Optional<Role> opt = roleRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Đã tìm nạp role thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Role không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Role> createRole(Role role) {
        Role saved = roleRepository.save(role);
        return new ApiResponse<>("success", "Role created", saved, 201, new Date(), null);
    }

    public ApiResponse<Role> updateRole(Integer id, Role role) {
        Optional<Role> opt = roleRepository.findById(id);
        if (opt.isPresent()) {
            Role existing = opt.get();
            existing.setRoleName(role.getRoleName());
            existing.setDescription(role.getDescription());
            Role updated = roleRepository.save(existing);
            return new ApiResponse<>("success", "Role updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Role không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteRole(Integer id) {
        roleRepository.deleteById(id);
        return new ApiResponse<>("success", "Role deleted", null, 200, new Date(), null);
    }
}

