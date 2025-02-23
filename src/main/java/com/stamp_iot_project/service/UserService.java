package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.User;
import com.stamp_iot_project.repository.EmployeeRepository;
import com.stamp_iot_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ApiResponse<List<User>> getAllUsers() {
        List<User> list = userRepository.findAll();
        return new ApiResponse<>("success", "Fetched users thành công", list, 200, new Date(), null);
    }

    public ApiResponse<User> getUserById(Integer id) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched user thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "User không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<User> createUser(User user, Integer employeeId) {
        if (employeeId != null) {
            employeeRepository.findById(employeeId).ifPresent(user::setEmployee);
        }
        User saved = userRepository.save(user);
        return new ApiResponse<>("success", "User created", saved, 201, new Date(), null);
    }

    public ApiResponse<User> updateUser(Integer id, User user) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User existing = opt.get();
            existing.setUsername(user.getUsername());
            existing.setPasswordHash(user.getPasswordHash());
            existing.setEmail(user.getEmail());
            existing.setStatus(user.getStatus());
            if (user.getEmployee() != null && user.getEmployee().getEmployeeId() != null) {
                employeeRepository.findById(user.getEmployee().getEmployeeId()).ifPresent(existing::setEmployee);
            }
            User updated = userRepository.save(existing);
            return new ApiResponse<>("success", "User updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "User không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteUser(Integer id) {
        userRepository.deleteById(id);
        return new ApiResponse<>("success", "User deleted", null, 200, new Date(), null);
    }
}

