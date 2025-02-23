package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Inventory;
import com.stamp_iot_project.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ApiResponse<List<Inventory>> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public ApiResponse<Inventory> getInventoryById(@PathVariable Integer id) {
        return inventoryService.getInventoryById(id);
    }

    // Giả sử khi tạo Inventory bạn cần truyền thêm productId và warehouseId
    @PostMapping("/{productId}/{warehouseId}")
    public ApiResponse<Inventory> createInventory(@RequestBody Inventory inventory,
                                                  @PathVariable Integer productId,
                                                  @PathVariable Integer warehouseId) {
        return inventoryService.createInventory(inventory, productId, warehouseId);
    }

    @PutMapping("/{id}")
    public ApiResponse<Inventory> updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
        return inventoryService.updateInventory(id, inventory);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteInventory(@PathVariable Integer id) {
        return inventoryService.deleteInventory(id);
    }
}

