# 医药销售管理系统

这是一个使用Spring Boot构建的医药销售管理系统。系统管理医药销售的各个方面，包括客户、药品、员工、财务统计、库存、权限、退货处理、角色、角色权限、销售、销售退货、入库和供应商。

## 目录

- [安装](#安装)
- [使用](#使用)
- [API端点](#API端点)
- [贡献](#贡献)
- [许可证](#许可证)

## 安装

### 前提条件

- Java 8 或更高版本
- Maven
- MySQL 或其他关系型数据库

### 步骤

1. 克隆仓库：

    ```sh
    git clone https://github.com/yourusername/pharmaceuticalsales.git
    ```

2. 进入项目目录：

    ```sh
    cd pharmaceuticalsales
    ```

3. 配置数据库连接，在 `src/main/resources/application.properties` 文件中：

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/pharmaceuticalsales
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

4. 构建项目：

    ```sh
    mvn clean install
    ```

5. 运行项目：

    ```sh
    mvn spring-boot:run
    ```

应用程序将启动在 `http://localhost:8080`。

## 使用

### API端点

#### 客户

- **GET** `/api/customers` - 获取所有客户
- **POST** `/api/customers` - 创建新客户
- **PUT** `/api/customers/{id}` - 更新现有客户
- **DELETE** `/api/customers/{id}` - 删除客户

#### 药品

- **GET** `/api/drugs` - 获取所有药品
- **POST** `/api/drugs` - 创建新药品
- **PUT** `/api/drugs/{id}` - 更新现有药品
- **DELETE** `/api/drugs/{id}` - 删除药品

#### 员工

- **GET** `/api/employees` - 获取所有员工
- **POST** `/api/employees` - 创建新员工
- **PUT** `/api/employees/{id}` - 更新现有员工
- **DELETE** `/api/employees/{id}` - 删除员工

#### 财务统计

- **GET** `/api/financialstats` - 获取所有财务统计
- **POST** `/api/financialstats` - 创建新的财务统计
- **PUT** `/api/financialstats/{id}` - 更新现有财务统计
- **DELETE** `/api/financialstats/{id}` - 删除财务统计

#### 库存

- **GET** `/api/inventories` - 获取所有库存
- **POST** `/api/inventories` - 创建新库存
- **PUT** `/api/inventories/{id}` - 更新现有库存
- **DELETE** `/api/inventories/{id}` - 删除库存

#### 权限

- **GET** `/api/permissions` - 获取所有权限
- **POST** `/api/permissions` - 创建新权限
- **PUT** `/api/permissions/{id}` - 更新现有权限
- **DELETE** `/api/permissions/{id}` - 删除权限

#### 退货处理

- **GET** `/api/returnhandlings` - 获取所有退货处理
- **POST** `/api/returnhandlings` - 创建新的退货处理
- **PUT** `/api/returnhandlings/{id}` - 更新现有退货处理
- **DELETE** `/api/returnhandlings/{id}` - 删除退货处理

#### 角色

- **GET** `/api/roles` - 获取所有角色
- **POST** `/api/roles` - 创建新角色
- **PUT** `/api/roles/{id}` - 更新现有角色
- **DELETE** `/api/roles/{id}` - 删除角色

#### 角色权限

- **GET** `/api/rolepermissions` - 获取所有角色权限
- **POST** `/api/rolepermissions` - 创建新角色权限
- **PUT** `/api/rolepermissions/{roleId}/{permissionId}` - 更新现有角色权限
- **DELETE** `/api/rolepermissions/{roleId}/{permissionId}` - 删除角色权限

#### 销售

- **GET** `/api/sales` - 获取所有销售
- **POST** `/api/sales` - 创建新销售
- **PUT** `/api/sales/{id}` - 更新现有销售
- **DELETE** `/api/sales/{id}` - 删除销售

#### 销售退货

- **GET** `/api/salesreturns` - 获取所有销售退货
- **POST** `/api/salesreturns` - 创建新的销售退货
- **PUT** `/api/salesreturns/{id}` - 更新现有销售退货
- **DELETE** `/api/salesreturns/{id}` - 删除销售退货

#### 入库

- **GET** `/api/stockins` - 获取所有入库记录
- **POST** `/api/stockins` - 创建新的入库记录
- **PUT** `/api/stockins/{id}` - 更新现有入库记录
- **DELETE** `/api/stockins/{id}` - 删除入库记录

#### 供应商

- **GET** `/api/suppliers` - 获取所有供应商
- **POST** `/api/suppliers` - 创建新供应商
- **PUT** `/api/suppliers/{id}` - 更新现有供应商
- **DELETE** `/api/suppliers/{id}` - 删除供应商

#### 用户

- **GET** `/api/users` - 获取所有用户
- **POST** `/api/users` - 创建新用户
- **PUT** `/api/users/{id}` - 更新现有用户
- **DELETE** `/api/users/{id}` - 删除用户

## 贡献

欢迎贡献！请fork此仓库并提交pull requests。

## 许可证

此项目基于MIT许可证。

