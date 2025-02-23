package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Warehouse;
import com.stamp_iot_project.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public ApiResponse<List<Warehouse>> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public ApiResponse<Warehouse> getWarehouseById(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id);
    }

    @PostMapping
    public ApiResponse<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @PutMapping("/{id}")
    public ApiResponse<Warehouse> updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        return warehouseService.updateWarehouse(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteWarehouse(@PathVariable Integer id) {
        return warehouseService.deleteWarehouse(id);
    }
}

