package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.ProductionStep;
import com.stamp_iot_project.service.ProductionStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/steps")
public class ProductionStepController {

    @Autowired
    private ProductionStepService stepService;

    @GetMapping
    public ApiResponse<List<ProductionStep>> getAllSteps() {
        return stepService.getAllSteps();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductionStep> getStepById(@PathVariable Integer id) {
        return stepService.getStepById(id);
    }

    // Khi tạo ProductionStep, cần truyền orderId
    @PostMapping("/{orderId}")
    public ApiResponse<ProductionStep> createStep(@RequestBody ProductionStep step,
                                                  @PathVariable Integer orderId) {
        return stepService.createStep(step, orderId);
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductionStep> updateStep(@PathVariable Integer id,
                                                  @RequestBody ProductionStep step) {
        return stepService.updateStep(id, step);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStep(@PathVariable Integer id) {
        return stepService.deleteStep(id);
    }
}

