package com.stamp_iot_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    // Trạng thái, ví dụ: "success" hoặc "error"
    private String status;
    // Thông điệp chi tiết
    private String message;
    // Dữ liệu trả về (generic)
    private T data;
    // Mã trạng thái (ví dụ HTTP code hoặc code ứng dụng)
    private int code;
    // Thời gian response được tạo
    private Date timestamp;
    // Metadata bổ sung (nếu cần) như thông tin phân trang, v.v.
    private Map<String, Object> meta;
}