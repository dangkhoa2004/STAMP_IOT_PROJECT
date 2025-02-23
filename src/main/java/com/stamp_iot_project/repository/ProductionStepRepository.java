package com.stamp_iot_project.repository;

import com.stamp_iot_project.entity.ProductionStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionStepRepository extends JpaRepository<ProductionStep, Integer> {
}
