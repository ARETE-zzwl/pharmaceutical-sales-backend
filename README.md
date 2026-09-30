# Pharmaceutical Sales Backend

[中文](#中文) · [English](#english)

## 中文

医药销售与库存管理系统的 Spring Boot 后端，包含用户登录、角色权限、药品与库存、客户与供应商、销售退货和财务统计接口。

数据存储使用 MySQL 和 Spring Data JPA，登录认证使用 Spring Security 和 JWT。配套前端见 [pharmaceutical-sales-frontend](https://github.com/ARETE-zzwl/pharmaceutical-sales-frontend)。

### 运行环境

- JDK 17，与 `build.gradle` 中的 Java toolchain 一致
- MySQL 8
- 仓库自带 Gradle Wrapper，无需另装 Gradle

项目使用 Spring Boot 3.3.1。旧说明中的 Java 8 不适用于当前构建配置。

### 数据库和配置

先在 MySQL 中创建本地数据库：

```sql
CREATE DATABASE medical_sales_management;
```

启动前设置以下环境变量。示例中的密码和密钥需要替换为自己的本地值：

```text
DB_URL=jdbc:mysql://localhost:3306/medical_sales_management?useSSL=false&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=your-local-password
JWT_SECRET=your-long-random-secret
JWT_EXPIRATION=3600000
SERVER_PORT=8080
```

变量由 [application.properties](src/main/resources/application.properties) 读取，也可以参考 [application-example.properties](src/main/resources/application-example.properties)。`DB_PASSWORD` 和 `JWT_SECRET` 没有默认值，必须配置。不要把实际密码或密钥提交到仓库。

当前使用 `spring.jpa.hibernate.ddl-auto=update`，启动时会更新表结构。`src/main/resources/sql_complete` 和 `sql_mod/` 保留了建表、示例数据和修改脚本；按需检查后使用，它们不会自动作为版本化迁移执行。

### 启动

```bash
git clone https://github.com/ARETE-zzwl/pharmaceutical-sales-backend.git
cd pharmaceutical-sales-backend
./gradlew bootRun
```

Windows 使用：

```powershell
.\gradlew.bat bootRun
```

默认监听 `http://localhost:8080`。开发前端使用 8081，并代理 `/api` 请求到后端。

### 接口和代码

登录与注册位于 `/api/auth/login` 和 `/api/auth/register`。业务接口按客户、药品、员工、库存、权限、角色、销售、退货、入库和供应商等资源组织。

| 目录 | 内容 |
| --- | --- |
| `Controller/` | HTTP 接口 |
| `Service/` | 业务逻辑 |
| `Repository/` | JPA 数据访问 |
| `Model/` | 数据实体 |
| `config/`、`Util/` | 安全配置和 JWT 工具 |

以上目录均位于 `src/main/java/com/example/pharmaceuticalsales/`。角色与权限模块的具体访问规则见 [SecurityConfig.java](src/main/java/com/example/pharmaceuticalsales/config/SecurityConfig.java)。

## English

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
