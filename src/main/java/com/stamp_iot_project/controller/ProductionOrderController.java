package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.ProductionOrder;
import com.stamp_iot_project.service.ProductionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class ProductionOrderController {

    @Autowired
    private ProductionOrderService orderService;

    @GetMapping
    public ApiResponse<List<ProductionOrder>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductionOrder> getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    // Giả sử cần truyền productId để tạo ProductionOrder
    @PostMapping("/{productId}")
    public ApiResponse<ProductionOrder> createOrder(@RequestBody ProductionOrder order,
                                                    @PathVariable Integer productId) {
        return orderService.createOrder(order, productId);
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductionOrder> updateOrder(@PathVariable Integer id,
                                                    @RequestBody ProductionOrder order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteOrder(@PathVariable Integer id) {
        return orderService.deleteOrder(id);
    }
}

