# Pharmaceutical Sales Backend

[中文 / bilingual README](README.md)

The Spring Boot backend for a pharmaceutical sales and inventory system. It provides authentication, roles and permissions, drug and inventory records, customers and suppliers, sales, returns and financial statistics.

Persistence uses MySQL and Spring Data JPA. Authentication uses Spring Security and JWT. The companion frontend is [pharmaceutical-sales-frontend](https://github.com/ARETE-zzwl/pharmaceutical-sales-frontend).

### Requirements

Use JDK 17 and MySQL 8. The Gradle Wrapper is included. The current project uses Spring Boot 3.3.1 and a Java 17 toolchain; the previous Java 8 requirement was incorrect.

### Database and configuration

Create the local database:

```sql
CREATE DATABASE medical_sales_management;
```

Set these environment variables before starting, replacing the example password and secret:

```text
DB_URL=jdbc:mysql://localhost:3306/medical_sales_management?useSSL=false&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=your-local-password
JWT_SECRET=your-long-random-secret
JWT_EXPIRATION=3600000
SERVER_PORT=8080
```

[application.properties](src/main/resources/application.properties) reads these variables. `DB_PASSWORD` and `JWT_SECRET` have no defaults and must be set. See [application-example.properties](src/main/resources/application-example.properties) for reference. Keep actual credentials out of version control.

The current `ddl-auto=update` setting updates the schema at startup. Files under `src/main/resources/sql_complete` and `sql_mod/` contain schema, sample data and historical changes. Review them as needed; they are not automatically applied as versioned migrations.

### Run locally

```bash
git clone https://github.com/ARETE-zzwl/pharmaceutical-sales-backend.git
cd pharmaceutical-sales-backend
./gradlew bootRun
```

On Windows, run `.gradlew.bat bootRun`. The default address is `http://localhost:8080`. The development frontend runs on 8081 and proxies `/api` to this service.

### API and source layout

Authentication endpoints are `/api/auth/login` and `/api/auth/register`. Business controllers cover customers, drugs, employees, inventories, permissions, roles, sales, returns, stock-in records and suppliers.

Under `src/main/java/com/example/pharmaceuticalsales/`, `Controller/` contains HTTP endpoints, `Service/` business logic, `Repository/` JPA data access, and `Model/` entities. Security and JWT code live in `config/` and `Util/`. Read [SecurityConfig.java](src/main/java/com/example/pharmaceuticalsales/config/SecurityConfig.java) for the actual access rules.

## License

[Mulan PSL v2](LICENSE).
