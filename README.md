**# STAMP_IOT_PROJECT - Hệ thống quản lý thiết bị IoT cho nhà máy sản xuất**

Dự án này cung cấp một giải pháp IoT (Internet of Things) cho nhà máy sản xuất, hỗ trợ quản lý, giám sát và điều khiển các thiết bị trong quy trình sản xuất. Dự án được xây dựng trên nền tảng Spring Boot (Java), áp dụng kiến trúc đa tầng (controller, service, repository, entity, v.v.).

---

## 1. Giới thiệu

- **Mục đích**: Cung cấp nền tảng IoT cho phép người dùng (nhà quản lý, kỹ thuật viên, v.v.) có thể:
  - Quản lý thông tin thiết bị, cập nhật trạng thái và cấu hình.
  - Giám sát thông số hoạt động của thiết bị (nhiệt độ, độ ẩm, v.v.).
  - Tích hợp với các hệ thống khác hoặc giao tiếp qua các giao thức IoT (MQTT, HTTP, WebSocket, v.v.).

- **Ngôn ngữ & Công nghệ**:
  - Java 23 (hoặc phiên bản Java tương đương).
  - Spring Boot.
  - Maven (quản lý và build dự án).
  - JPA/Hibernate (quản lý kết nối và truy vấn cơ sở dữ liệu).
  - Bảo mật với Spring Security (JWT, OAuth2, v.v. – tùy cấu hình).

---

## 2. Cấu trúc thư mục

```
├── src
   ├── main
      ├── java
         └── com
             └── stamp_iot_project
                 ├── configuration
                     ├── JwtFilter.java
                     ├── JwtUtil.java
                     └── SecurityConfig.java
                 ├── controller
                     ├── AuthController.java
                     ├── DepartmentController.java
                     ├── EmployeeController.java
                     ├── EquipmentController.java
                     ├── InventoryController.java
                     ├── InventoryTransactionController.java
                     ├── MaintenanceLogController.java
                     ├── ProductController.java
                     ├── ProductionOrderController.java
                     ├── ProductionStepController.java
                     ├── QualityControlController.java
                     ├── RawMaterialController.java
                     ├── ReportController.java
                     ├── RoleController.java
                     ├── SecurityLogController.java
                     ├── UserController.java
                     ├── UserRoleController.java
                     └── WarehouseController.java
                 ├── dto
                     └── response
                         ├── ApiResponse.java
                         └── AuthResponse.java
                 ├── entity
                     ├── Department.java
                     ├── Employee.java
                     ├── Equipment.java
                     ├── Inventory.java
                     ├── InventoryTransaction.java
                     ├── MaintenanceLog.java
                     ├── Product.java
                     ├── ProductionOrder.java
                     ├── ProductionStep.java
                     ├── QualityControl.java
                     ├── RawMaterial.java
                     ├── Report.java
                     ├── Role.java
                     ├── SecurityLog.java
                     ├── Supplier.java
                     ├── User.java
                     ├── UserRole.java
                     ├── UserRoleId.java
                     └── Warehouse.java
                 ├── repository
                     ├── DepartmentRepository.java
                     ├── EmployeeRepository.java
                     ├── EquipmentRepository.java
                     ├── InventoryRepository.java
                     ├── InventoryTransactionRepository.java
                     ├── MaintenanceLogRepository.java
                     ├── ProductionOrderRepository.java
                     ├── ProductionStepRepository.java
                     ├── ProductRepository.java
                     ├── QualityControlRepository.java
                     ├── RawMaterialRepository.java
                     ├── ReportRepository.java
                     ├── RoleRepository.java
                     ├── SecurityLogRepository.java
                     ├── SupplierRepository.java
                     ├── UserRepository.java
                     ├── UserRoleRepository.java
                     └── WarehouseRepository.java
                 ├── service
                     ├── DepartmentService.java
                     ├── EmployeeService.java
                     ├── EquipmentService.java
                     ├── InventoryService.java
                     ├── InventoryTransactionService.java
                     ├── MaintenanceLogService.java
                     ├── ProductionOrderService.java
                     ├── ProductionStepService.java
                     ├── ProductService.java
                     ├── QualityControlService.java
                     ├── RawMaterialService.java
                     ├── ReportService.java
                     ├── RoleService.java
                     ├── SecurityLogService.java
                     ├── SupplierService.java
                     ├── UserRoleService.java
                     ├── UserService.java
                     └── WarehouseService.java
                 └── StampIotProjectApplication.java
      └── resources
         ├── static
         ├── templates
         ├── application.properties
         └── data.sql
├── test
├── target
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```

