package com.stamp_iot_project.repository;

import com.stamp_iot_project.entity.QualityControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityControlRepository extends JpaRepository<QualityControl, Integer> {
}
