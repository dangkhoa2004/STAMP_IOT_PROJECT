-- 1. Bảng Product
CREATE TABLE Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    brand VARCHAR(100),
    category VARCHAR(100),
    specification TEXT,
    price DECIMAL(10,2),
    image_url VARCHAR(255)
) ENGINE=InnoDB;

-- 2. Bảng Warehouse
CREATE TABLE Warehouse (
    warehouse_id INT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    capacity INT,
    description TEXT
) ENGINE=InnoDB;

-- 3. Bảng Inventory
CREATE TABLE Inventory (
    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    warehouse_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    last_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES Product(product_id),
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse(warehouse_id)
) ENGINE=InnoDB;

-- 4. Bảng InventoryTransaction
CREATE TABLE InventoryTransaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    inventory_id INT NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,  -- ví dụ: 'inbound', 'outbound'
    quantity INT NOT NULL,
    transaction_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    FOREIGN KEY (inventory_id) REFERENCES Inventory(inventory_id)
) ENGINE=InnoDB;

-- 5. Bảng ProductionOrder
CREATE TABLE ProductionOrder (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    start_date DATE,
    end_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
) ENGINE=InnoDB;

-- 6. Bảng ProductionStep
CREATE TABLE ProductionStep (
    step_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    step_number INT NOT NULL,
    description TEXT,
    start_time DATETIME,
    end_time DATETIME,
    status VARCHAR(50),
    FOREIGN KEY (order_id) REFERENCES ProductionOrder(order_id)
) ENGINE=InnoDB;

-- 7. Bảng Department
CREATE TABLE Department (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
) ENGINE=InnoDB;

-- 8. Bảng Employee
CREATE TABLE Employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_id INT,  -- liên kết với bảng Department
    contact_info VARCHAR(255),
    hire_date DATE,
    salary DECIMAL(10,2),
    FOREIGN KEY (department_id) REFERENCES Department(department_id)
) ENGINE=InnoDB;

-- 9. Bảng Equipment
CREATE TABLE Equipment (
    equipment_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100),
    department_id INT,  -- thiết bị thuộc phòng ban nào
    purchase_date DATE,
    maintenance_schedule VARCHAR(255),
    status VARCHAR(50),
    FOREIGN KEY (department_id) REFERENCES Department(department_id)
) ENGINE=InnoDB;

-- 10. Bảng Supplier
CREATE TABLE Supplier (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255)
) ENGINE=InnoDB;

-- 11. Bảng RawMaterial
CREATE TABLE RawMaterial (
    material_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    supplier_id INT,
    unit VARCHAR(50),
    current_stock INT DEFAULT 0,
    reorder_level INT,
    FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id)
) ENGINE=InnoDB;

-- 12. Bảng QualityControl
CREATE TABLE QualityControl (
    qc_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT DEFAULT NULL,
    production_order_id INT DEFAULT NULL,
    inspection_date DATE NOT NULL,
    inspector_id INT NOT NULL,
    result VARCHAR(50),
    notes TEXT,
    FOREIGN KEY (product_id) REFERENCES Product(product_id),
    FOREIGN KEY (production_order_id) REFERENCES ProductionOrder(order_id),
    FOREIGN KEY (inspector_id) REFERENCES Employee(employee_id)
) ENGINE=InnoDB;

-- 13. Bảng SecurityLog
CREATE TABLE SecurityLog (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    event_type VARCHAR(100),
    event_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    employee_id INT,
    location VARCHAR(255),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
) ENGINE=InnoDB;

-- 14. Bảng MaintenanceLog
CREATE TABLE MaintenanceLog (
    maintenance_id INT AUTO_INCREMENT PRIMARY KEY,
    equipment_id INT NOT NULL,
    maintenance_date DATE NOT NULL,
    maintenance_type VARCHAR(100),
    performed_by INT,  -- nhân viên thực hiện bảo trì
    maintenance_description TEXT,
    cost DECIMAL(10,2),
    next_scheduled_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (equipment_id) REFERENCES Equipment(equipment_id),
    FOREIGN KEY (performed_by) REFERENCES Employee(employee_id)
) ENGINE=InnoDB;

-- 15. Bảng User (chú ý: "User" là từ khóa nên được đặt trong dấu ``)
CREATE TABLE `User` (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    employee_id INT,
    status VARCHAR(50) DEFAULT 'Active',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
) ENGINE=InnoDB;

-- 16. Bảng Role
CREATE TABLE Role (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    description TEXT
) ENGINE=InnoDB;

-- 17. Bảng UserRole (bảng trung gian cho mối quan hệ nhiều - nhiều giữa User và Role)
CREATE TABLE UserRole (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES `User`(user_id),
    FOREIGN KEY (role_id) REFERENCES Role(role_id)
) ENGINE=InnoDB;

-- 18. Bảng Report
CREATE TABLE Report (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    report_name VARCHAR(255) NOT NULL,
    report_type VARCHAR(100),
    created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_by INT,
    parameters TEXT,  -- lưu trữ tham số, có thể dùng định dạng JSON
    summary TEXT,
    file_url VARCHAR(255),
    FOREIGN KEY (created_by) REFERENCES `User`(user_id)
) ENGINE=InnoDB;
