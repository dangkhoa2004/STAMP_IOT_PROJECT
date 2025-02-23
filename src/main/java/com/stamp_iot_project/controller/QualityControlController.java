package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.QualityControl;
import com.stamp_iot_project.service.QualityControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quality-controls")
public class QualityControlController {

    @Autowired
    private QualityControlService qcService;

    @GetMapping
    public ApiResponse<List<QualityControl>> getAllQualityControls() {
        return qcService.getAllQualityControls();
    }

    @GetMapping("/{id}")
    public ApiResponse<QualityControl> getQualityControlById(@PathVariable Integer id) {
        return qcService.getQualityControlById(id);
    }

    // Khi tạo QualityControl, truyền productId, orderId và inspectorId nếu có
    @PostMapping("/{productId}/{orderId}/{inspectorId}")
    public ApiResponse<QualityControl> createQualityControl(@RequestBody QualityControl qc,
                                                            @PathVariable(required = false) Integer productId,
                                                            @PathVariable(required = false) Integer orderId,
                                                            @PathVariable(required = false) Integer inspectorId) {
        return qcService.createQualityControl(qc, productId, orderId, inspectorId);
    }

    @PutMapping("/{id}")
    public ApiResponse<QualityControl> updateQualityControl(@PathVariable Integer id,
                                                            @RequestBody QualityControl qc) {
        return qcService.updateQualityControl(id, qc);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteQualityControl(@PathVariable Integer id) {
        return qcService.deleteQualityControl(id);
    }
}
