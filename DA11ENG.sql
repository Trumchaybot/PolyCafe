create database DUAN1ENG
go
use DUAN1ENG
go

-- ==============================
-- BẢNG ROLE
-- ==============================
CREATE TABLE Role (
    role_id INT PRIMARY KEY IDENTITY(1,1),
    role NVARCHAR(50) NOT NULL,
    role_name NVARCHAR(50) NOT NULL,
    hourly_salary FLOAT
);
GO

-- ==============================
-- BẢNG MATERIALS (Nguyên vật liệu)
-- ==============================
CREATE TABLE Materials (
    material_id INT PRIMARY KEY IDENTITY(1,1),
    material_name NVARCHAR(100),
    unit NVARCHAR(20),
    unit_price FLOAT,
    quantity INT
);
GO

-- ==============================
-- BẢNG PRODUCTS (Sản phẩm)
-- ==============================
CREATE TABLE Products (
    product_id INT PRIMARY KEY IDENTITY(1,1),
    product_name NVARCHAR(100),
    description NVARCHAR(255),
    unit_price FLOAT,
    target INT,
    basic_salary FLOAT,
    target_salary FLOAT,
    quantity INT
);
GO

-- ==============================
-- BẢNG SUPPLIERS (Nhà cung cấp)
-- ==============================
CREATE TABLE Suppliers (
    supplier_id INT PRIMARY KEY IDENTITY(1,1),
    supplier_name NVARCHAR(100),
    PhoneNumber VARCHAR(15),
    email VARCHAR(100),
    address NVARCHAR(255)
);
GO

-- ==============================
-- BẢNG PRODUCTION STAGES (Khâu sản xuất)
-- ==============================
CREATE TABLE ProductionStages (
    productionStages_id INT PRIMARY KEY IDENTITY(1,1),
    productionStages_name NVARCHAR(100)
);
GO

-- ==============================
-- BẢNG EMPLOYEE INFO (Thông tin nhân viên)
-- ==============================
CREATE TABLE EmployeeInfo (
    UserName VARCHAR(50) PRIMARY KEY,
    PassWord VARCHAR(100),
    FullName NVARCHAR(100),
    Gender NVARCHAR(5),
    NationalID VARCHAR(20) UNIQUE,
    BirthDate DATE,
    Address NVARCHAR(255),
    PhoneNumber VARCHAR(15),
    Email VARCHAR(100) UNIQUE,
    role_id INT FOREIGN KEY REFERENCES Role(role_id),
    Photo VARCHAR(255),
    Status NVARCHAR(50)
);
GO
ALTER TABLE EmployeeInfo
ADD CONSTRAINT UQ_EmployeeInfo_Email UNIQUE (Email);

-- ==============================
-- BẢNG OTP (Mã OTP)
-- ==============================
CREATE TABLE OTP (
    id_OTP INT PRIMARY KEY IDENTITY(1,1),
    UserName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    OTP_Code CHAR(6) NOT NULL,
    CreatedAt DATETIME DEFAULT GETDATE(),
    ExpiredAt DATETIME NULL,
    CONSTRAINT FK_OTP_UserName FOREIGN KEY (UserName) REFERENCES EmployeeInfo(UserName),
    CONSTRAINT FK_OTP_Email FOREIGN KEY (Email) REFERENCES EmployeeInfo(Email)
);



-- ==============================
-- BẢNG AREA (Khu vực)
-- ==============================
CREATE TABLE Areas (
    area_id INT PRIMARY KEY IDENTITY(1,1),
    area_name NVARCHAR(100),
    description NVARCHAR(255),
    status NVARCHAR(255)
);
GO

-- ==============================
-- BẢNG AREA DETAILS (Chi tiết khu vực)
-- ==============================
CREATE TABLE AreaDetails (
    id INT PRIMARY KEY IDENTITY(1,1),
    area_id INT FOREIGN KEY REFERENCES Areas(area_id),
    UserName VARCHAR(50) FOREIGN KEY REFERENCES EmployeeInfo(UserName),
    role_id INT FOREIGN KEY REFERENCES Role(role_id),
    productionStages_id INT FOREIGN KEY REFERENCES ProductionStages(productionStages_id),
    assign_date DATE
);
GO

-- ==============================
-- BẢNG PRODUCT DETAILS (Chi tiết sản phẩm)
-- ==============================
CREATE TABLE ProductDetails (
    id INT PRIMARY KEY IDENTITY(1,1),
    product_id INT FOREIGN KEY REFERENCES Products(product_id),
    material_id INT FOREIGN KEY REFERENCES Materials(material_id),
    quantity INT,

);
GO

