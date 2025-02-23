package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Supplier;
import com.stamp_iot_project.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public ApiResponse<List<Supplier>> getAllSuppliers() {
        List<Supplier> list = supplierRepository.findAll();
        return new ApiResponse<>("success", "Đã tìm nạp suppliers thành công", list, 200, new Date(), null);
    }

    public ApiResponse<Supplier> getSupplierById(Integer id) {
        Optional<Supplier> opt = supplierRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Đã tìm nạp supplier thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Supplier không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Supplier> createSupplier(Supplier supplier) {
        Supplier saved = supplierRepository.save(supplier);
        return new ApiResponse<>("success", "Supplier created", saved, 201, new Date(), null);
    }

    public ApiResponse<Supplier> updateSupplier(Integer id, Supplier supplier) {
        Optional<Supplier> opt = supplierRepository.findById(id);
        if (opt.isPresent()) {
            Supplier existing = opt.get();
            existing.setName(supplier.getName());
            existing.setContactInfo(supplier.getContactInfo());
            Supplier updated = supplierRepository.save(existing);
            return new ApiResponse<>("success", "Supplier updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Supplier không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteSupplier(Integer id) {
        supplierRepository.deleteById(id);
        return new ApiResponse<>("success", "Supplier deleted", null, 200, new Date(), null);
    }
}

