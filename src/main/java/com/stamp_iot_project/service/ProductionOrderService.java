package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.ProductionOrder;
import com.stamp_iot_project.entity.Product;
import com.stamp_iot_project.repository.ProductionOrderRepository;
import com.stamp_iot_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductionOrderService {

    @Autowired
    private ProductionOrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public ApiResponse<List<ProductionOrder>> getAllOrders() {
        List<ProductionOrder> list = orderRepository.findAll();
        return new ApiResponse<>("success", "Fetched orders thành công", list, 200, new Date(), null);
    }

    public ApiResponse<ProductionOrder> getOrderById(Integer id) {
        Optional<ProductionOrder> opt = orderRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched order thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Order không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<ProductionOrder> createOrder(ProductionOrder order, Integer productId) {
        Optional<Product> prodOpt = productRepository.findById(productId);
        if (prodOpt.isPresent()) {
            order.setProduct(prodOpt.get());
            ProductionOrder saved = orderRepository.save(order);
            return new ApiResponse<>("success", "Order created", saved, 201, new Date(), null);
        }
        return new ApiResponse<>("error", "Product không tìm thấy", null, 400, new Date(), null);
    }

    public ApiResponse<ProductionOrder> updateOrder(Integer id, ProductionOrder order) {
        Optional<ProductionOrder> opt = orderRepository.findById(id);
        if (opt.isPresent()) {
            ProductionOrder existing = opt.get();
            existing.setQuantity(order.getQuantity());
            existing.setStartDate(order.getStartDate());
            existing.setEndDate(order.getEndDate());
            existing.setStatus(order.getStatus());
            ProductionOrder updated = orderRepository.save(existing);
            return new ApiResponse<>("success", "Order updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Order không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteOrder(Integer id) {
        orderRepository.deleteById(id);
        return new ApiResponse<>("success", "Order deleted", null, 200, new Date(), null);
    }
}