-- ==============================
-- BẢNG CUSTOMERS (Khách hàng)
-- ==============================
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY IDENTITY(1,1),
    customer_name NVARCHAR(100),
    birthdate DATE,
    PhoneNumber VARCHAR(15),
    email VARCHAR(100)
);
GO

-- ==============================
-- BẢNG ORDERS (Đơn hàng)
-- ==============================
CREATE TABLE Orders (
    order_id INT PRIMARY KEY IDENTITY(1,1),
    customer_id INT FOREIGN KEY REFERENCES Customers(customer_id),
    address NVARCHAR(255),
    total_amount FLOAT,
    order_date DATE,
    delivery_date DATE NULL,
    status NVARCHAR(50)
);
GO

-- ==============================
-- BẢNG ORDER DETAILS (Chi tiết đơn hàng)
-- ==============================
CREATE TABLE OrderDetails (
    id INT PRIMARY KEY IDENTITY(1,1),
    order_id INT FOREIGN KEY REFERENCES Orders(order_id),
    product_id INT FOREIGN KEY REFERENCES Products(product_id),
    quantity INT,
    total_amount FLOAT
);
GO

-- ==============================
-- BẢNG IMPORT (Nhập kho)
-- ==============================
CREATE TABLE Import (
    import_id INT PRIMARY KEY IDENTITY(1,1),
    UserName VARCHAR(50) FOREIGN KEY REFERENCES EmployeeInfo(UserName),
    supplier_id INT FOREIGN KEY REFERENCES Suppliers(supplier_id),
    total_amount FLOAT,
    import_date DATE
);
GO

-- ==============================
-- BẢNG IMPORT DETAILS (Chi tiết nhập kho)
-- ==============================
CREATE TABLE ImportDetails (
    id INT PRIMARY KEY IDENTITY(1,1),
    import_id INT FOREIGN KEY REFERENCES Import(import_id),
    material_id INT FOREIGN KEY REFERENCES Materials(material_id),
    quantity INT,
    total_amount FLOAT
);
GO

-- ==============================
-- BẢNG EXPORT (Xuất kho)
-- ==============================
CREATE TABLE Export (
    export_id INT PRIMARY KEY IDENTITY(1,1),
    UserName VARCHAR(50) FOREIGN KEY REFERENCES EmployeeInfo(UserName),
    total_amount FLOAT,
    export_date DATE
);
GO

-- ==============================
-- BẢNG EXPORT DETAILS (Chi tiết xuất kho)
-- ==============================
CREATE TABLE ExportDetails (
    id INT PRIMARY KEY IDENTITY(1,1),
    export_id INT FOREIGN KEY REFERENCES Export(export_id),
    material_id INT FOREIGN KEY REFERENCES Materials(material_id),
    quantity INT,
    total_amount FLOAT
);
GO

-- ==============================
-- BẢNG ATTENDANCE (Chấm công)
-- ==============================
CREATE TABLE Attendance (
    id INT PRIMARY KEY IDENTITY(1,1),
    UserName VARCHAR(50) FOREIGN KEY REFERENCES EmployeeInfo(UserName),
    work_date DATE,
    checkin_time TIME NULL,
    checkout_time TIME,
    total_hours FLOAT NULL
);
GO

-- ==============================
-- BẢNG SALARY (Lương)
-- ==============================
CREATE TABLE Salary (
    id INT PRIMARY KEY IDENTITY(1,1),
    UserName VARCHAR(50) FOREIGN KEY REFERENCES EmployeeInfo(UserName),
    month INT,
    year INT,
    total_hours FLOAT,
    total_products INT,
    bonus FLOAT,
    total_salary FLOAT
);
GO

-- ==============================
-- BẢNG PRODUCTIVITY (Sản lượng)
-- ==============================
CREATE TABLE Productivity (
    id INT PRIMARY KEY IDENTITY(1,1),
    UserName VARCHAR(50) FOREIGN KEY REFERENCES EmployeeInfo(UserName),
    product_id INT FOREIGN KEY REFERENCES Products(product_id),
    quantity INT,
    work_date DATE
);
GO