- **`controller`**: Chứa các lớp Controller (REST API) xử lý request/response.
- **`dto`**: Data Transfer Object, dùng để truyền dữ liệu giữa các tầng hoặc trao đổi với client.
- **`entity`**: Chứa các lớp ánh xạ (ORM) với bảng trong cơ sở dữ liệu.
- **`repository`**: Chứa các interface/kho lưu trữ (Repository) kết nối tới CSDL (thông qua JPA/Hibernate).
- **`security`**: Cấu hình bảo mật (Spring Security, JWT, v.v.).
- **`service`**: Chứa logic nghiệp vụ, xử lý chính cho ứng dụng.
- **`resources`**: Cấu hình tài nguyên (application.properties/yml, file tĩnh, v.v.).
- **`IotProjectApplication.java`**: Lớp main, điểm khởi chạy của ứng dụng Spring Boot.
- **`test`**: Thư mục chứa các bài test (Unit Test, Integration Test, v.v.).
- **`pom.xml`**: File cấu hình Maven, quản lý dependencies, plugin, v.v.

---

## 3. Yêu cầu hệ thống

- **Java**: Phiên bản 23 (hoặc cao hơn).
- **Maven**: Phiên bản 3.8+ (hoặc sử dụng `mvnw/mvnw.cmd` đi kèm).
- **Cơ sở dữ liệu**: Tùy chọn (MySQL/PostgreSQL/H2, v.v.). Bạn cần cấu hình trong `application.properties` hoặc `application.yml`.

---

## 4. Cài đặt & Chạy dự án

### 4.1 Clone dự án

```bash
git clone https://github.com/dangkhoa2004/STAMP_IOT_PROJECT.git
cd stamp_iot_project
```

### 4.2 Cấu hình

- Mở file `src/main/resources/application.properties` (hoặc `application.yml`) và chỉnh sửa các thông tin cần thiết:
  - **Thông tin kết nối CSDL** (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`, v.v.)
  - **Cấu hình bảo mật** (JWT secret, OAuth2 client info, v.v. nếu có)
  - **Port chạy ứng dụng** (mặc định: `server.port=8080`)

### 4.3 Build & chạy

- Sử dụng Maven cài đặt sẵn trên máy:
  ```bash
  mvn clean install
  mvn spring-boot:run
  ```
- Hoặc sử dụng Maven Wrapper (nếu không có Maven global):
  ```bash
  ./mvnw clean install
  ./mvnw spring-boot:run
  ```

Sau khi chạy thành công, ứng dụng sẽ khởi chạy tại địa chỉ mặc định:
```
http://localhost:8080
```

---

## 5. Cách sử dụng (API cơ bản)

Tùy theo cấu hình thực tế, bạn có thể gọi các API qua đường dẫn `http://localhost:8080/api/...` (ví dụ):

- **Thiết bị (Device)**:
  - `GET /api/devices`: Lấy danh sách thiết bị
  - `POST /api/devices`: Thêm mới thiết bị
  - `GET /api/devices/{id}`: Lấy thông tin chi tiết thiết bị
  - `PUT /api/devices/{id}`: Cập nhật thông tin thiết bị
  - `DELETE /api/devices/{id}`: Xóa thiết bị

- **Người dùng (User)**:
  - `POST /api/auth/register`: Đăng ký tài khoản
  - `POST /api/auth/login`: Đăng nhập, nhận token (JWT)
  - ...

(Chi tiết API tuỳ vào cách bạn thiết kế trong Controller)

---

## 6. Hướng dẫn mở rộng

1. **Thêm module giao tiếp IoT**:  
   - Nếu cần giao tiếp qua MQTT, có thể thêm dependency và cấu hình MQTT Client trong lớp service hoặc tạo thêm package `mqtt`.
   - Nếu cần lưu log hay streaming dữ liệu thời gian thực, cân nhắc tích hợp Kafka, RabbitMQ, v.v.

2. **Tích hợp giao diện web**:
   - Có thể sử dụng React/Angular/Vue kết hợp API này để tạo dashboard giám sát thiết bị.

3. **Đóng gói & triển khai**:
   - Sử dụng Dockerfile & Docker Compose để đóng gói ứng dụng.
   - Triển khai trên các dịch vụ Cloud (AWS, Azure, GCP) hoặc server on-premise.

---

## 7. Đóng góp

- **Pull Request**: Tạo pull request trên repository Git nếu bạn muốn đóng góp chức năng, sửa lỗi, cải thiện hiệu năng, v.v.
- **Issue**: Tạ o issue nếu phát hiện lỗi hoặc có ý tưởng mới.

---

## 8. Thông tin liên hệ

- **Tác giả/Đội ngũ phát triển**: *Tên, email hoặc thông tin liên hệ*
- **License**: (Tùy chọn) GPL, MIT, Apache 2.0, v.v.

---

**Chúc bạn cài đặt và sử dụng dự án thành công!**
