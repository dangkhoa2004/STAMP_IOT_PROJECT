package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.RawMaterial;
import com.stamp_iot_project.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class RawMaterialController {

    @Autowired
    private RawMaterialService materialService;

    @GetMapping
    public ApiResponse<List<RawMaterial>> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    public ApiResponse<RawMaterial> getMaterialById(@PathVariable Integer id) {
        return materialService.getMaterialById(id);
    }

    // Khi tạo RawMaterial, nếu có supplierId truyền kèm
    @PostMapping("/{supplierId}")
    public ApiResponse<RawMaterial> createMaterial(@RequestBody RawMaterial material,
                                                   @PathVariable(required = false) Integer supplierId) {
        return materialService.createMaterial(material, supplierId);
    }

    @PutMapping("/{id}")
    public ApiResponse<RawMaterial> updateMaterial(@PathVariable Integer id, @RequestBody RawMaterial material) {
        return materialService.updateMaterial(id, material);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteMaterial(@PathVariable Integer id) {
        return materialService.deleteMaterial(id);
    }
}
