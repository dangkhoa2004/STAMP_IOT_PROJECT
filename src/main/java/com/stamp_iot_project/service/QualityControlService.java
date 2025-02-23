package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Employee;
import com.stamp_iot_project.entity.Product;
import com.stamp_iot_project.entity.ProductionOrder;
import com.stamp_iot_project.entity.QualityControl;
import com.stamp_iot_project.repository.EmployeeRepository;
import com.stamp_iot_project.repository.ProductRepository;
import com.stamp_iot_project.repository.ProductionOrderRepository;
import com.stamp_iot_project.repository.QualityControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QualityControlService {

    @Autowired
    private QualityControlRepository qcRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductionOrderRepository orderRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ApiResponse<List<QualityControl>> getAllQualityControls() {
        List<QualityControl> list = qcRepository.findAll();
        return new ApiResponse<>("success", "Fetched quality controls thành công", list, 200, new Date(), null);
    }

    public ApiResponse<QualityControl> getQualityControlById(Integer id) {
        Optional<QualityControl> opt = qcRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched quality control thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Quality control không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<QualityControl> createQualityControl(QualityControl qc, Integer productId, Integer orderId, Integer inspectorId) {
        if (productId != null) {
            productRepository.findById(productId).ifPresent(qc::setProduct);
        }
        if (orderId != null) {
            orderRepository.findById(orderId).ifPresent(qc::setProductionOrder);
        }
        if (inspectorId != null) {
            employeeRepository.findById(inspectorId).ifPresent(qc::setInspector);
        }
        QualityControl saved = qcRepository.save(qc);
        return new ApiResponse<>("success", "Quality control created", saved, 201, new Date(), null);
    }

    public ApiResponse<QualityControl> updateQualityControl(Integer id, QualityControl qc) {
        Optional<QualityControl> opt = qcRepository.findById(id);
        if (opt.isPresent()) {
            QualityControl existing = opt.get();
            existing.setInspectionDate(qc.getInspectionDate());
            existing.setResult(qc.getResult());
            existing.setNotes(qc.getNotes());
            QualityControl updated = qcRepository.save(existing);
            return new ApiResponse<>("success", "Quality control updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Quality control không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteQualityControl(Integer id) {
        qcRepository.deleteById(id);
        return new ApiResponse<>("success", "Quality control deleted", null, 200, new Date(), null);
    }
}
