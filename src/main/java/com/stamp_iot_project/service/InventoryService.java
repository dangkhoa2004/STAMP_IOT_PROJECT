package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Inventory;
import com.stamp_iot_project.entity.Product;
import com.stamp_iot_project.entity.Warehouse;
import com.stamp_iot_project.repository.InventoryRepository;
import com.stamp_iot_project.repository.ProductRepository;
import com.stamp_iot_project.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public ApiResponse<List<Inventory>> getAllInventories() {
        List<Inventory> list = inventoryRepository.findAll();
        return new ApiResponse<>("success", "Đã tìm nạp inventories thành công", list, 200, new Date(), null);
    }

    public ApiResponse<Inventory> getInventoryById(Integer id) {
        Optional<Inventory> opt = inventoryRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Đã tìm nạp inventory thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Inventory không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Inventory> createInventory(Inventory inv, Integer productId, Integer warehouseId) {
        Optional<Product> prodOpt = productRepository.findById(productId);
        Optional<Warehouse> wareOpt = warehouseRepository.findById(warehouseId);
        if (prodOpt.isPresent() && wareOpt.isPresent()) {
            inv.setProduct(prodOpt.get());
            inv.setWarehouse(wareOpt.get());
            Inventory saved = inventoryRepository.save(inv);
            return new ApiResponse<>("success", "Inventory created", saved, 201, new Date(), null);
        }
        return new ApiResponse<>("error", "Product or Warehouse không tìm thấy", null, 400, new Date(), null);
    }

    public ApiResponse<Inventory> updateInventory(Integer id, Inventory inv) {
        Optional<Inventory> opt = inventoryRepository.findById(id);
        if (opt.isPresent()) {
            Inventory existing = opt.get();
            existing.setQuantity(inv.getQuantity());
            Inventory updated = inventoryRepository.save(existing);
            return new ApiResponse<>("success", "Inventory updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Inventory không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteInventory(Integer id) {
        inventoryRepository.deleteById(id);
        return new ApiResponse<>("success", "Inventory deleted", null, 200, new Date(), null);
    }
}
