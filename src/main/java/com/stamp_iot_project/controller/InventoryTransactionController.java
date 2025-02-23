package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.InventoryTransaction;
import com.stamp_iot_project.service.InventoryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class InventoryTransactionController {

    @Autowired
    private InventoryTransactionService transactionService;

    @GetMapping
    public ApiResponse<List<InventoryTransaction>> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ApiResponse<InventoryTransaction> getTransactionById(@PathVariable Integer id) {
        return transactionService.getTransactionById(id);
    }

    // Sử dụng PathVariable để chỉ định inventoryId tương ứng khi tạo transaction
    @PostMapping("/{inventoryId}")
    public ApiResponse<InventoryTransaction> createTransaction(@RequestBody InventoryTransaction transaction,
                                                               @PathVariable Integer inventoryId) {
        return transactionService.createTransaction(transaction, inventoryId);
    }

    @PutMapping("/{id}")
    public ApiResponse<InventoryTransaction> updateTransaction(@PathVariable Integer id,
                                                               @RequestBody InventoryTransaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTransaction(@PathVariable Integer id) {
        return transactionService.deleteTransaction(id);
    }
}
