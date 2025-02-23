package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.ProductionOrder;
import com.stamp_iot_project.entity.ProductionStep;
import com.stamp_iot_project.repository.ProductionOrderRepository;
import com.stamp_iot_project.repository.ProductionStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductionStepService {

    @Autowired
    private ProductionStepRepository stepRepository;
    @Autowired
    private ProductionOrderRepository orderRepository;

    public ApiResponse<List<ProductionStep>> getAllSteps() {
        List<ProductionStep> list = stepRepository.findAll();
        return new ApiResponse<>("success", "Fetched steps thành công", list, 200, new Date(), null);
    }

    public ApiResponse<ProductionStep> getStepById(Integer id) {
        Optional<ProductionStep> opt = stepRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched step thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Step không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<ProductionStep> createStep(ProductionStep step, Integer orderId) {
        Optional<ProductionOrder> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            step.setProductionOrder(orderOpt.get());
            ProductionStep saved = stepRepository.save(step);
            return new ApiResponse<>("success", "Step created", saved, 201, new Date(), null);
        }
        return new ApiResponse<>("error", "Order không tìm thấy", null, 400, new Date(), null);
    }

    public ApiResponse<ProductionStep> updateStep(Integer id, ProductionStep step) {
        Optional<ProductionStep> opt = stepRepository.findById(id);
        if (opt.isPresent()) {
            ProductionStep existing = opt.get();
            existing.setStepNumber(step.getStepNumber());
            existing.setDescription(step.getDescription());
            existing.setStartTime(step.getStartTime());
            existing.setEndTime(step.getEndTime());
            existing.setStatus(step.getStatus());
            ProductionStep updated = stepRepository.save(existing);
            return new ApiResponse<>("success", "Step updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Step không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteStep(Integer id) {
        stepRepository.deleteById(id);
        return new ApiResponse<>("success", "Step deleted", null, 200, new Date(), null);
    }
}