-- ==============================
-- BẢNG RECRUITMENT (Tuyển dụng)
-- ==============================
CREATE TABLE Recruitment (
    recruitment_id INT PRIMARY KEY IDENTITY(1,1),
    position NVARCHAR(100),
    description NVARCHAR(255),
    requirement NVARCHAR(255),
    quantity INT,
    start_date DATE,
    end_date DATE,
    status NVARCHAR(50),
    UserName VARCHAR(50) FOREIGN KEY REFERENCES EmployeeInfo(UserName)
);
GO

-- ==============================
-- BẢNG APPLICATIONS (Ứng tuyển)
-- ==============================
CREATE TABLE Applications (
    id INT PRIMARY KEY IDENTITY(1,1),
    recruitment_id INT FOREIGN KEY REFERENCES Recruitment(recruitment_id),
    full_name NVARCHAR(100),
    birthdate DATE,
    PhoneNumber VARCHAR(15),
    email VARCHAR(100),
    applied_position NVARCHAR(100),
    application_date DATE,
    status NVARCHAR(50)
);
GO

-- ==============================
-- BẢNG TOTAL REVENUE (Tổng thu)
-- ==============================
CREATE TABLE TotalRevenue (
    id INT PRIMARY KEY IDENTITY(1,1),
    month INT,
    year INT,
    order_income DECIMAL(18,2),
    export_income DECIMAL(18,2),
    total_income DECIMAL(18,2)
);
GO

-- ==============================
-- BẢNG TOTAL EXPENSES (Tổng chi)
-- ==============================
CREATE TABLE TotalExpenses (
    id INT PRIMARY KEY IDENTITY(1,1),
    month INT,
    year INT,
    salary_expense DECIMAL(18,2),
    import_expense DECIMAL(18,2),
    total_expense DECIMAL(18,2)
);
GO

-- ==============================
-- DỮ LIỆU MẪU 
-- ==============================

-- ==============================
-- ROLE
-- ==============================
INSERT INTO Role (role, role_name, hourly_salary)
VALUES 
(N'Admin', N'Quản trị viên', 50),
(N'Manager', N'Quản lý', 40),
(N'Worker', N'Công nhân', 25);
GO

-- ==============================
-- EMPLOYEE INFO
-- ==============================
INSERT INTO EmployeeInfo (UserName, PassWord, FullName, Gender, NationalID, BirthDate, Address, PhoneNumber, Email, role_id, Photo, Status)
VALUES
('admin01', '123456', N'Nguyễn Văn A', N'Nam', '0123456789', '1990-05-10', N'Hà Nội', '0901234567', 'admin@gmail.com', 1, 'admin.jpg', N'Active'),
('manager01', '123456', N'Trần Thị B', N'Nữ', '9876543210', '1985-03-15', N'Hồ Chí Minh', '0912345678', 'manager@gmail.com', 2, 'manager.jpg', N'Active'),
('worker01', '123456', N'Phạm Văn C', N'Nam', '4567891230', '1995-08-20', N'Đà Nẵng', '0934567890', 'worker@gmail.com', 3, 'worker.jpg', N'Active');
GO

-- ==============================
-- MATERIALS
-- ==============================
INSERT INTO Materials (material_name, unit, unit_price, quantity)
VALUES
(N'Vải Cotton', N'Mét', 50, 1000),
(N'Chỉ May', N'Cuộn', 10, 500),
(N'Nút áo', N'Cái', 2, 2000);
GO

-- ==============================
-- PRODUCTS
-- ==============================
INSERT INTO Products (product_name, description, unit_price, target, basic_salary, target_salary, quantity)
VALUES
(N'Áo Thun', N'Áo thun cotton chất lượng cao', 150, 100, 3000, 4000, 200),
(N'Quần Jean', N'Quần jean nam nữ', 300, 80, 3500, 4500, 150),
(N'Áo Sơ Mi', N'Áo sơ mi công sở', 200, 120, 2800, 3800, 180);
GO

-- ==============================
-- SUPPLIERS
-- ==============================
INSERT INTO Suppliers (supplier_name, PhoneNumber, email, address)
VALUES
(N'Công ty Vải Hà Nội', '0988001122', 'vaihanoi@gmail.com', N'Hà Nội'),
(N'Công ty May Sài Gòn', '0977554433', 'maysaigon@gmail.com', N'Hồ Chí Minh');
GO

-- ==============================
-- PRODUCTION STAGES
-- ==============================
INSERT INTO ProductionStages (productionStages_name)
VALUES
(N'Cắt vải'),
(N'May thân'),
(N'Hoàn thiện');
GO

