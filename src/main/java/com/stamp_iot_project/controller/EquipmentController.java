package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Equipment;
import com.stamp_iot_project.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ApiResponse<List<Equipment>> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/{id}")
    public ApiResponse<Equipment> getEquipmentById(@PathVariable Integer id) {
        return equipmentService.getEquipmentById(id);
    }

    // Khi tạo Equipment, nếu có departmentId truyền kèm
    @PostMapping("/{departmentId}")
    public ApiResponse<Equipment> createEquipment(@RequestBody Equipment equipment,
                                                  @PathVariable(required = false) Integer departmentId) {
        return equipmentService.createEquipment(equipment, departmentId);
    }

    @PutMapping("/{id}")
    public ApiResponse<Equipment> updateEquipment(@PathVariable Integer id, @RequestBody Equipment equipment) {
        return equipmentService.updateEquipment(id, equipment);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteEquipment(@PathVariable Integer id) {
        return equipmentService.deleteEquipment(id);
    }
}
