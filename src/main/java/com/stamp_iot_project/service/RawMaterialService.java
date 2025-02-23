package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.RawMaterial;
import com.stamp_iot_project.entity.Supplier;
import com.stamp_iot_project.repository.RawMaterialRepository;
import com.stamp_iot_project.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RawMaterialService {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public ApiResponse<List<RawMaterial>> getAllMaterials() {
        List<RawMaterial> list = rawMaterialRepository.findAll();
        return new ApiResponse<>("success", "Fetched materials thành công", list, 200, new Date(), null);
    }

    public ApiResponse<RawMaterial> getMaterialById(Integer id) {
        Optional<RawMaterial> opt = rawMaterialRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched material thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Material không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<RawMaterial> createMaterial(RawMaterial mat, Integer supplierId) {
        if (supplierId != null) {
            Optional<Supplier> supOpt = supplierRepository.findById(supplierId);
            supOpt.ifPresent(mat::setSupplier);
        }
        RawMaterial saved = rawMaterialRepository.save(mat);
        return new ApiResponse<>("success", "Material created", saved, 201, new Date(), null);
    }

    public ApiResponse<RawMaterial> updateMaterial(Integer id, RawMaterial mat) {
        Optional<RawMaterial> opt = rawMaterialRepository.findById(id);
        if (opt.isPresent()) {
            RawMaterial existing = opt.get();
            existing.setName(mat.getName());
            existing.setDescription(mat.getDescription());
            existing.setUnit(mat.getUnit());
            existing.setCurrentStock(mat.getCurrentStock());
            existing.setReorderLevel(mat.getReorderLevel());
            // Nếu cần update supplier, đảm bảo supplierId được set trong mat.getSupplier()
            if (mat.getSupplier() != null && mat.getSupplier().getSupplierId() != null) {
                Optional<Supplier> supOpt = supplierRepository.findById(mat.getSupplier().getSupplierId());
                supOpt.ifPresent(existing::setSupplier);
            }
            RawMaterial updated = rawMaterialRepository.save(existing);
            return new ApiResponse<>("success", "Material updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Material không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteMaterial(Integer id) {
        rawMaterialRepository.deleteById(id);
        return new ApiResponse<>("success", "Material deleted", null, 200, new Date(), null);
    }
}