-- ==============================
-- AREAS
-- ==============================
INSERT INTO Areas (area_name, description, status)
VALUES
(N'Xưởng 1', N'Chuyên may áo thun', N'Hoạt động'),
(N'Xưởng 2', N'Chuyên may quần jean', N'Hoạt động');
GO

-- ==============================
-- AREA DETAILS
-- ==============================
INSERT INTO AreaDetails (area_id, UserName, role_id, productionStages_id, assign_date)
VALUES
(1, 'worker01', 3, 1, '2025-08-01');
GO

-- ==============================
-- CUSTOMERS
-- ==============================
INSERT INTO Customers (customer_name, birthdate, PhoneNumber, email)
VALUES
(N'Lê Văn D', '1992-07-12', '0945671234', 'leved@gmail.com'),
(N'Nguyễn Thị E', '1998-11-25', '0923456789', 'nguyenthe@gmail.com');
GO

-- ==============================
-- ORDERS
-- ==============================
INSERT INTO Orders (customer_id, address, total_amount, order_date, delivery_date, status)
VALUES
(1, N'Hà Nội', 3000, '2025-08-10', '2025-08-15', N'Đã giao'),
(2, N'Hồ Chí Minh', 4500, '2025-08-12', '2025-08-18', N'Chưa giao');
GO

-- ==============================
-- ORDER DETAILS
-- ==============================
INSERT INTO OrderDetails (order_id, product_id, quantity, total_amount)
VALUES
(1, 1, 10, 1500),
(1, 3, 5, 1000),
(2, 2, 10, 3000);
GO

-- ==============================
-- IMPORT (NHẬP KHO)
-- ==============================
INSERT INTO Import (UserName, supplier_id, total_amount, import_date)
VALUES
('manager01', 1, 5000, '2025-08-05');
GO

-- ==============================
-- IMPORT DETAILS
-- ==============================
INSERT INTO ImportDetails (import_id, material_id, quantity, total_amount)
VALUES
(1, 1, 100, 5000);
GO

-- ==============================
-- EXPORT (XUẤT KHO)
-- ==============================
INSERT INTO Export (UserName, total_amount, export_date)
VALUES
('manager01', 2000, '2025-08-07');
GO

-- ==============================
-- EXPORT DETAILS
-- ==============================
INSERT INTO ExportDetails (export_id, material_id, quantity, total_amount)
VALUES
(1, 2, 100, 1000);
GO

-- ==============================
-- ATTENDANCE
-- ==============================
INSERT INTO Attendance (UserName, work_date, checkin_time, checkout_time, total_hours)
VALUES
('worker01', '2025-08-10', '08:00:00', '17:00:00', 8);
GO 

-- ==============================
-- SALARY
-- ==============================
INSERT INTO Salary (UserName, month, year, total_hours, total_products, bonus, total_salary)
VALUES
('worker01', 8, 2025, 160, 200, 500, 8000);
GO

-- ==============================
-- PRODUCTIVITY
-- ==============================
INSERT INTO Productivity (UserName, product_id, quantity, work_date)
VALUES
('worker01', 1, 20, '2025-08-10');
GO

-- ==============================
-- RECRUITMENT
-- ==============================
INSERT INTO Recruitment (position, description, requirement, quantity, start_date, end_date, status, UserName)
VALUES
(N'Công nhân may', N'Tuyển công nhân may áo thun', N'Biết may cơ bản', 10, '2025-08-01', '2025-09-01', N'Đang tuyển', 'manager01');
GO

-- ==============================
-- APPLICATIONS
-- ==============================
INSERT INTO Applications (recruitment_id, full_name, birthdate, PhoneNumber, email, applied_position, application_date, status)
VALUES
(1, N'Hoàng Văn F', '2000-02-20', '0967889900', 'hvf@gmail.com', N'Công nhân may', '2025-08-20', N'Chờ duyệt');
GO

-- ==============================
-- TOTAL REVENUE
-- ==============================
INSERT INTO TotalRevenue (month, year, order_income, export_income, total_income)
VALUES
(8, 2025, 5000, 2000, 7000);
GO

-- ==============================
-- TOTAL EXPENSES
-- ==============================
INSERT INTO TotalExpenses (month, year, salary_expense, import_expense, total_expense)
VALUES
(8, 2025, 8000, 5000, 13000);
GO

-------------------------------------------------
--					VIEW 					   --
-------------------------------------------------
CREATE VIEW v_OrderDetails AS
SELECT 
    od.id,
    od.order_id,
    od.product_id,
    od.quantity,
    od.total_amount,
    p.product_name
