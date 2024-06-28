# Pharmaceutical Sales Management System

This is a Pharmaceutical Sales Management System built with Spring Boot. The system manages various aspects of pharmaceutical sales including customers, drugs, employees, financial statistics, inventory, permissions, return handling, roles, role permissions, sales, sales returns, stock-ins, and suppliers.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Installation

### Prerequisites

- Java 8 or higher
- Maven
- MySQL or any other relational database

### Steps

1. Clone the repository:

    ```sh
    git clone https://github.com/yourusername/pharmaceuticalsales.git
    ```

2. Navigate to the project directory:

    ```sh
    cd pharmaceuticalsales
    ```

3. Configure the database connection in `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/pharmaceuticalsales
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Build the project:

    ```sh
    mvn clean install
    ```

5. Run the project:

    ```sh
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080`.

## Usage

### API Endpoints

#### Customers

- **GET** `/api/customers` - Retrieve all customers
- **POST** `/api/customers` - Create a new customer
- **PUT** `/api/customers/{id}` - Update an existing customer
- **DELETE** `/api/customers/{id}` - Delete a customer

#### Drugs

- **GET** `/api/drugs` - Retrieve all drugs
- **POST** `/api/drugs` - Create a new drug
- **PUT** `/api/drugs/{id}` - Update an existing drug
- **DELETE** `/api/drugs/{id}` - Delete a drug

#### Employees

- **GET** `/api/employees` - Retrieve all employees
- **POST** `/api/employees` - Create a new employee
- **PUT** `/api/employees/{id}` - Update an existing employee
- **DELETE** `/api/employees/{id}` - Delete an employee

#### Financial Stats

- **GET** `/api/financialstats` - Retrieve all financial stats
- **POST** `/api/financialstats` - Create new financial stats
- **PUT** `/api/financialstats/{id}` - Update existing financial stats
- **DELETE** `/api/financialstats/{id}` - Delete financial stats

#### Inventory

- **GET** `/api/inventories` - Retrieve all inventories
- **POST** `/api/inventories` - Create new inventory
- **PUT** `/api/inventories/{id}` - Update existing inventory
- **DELETE** `/api/inventories/{id}` - Delete inventory

#### Permissions

- **GET** `/api/permissions` - Retrieve all permissions
- **POST** `/api/permissions` - Create a new permission
- **PUT** `/api/permissions/{id}` - Update an existing permission
- **DELETE** `/api/permissions/{id}` - Delete a permission

#### Return Handling

- **GET** `/api/returnhandlings` - Retrieve all return handlings
- **POST** `/api/returnhandlings` - Create new return handling
- **PUT** `/api/returnhandlings/{id}` - Update existing return handling
- **DELETE** `/api/returnhandlings/{id}` - Delete return handling

#### Roles

- **GET** `/api/roles` - Retrieve all roles
- **POST** `/api/roles` - Create a new role
- **PUT** `/api/roles/{id}` - Update an existing role
- **DELETE** `/api/roles/{id}` - Delete a role

#### Role Permissions

- **GET** `/api/rolepermissions` - Retrieve all role permissions
- **POST** `/api/rolepermissions` - Create new role permission
- **PUT** `/api/rolepermissions/{roleId}/{permissionId}` - Update existing role permission
- **DELETE** `/api/rolepermissions/{roleId}/{permissionId}` - Delete role permission

#### Sales

- **GET** `/api/sales` - Retrieve all sales
- **POST** `/api/sales` - Create new sales
- **PUT** `/api/sales/{id}` - Update existing sales
- **DELETE** `/api/sales/{id}` - Delete sales

#### Sales Return

- **GET** `/api/salesreturns` - Retrieve all sales returns
- **POST** `/api/salesreturns` - Create new sales return
- **PUT** `/api/salesreturns/{id}` - Update existing sales return
- **DELETE** `/api/salesreturns/{id}` - Delete sales return

#### Stock In

- **GET** `/api/stockins` - Retrieve all stock-ins
- **POST** `/api/stockins` - Create new stock-in
- **PUT** `/api/stockins/{id}` - Update existing stock-in
- **DELETE** `/api/stockins/{id}` - Delete stock-in

#### Suppliers

- **GET** `/api/suppliers` - Retrieve all suppliers
- **POST** `/api/suppliers` - Create a new supplier
- **PUT** `/api/suppliers/{id}` - Update an existing supplier
- **DELETE** `/api/suppliers/{id}` - Delete a supplier

#### Users

- **GET** `/api/users` - Retrieve all users
- **POST** `/api/users` - Create a new user
- **PUT** `/api/users/{id}` - Update an existing user
- **DELETE** `/api/users/{id}` - Delete a user

## Contributing

Contributions are welcome! Please fork this repository and submit pull requests.

## License

This project is licensed under the MIT License.
