package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Inventory;
import com.stamp_iot_project.entity.InventoryTransaction;
import com.stamp_iot_project.repository.InventoryRepository;
import com.stamp_iot_project.repository.InventoryTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryTransactionService {

    @Autowired
    private InventoryTransactionRepository transactionRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    public ApiResponse<List<InventoryTransaction>> getAllTransactions() {
        List<InventoryTransaction> list = transactionRepository.findAll();
        return new ApiResponse<>("success", "Fetched transactions thành công", list, 200, new Date(), null);
    }

    public ApiResponse<InventoryTransaction> getTransactionById(Integer id) {
        Optional<InventoryTransaction> opt = transactionRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched transaction thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Transaction không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<InventoryTransaction> createTransaction(InventoryTransaction tx, Integer inventoryId) {
        Optional<Inventory> invOpt = inventoryRepository.findById(inventoryId);
        if (invOpt.isPresent()) {
            tx.setInventory(invOpt.get());
            tx.setTransactionDate(new Date());
            InventoryTransaction saved = transactionRepository.save(tx);
            return new ApiResponse<>("success", "Transaction created", saved, 201, new Date(), null);
        }
        return new ApiResponse<>("error", "Inventory không tìm thấy", null, 400, new Date(), null);
    }

    public ApiResponse<InventoryTransaction> updateTransaction(Integer id, InventoryTransaction tx) {
        Optional<InventoryTransaction> opt = transactionRepository.findById(id);
        if (opt.isPresent()) {
            InventoryTransaction existing = opt.get();
            existing.setTransactionType(tx.getTransactionType());
            existing.setQuantity(tx.getQuantity());
            existing.setDescription(tx.getDescription());
            InventoryTransaction updated = transactionRepository.save(existing);
            return new ApiResponse<>("success", "Transaction updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Transaction không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
        return new ApiResponse<>("success", "Transaction deleted", null, 200, new Date(), null);
    }
}