FROM OrderDetails od
JOIN Products p ON od.product_id = p.product_id;
GO

-------------------------------------------------
CREATE OR ALTER VIEW v_AreaDetails AS
SELECT 
    ad.id,
    ad.area_id,
    ad.UserName,
    ad.role_id,
    r.role_name, -- thêm từ bảng Role
    ad.productionStages_id,
    ps.productionStages_Name,
    ad.assign_date
FROM AreaDetails ad
JOIN Role r ON ad.role_id = r.role_id
JOIN ProductionStages ps ON ad.productionStages_id = ps.productionStages_id;
GO

-------------------------------------------------
CREATE VIEW v_ImportDetails AS
SELECT 
    idt.id,
    idt.import_id,
    idt.material_id,
    idt.quantity,
    idt.total_amount,
    m.material_name
FROM ImportDetails idt
JOIN Materials m ON idt.material_id = m.material_id;
GO

-------------------------------------------------
CREATE VIEW v_ExportDetails AS
SELECT 
    edt.id,
    edt.export_id,
    edt.material_id,
    edt.quantity,
    edt.total_amount,
    m.material_name
FROM ExportDetails edt
JOIN Materials m ON edt.material_id = m.material_id;
GO

-------------------------------------------------
CREATE VIEW v_Orders AS
SELECT 
    o.order_id,
    o.customer_id,
    o.address,
    o.total_amount,
    o.order_date,
    o.delivery_date,
    o.status,
    c.customer_name,
    c.email,
    c.PhoneNumber,
    c.birthdate
FROM Orders o
JOIN Customers c ON o.customer_id = c.customer_id;
GO

-------------------------------------------------
CREATE VIEW v_Import AS
SELECT 
    i.import_id,
    i.UserName,
    i.total_amount,
    i.import_date,
    s.supplier_id,
    s.supplier_name,
    s.PhoneNumber,
    s.email,
    s.address
FROM Import i
LEFT JOIN Suppliers s ON i.supplier_id = s.supplier_id;
GO

-------------------------------------------------
CREATE VIEW v_EmployeeInfo AS
SELECT 
      e.UserName,
      e.PassWord,
      e.FullName,
      e.Gender,
      e.NationalID,
      e.BirthDate,
      e.Address,
      e.PhoneNumber,
      e.Email,
      e.role_id,
      r.role_name,
      e.Photo,
      e.Status
FROM EmployeeInfo e
LEFT JOIN Role r ON e.role_id = r.role_id;
GO

-------------------------------------------------
--					TRIGGER					   --
-------------------------------------------------

-------------------------------------------------
-- 1. Cập nhật số lượng vật liệu khi nhập kho
-------------------------------------------------
CREATE OR ALTER TRIGGER trg_ImportDetails_Insert
ON ImportDetails
AFTER INSERT
AS
BEGIN
    UPDATE m
    SET m.quantity = m.quantity + i.quantity
    FROM Materials m
    JOIN inserted i ON m.material_id = i.material_id;
END;
GO

-------------------------------------------------
-- 2. Trừ số lượng vật liệu khi xuất kho
-------------------------------------------------
CREATE OR ALTER TRIGGER trg_ExportDetails_Insert
ON ExportDetails
AFTER INSERT
AS
BEGIN
    UPDATE m
    SET m.quantity = m.quantity - i.quantity
    FROM Materials m
    JOIN inserted i ON m.material_id = i.material_id;
END;
GO

-------------------------------------------------
-- 3. Cập nhật số lượng sản phẩm khi có đơn hàng
-------------------------------------------------
CREATE OR ALTER TRIGGER trg_OrderDetails_Insert
ON OrderDetails
AFTER INSERT
AS
BEGIN
    UPDATE p
    SET p.quantity = p.quantity - i.quantity
    FROM Products p
    JOIN inserted i ON p.product_id = i.product_id;
END;
GO

-------------------------------------------------
-- 4. Hoàn trả sản phẩm khi xoá chi tiết đơn hàng
-------------------------------------------------
CREATE OR ALTER TRIGGER trg_OrderDetails_Delete
ON OrderDetails
AFTER DELETE
AS
BEGIN
    UPDATE p
    SET p.quantity = p.quantity + d.quantity
    FROM Products p
    JOIN deleted d ON p.product_id = d.product_id;
END;
GO

