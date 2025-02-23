-- 1. Bảng Product
INSERT INTO Product (name, description, brand, category, specification, price, image_url)
VALUES ('Product A', 'Mô tả sản phẩm A', 'Brand A', 'Category A', 'Spec A', 100.00, 'http://example.com/a.jpg'),
       ('Product B', 'Mô tả sản phẩm B', 'Brand B', 'Category B', 'Spec B', 150.00, 'http://example.com/b.jpg'),
       ('Product C', 'Mô tả sản phẩm C', 'Brand C', 'Category C', 'Spec C', 200.00, 'http://example.com/c.jpg');

-- 2. Bảng Warehouse
INSERT INTO Warehouse (location, capacity, description)
VALUES ('Location A', 1000, 'Mô tả kho A'),
       ('Location B', 2000, 'Mô tả kho B'),
       ('Location C', 1500, 'Mô tả kho C');

-- 3. Bảng Inventory (tham chiếu product_id và warehouse_id)
INSERT INTO Inventory (product_id, warehouse_id, quantity)
VALUES (1, 1, 50),
       (2, 2, 30),
       (3, 3, 20);

-- 4. Bảng InventoryTransaction (tham chiếu inventory_id)
INSERT INTO Inventory_Transaction (inventory_id, transaction_type, quantity, transaction_date, description)
VALUES (1, 'inbound', 10, "2025-01-01", 'Nhập kho ban đầu'),
       (2, 'outbound', 5, "2025-01-01", 'Xuất kho giao hàng'),
       (3, 'inbound', 15, "2025-01-01", 'Nhập thêm hàng');

-- 5. Bảng ProductionOrder (tham chiếu product_id)
INSERT INTO Production_Order (product_id, quantity, start_date, end_date, status)
VALUES (1, 100, '2025-01-01', '2025-01-10', 'Completed'),
       (2, 200, '2025-02-01', '2025-02-15', 'In Progress'),
       (3, 150, '2025-03-01', '2025-03-10', 'Pending');

-- 6. Bảng ProductionStep (tham chiếu order_id)
INSERT INTO Production_Step (order_id, step_number, description, start_time, end_time, status)
VALUES (1, 1, 'Trộn nguyên liệu', '2025-01-01 08:00:00', '2025-01-01 10:00:00', 'Completed'),
       (2, 1, 'Lắp ráp thành phẩm', '2025-02-01 09:00:00', '2025-02-01 11:00:00', 'In Progress'),
       (3, 1, 'Kiểm tra chất lượng', '2025-03-01 07:00:00', '2025-03-01 09:00:00', 'Pending');

-- 7. Bảng Department
INSERT INTO Department (name, description)
VALUES ('Sales', 'Phòng kinh doanh'),
       ('Production', 'Phòng sản xuất'),
       ('Maintenance', 'Phòng bảo trì');

-- 8. Bảng Employee (tham chiếu department_id)
INSERT INTO Employee (name, department_id, contact_info, hire_date, salary)
VALUES ('Alice', 1, 'alice@example.com', '2020-05-10', 50000.00),
       ('Bob', 2, 'bob@example.com', '2019-08-15', 60000.00),
       ('Charlie', 3, 'charlie@example.com', '2021-01-20', 55000.00);

-- 9. Bảng Equipment (tham chiếu department_id)
INSERT INTO Equipment (name, type, department_id, purchase_date, maintenance_schedule, status)
VALUES ('Printer A', 'Printer', 1, '2023-01-15', 'Monthly', 'Active'),
       ('Conveyor Belt', 'Machine', 2, '2022-06-20', 'Quarterly', 'Active'),
       ('Forklift', 'Vehicle', 3, '2021-09-10', 'Yearly', 'Active');

-- 10. Bảng Supplier
INSERT INTO Supplier (name, contact_info)
VALUES ('Supplier A', 'contactA@example.com'),
       ('Supplier B', 'contactB@example.com'),
       ('Supplier C', 'contactC@example.com');

-- 11. Bảng RawMaterial (tham chiếu supplier_id)
INSERT INTO Raw_Material (name, description, supplier_id, unit, current_stock, reorder_level)
VALUES ('Material A', 'Nguyên liệu A chất lượng cao', 1, 'kg', 500, 100),
       ('Material B', 'Nguyên liệu B chất lượng cao', 2, 'liters', 300, 50),
       ('Material C', 'Nguyên liệu C chất lượng cao', 3, 'pcs', 1000, 200);

-- 12. Bảng QualityControl (tham chiếu product_id, production_order_id, inspector_id)
INSERT INTO Quality_Control (product_id, production_order_id, inspection_date, inspector_id, result, notes)
VALUES (1, 1, '2025-01-11', 1, 'Pass', 'Mọi thứ ổn định'),
       (2, 2, '2025-02-16', 2, 'Fail', 'Phát hiện lỗi lắp ráp'),
       (3, 3, '2025-03-11', 3, 'Pass', 'Đạt chuẩn chất lượng');

-- 13. Bảng SecurityLog (tham chiếu employee_id)
INSERT INTO Security_Log (event_type, event_date, description, employee_id, location)
VALUES ('Login', '2025-01-05 08:00:00', 'Đăng nhập hệ thống', 1, 'Văn phòng chính'),
       ('Logout', '2025-01-05 17:00:00', 'Đăng xuất hệ thống', 2, 'Văn phòng chính'),
       ('Access', '2025-01-06 09:30:00', 'Truy cập khu vực bảo mật', 3, 'Kho hàng');

-- 14. Bảng MaintenanceLog (tham chiếu equipment_id và performed_by)
INSERT INTO Maintenance_Log (equipment_id, maintenance_date, maintenance_type, performed_by, maintenance_description,
                             cost, next_scheduled_date, status)
VALUES (1, '2025-01-10', 'Kiểm tra định kỳ', 1, 'Kiểm tra hiệu suất máy in', 100.00, '2025-02-10', 'Completed'),
       (2, '2025-02-15', 'Sửa chữa', 2, 'Sửa chữa động cơ băng chuyền', 300.00, '2025-05-15', 'Completed'),
       (3, '2025-03-20', 'Bảo dưỡng', 3, 'Bảo dưỡng xe nâng', 150.00, '2025-09-20', 'Scheduled');

-- 15. Bảng User (tham chiếu employee_id)
INSERT INTO `User` (username, password, email, employee_id, status, created_at, updated_at)
VALUES ('user1', 'password1', 'user1@example.com', 1, 'Active', NOW(), NOW()),
       ('user2', 'password2', 'user2@example.com', 2, 'Active', NOW(), NOW()),
       ('user3', 'password3', 'user3@example.com', 3, 'Active', NOW(), NOW());

-- 16. Bảng Role
INSERT INTO Role (role_name, description)
VALUES ('Admin', 'Quyền quản trị viên'),
       ('Manager', 'Quyền quản lý'),
       ('Staff', 'Quyền nhân viên');

-- 17. Bảng UserRole (tham chiếu user_id và role_id)
INSERT INTO User_Role (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

-- 18. Bảng Report (tham chiếu created_by từ bảng User)
INSERT INTO Report (report_name, report_type, created_by, parameters, summary, file_url)
VALUES ('Sales Report', 'Monthly', 1, '{"month":"January"}', 'Báo cáo bán hàng tháng',
        'http://example.com/report1.pdf'),
       ('Production Report', 'Weekly', 2, '{"week":"12"}', 'Báo cáo sản xuất tuần', 'http://example.com/report2.pdf'),
       ('Maintenance Report', 'Annual', 3, '{"year":"2024"}', 'Báo cáo bảo trì năm', 'http://example.com/report3.pdf');
