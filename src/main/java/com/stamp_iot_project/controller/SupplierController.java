package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Supplier;
import com.stamp_iot_project.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ApiResponse<List<Supplier>> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public ApiResponse<Supplier> getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public ApiResponse<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    @PutMapping("/{id}")
    public ApiResponse<Supplier> updateSupplier(@PathVariable Integer id, @RequestBody Supplier supplier) {
        return supplierService.updateSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplier(id);
    }
}