-------------------------------------------------
-- 5. Tính lại tổng tiền của Order khi có thay đổi
-------------------------------------------------
CREATE OR ALTER TRIGGER trg_UpdateOrderTotal
ON OrderDetails
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @OrderIDs TABLE (order_id INT);

    INSERT INTO @OrderIDs (order_id)
    SELECT DISTINCT order_id FROM inserted;

    INSERT INTO @OrderIDs (order_id)
    SELECT DISTINCT order_id FROM deleted;

    UPDATE o
    SET o.total_amount = ISNULL((
        SELECT SUM(od.total_amount)
        FROM OrderDetails od
        WHERE od.order_id = o.order_id
    ), 0)
    FROM Orders o
    JOIN @OrderIDs d ON o.order_id = d.order_id;
END;
GO

-------------------------------------------------
-- 6. Cập nhật lại tổng tiền chi tiết đơn hàng
-------------------------------------------------
CREATE OR ALTER TRIGGER trg_OrderDetails_UpdateTotal
ON OrderDetails
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE od
    SET od.total_amount = od.quantity * p.unit_price
    FROM OrderDetails od
    JOIN inserted i ON od.id = i.id
    JOIN Products p ON od.product_id = p.product_id;
END;
GO

-------------------------------------------------
-- 7. Cập nhật Salary khi có Attendance mới
-------------------------------------------------
CREATE OR ALTER TRIGGER trg_Attendance_Insert
ON Attendance
AFTER INSERT
AS
BEGIN
    DECLARE @user VARCHAR(50), @month INT, @year INT;
    SELECT TOP 1 
        @user = UserName,
        @month = MONTH(work_date),
        @year = YEAR(work_date)
    FROM inserted;

    DECLARE @total_hours FLOAT = ISNULL((
        SELECT SUM(total_hours) 
        FROM Attendance 
        WHERE UserName = @user 
          AND MONTH(work_date) = @month 
          AND YEAR(work_date) = @year
    ), 0);

    IF EXISTS (SELECT 1 FROM Salary WHERE UserName = @user AND month = @month AND year = @year)
    BEGIN
        UPDATE Salary
        SET total_hours = @total_hours
        WHERE UserName = @user AND month = @month AND year = @year;
    END
    ELSE
    BEGIN
        INSERT INTO Salary (UserName, month, year, total_hours, total_products, bonus, total_salary)
        VALUES (@user, @month, @year, @total_hours, 0, 0, 0);
    END
END;
GO

-------------------------------------------------
-- 8. Cập nhật Salary khi có Productivity mới
-------------------------------------------------
CREATE OR ALTER TRIGGER trg_Productivity_Insert
ON Productivity
AFTER INSERT
AS
BEGIN
    DECLARE @user VARCHAR(50), @month INT, @year INT;
    SELECT TOP 1 
        @user = UserName,
        @month = MONTH(work_date),
        @year = YEAR(work_date)
    FROM inserted;

    DECLARE @total_products INT = ISNULL((
        SELECT SUM(quantity) 
        FROM Productivity 
        WHERE UserName = @user 
          AND MONTH(work_date) = @month 
          AND YEAR(work_date) = @year
    ), 0);

    IF EXISTS (SELECT 1 FROM Salary WHERE UserName = @user AND month = @month AND year = @year)
    BEGIN
        UPDATE Salary
        SET total_products = @total_products
        WHERE UserName = @user AND month = @month AND year = @year;
    END
    ELSE
    BEGIN
        INSERT INTO Salary (UserName, month, year, total_hours, total_products, bonus, total_salary)
        VALUES (@user, @month, @year, 0, @total_products, 0, 0);
    END
END;
GO
-------------------------------------------------
-- 9. Hoàn trả sản phẩm khi đơn hàng bị hủy
-------------------------------------------------
CREATE OR ALTER TRIGGER trg_Orders_StatusCancel
ON Orders
AFTER UPDATE
AS
BEGIN
    -- Chỉ xử lý khi trạng thái đổi sang "Đã hủy"
    IF EXISTS (
        SELECT 1
        FROM inserted i
        JOIN deleted d ON i.order_id = d.order_id
        WHERE i.status = N'Đã hủy' AND d.status <> N'Đã hủy'
    )
    BEGIN
        UPDATE p
        SET p.quantity = p.quantity + od.quantity
        FROM Products p
        JOIN OrderDetails od ON p.product_id = od.product_id
        JOIN inserted i ON od.order_id = i.order_id
        WHERE i.status = N'Đã hủy';
    END
END;
GO