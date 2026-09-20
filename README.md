# Pharmaceutical Sales Backend

Spring Boot backend for a pharmaceutical sales and inventory management system.

## Features

- User registration, login, JWT authentication, roles and permissions
- Customer, employee, supplier, drug and inventory management
- Sales, returns, stock-in records and financial statistics
- MySQL persistence with JPA/Hibernate

## Tech Stack

- Java 8+
- Spring Boot
- Spring Data JPA / Hibernate
- Spring Security and JWT
- MySQL
- Gradle

## Requirements

- JDK 8 or newer
- MySQL 8 or a compatible MySQL server

## Configuration

The repository contains only public-safe configuration placeholders. Do not commit database passwords or JWT secrets.

Set these environment variables before starting the service:

```text
DB_URL=jdbc:mysql://localhost:3306/medical_sales_management?useSSL=false&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=your-local-password
JWT_SECRET=your-long-random-secret
JWT_EXPIRATION=3600000
SERVER_PORT=8080
```

See [`application-example.properties`](src/main/resources/application-example.properties) for the same variable names.

The SQL files under `src/main/resources` contain the schema and sample data used by the original project. Review them before applying them to a production database.

## Run Locally

```bash
git clone https://github.com/ARETE-zzwl/pharmaceutical-sales-backend.git
cd pharmaceutical-sales-backend
./gradlew bootRun
```

On Windows, use `gradlew.bat bootRun`.

The service listens on `http://localhost:8080` by default.

## API Areas

The REST API is organized around `/api` resources for customers, drugs, employees, inventories, permissions, roles, sales, returns, stock-in records, suppliers and users. Authentication endpoints are provided by `AuthController`.

## Security Notes

- Keep `DB_PASSWORD` and `JWT_SECRET` outside version control.
- Use a unique, long JWT secret in every deployment.
- Do not reuse development database credentials in production.

## License

Licensed under the Mulan Permissive Software License, Version 2 (Mulan PSL v2). See [`LICENSE`](LICENSE).
