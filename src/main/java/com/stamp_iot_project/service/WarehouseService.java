package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Warehouse;
import com.stamp_iot_project.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public ApiResponse<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return new ApiResponse<>("success", "Fetched warehouses thành công", warehouses, 200, new Date(), null);
    }

    public ApiResponse<Warehouse> getWarehouseById(Integer id) {
        Optional<Warehouse> opt = warehouseRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched warehouse thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Warehouse không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Warehouse> createWarehouse(Warehouse warehouse) {
        Warehouse saved = warehouseRepository.save(warehouse);
        return new ApiResponse<>("success", "Warehouse created", saved, 201, new Date(), null);
    }

    public ApiResponse<Warehouse> updateWarehouse(Integer id, Warehouse warehouse) {
        Optional<Warehouse> opt = warehouseRepository.findById(id);
        if (opt.isPresent()) {
            Warehouse existing = opt.get();
            existing.setLocation(warehouse.getLocation());
            existing.setCapacity(warehouse.getCapacity());
            existing.setDescription(warehouse.getDescription());
            Warehouse updated = warehouseRepository.save(existing);
            return new ApiResponse<>("success", "Warehouse updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Warehouse không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteWarehouse(Integer id) {
        warehouseRepository.deleteById(id);
        return new ApiResponse<>("success", "Warehouse deleted", null, 200, new Date(), null);
    }
}
