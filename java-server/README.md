# Bitwarden Server - Java Spring Boot Conversion

This project is a Java 16 Spring Boot conversion of the original C# .NET Core Bitwarden server codebase.

## Overview

The original Bitwarden server was built using:
- C# with .NET Core
- ASP.NET Core for REST APIs
- Entity Framework for data access
- SQL Server database
- IdentityServer for authentication

This conversion maintains the same functionality while using:
- Java 16
- Spring Boot 3.x
- Spring Data JPA with Hibernate
- PostgreSQL/SQL Server database support
- Spring Security for authentication

## Project Structure

```
src/main/java/com/bitwarden/server/
├── entity/          # JPA entities (converted from C# entities)
├── repository/      # Spring Data JPA repositories
├── service/         # Business logic services
├── controller/      # REST controllers
├── config/          # Configuration classes
├── dto/             # Data transfer objects
├── enums/           # Enums
└── exception/       # Custom exceptions
```

## Key Components Converted

### Entities
- **User**: Core user entity with authentication and profile data
- **Device**: User devices for multi-device support
- **Collection**: Organization collections for grouping items

### Controllers
- **UsersController**: User management endpoints
- **DevicesController**: Device management endpoints
- **CollectionsController**: Collection management endpoints

### Services
- **UserService**: User business logic and operations
- **DeviceService**: Device management operations
- **CollectionService**: Collection management operations
- **MailService**: Email notification service

## Configuration

The application uses `application.yml` for configuration with environment variable support:

### Database Configuration
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/bitwarden
    username: ${DB_USERNAME:bitwarden}
    password: ${DB_PASSWORD:password}
```

### External Services
- **Stripe**: Payment processing
- **SendGrid**: Email delivery
- **JWT**: Authentication tokens

## Building and Running

### Prerequisites
- Java 16 or higher
- Maven 3.6+
- PostgreSQL or SQL Server database

### Build
```bash
mvn clean compile
```

### Run
```bash
mvn spring-boot:run
```

### Test
```bash
mvn test
```

## Key Differences from C# Version

### Language and Framework Changes
- **Async/Await → CompletableFuture**: C# async patterns converted to Java async patterns
- **LINQ → Stream API**: C# LINQ queries converted to Java Stream operations
- **Entity Framework → JPA/Hibernate**: ORM conversion with similar functionality
- **ASP.NET Core DI → Spring DI**: Dependency injection pattern conversion
- **C# nullable types → Java Optional**: Null safety pattern conversion

### Database Changes
- **SQL Server → PostgreSQL**: Primary database changed to PostgreSQL (SQL Server still supported)
- **Entity Framework migrations → Liquibase/Flyway**: Database migration strategy (to be implemented)

### Authentication Changes
- **IdentityServer → Spring Security**: Authentication framework conversion
- **JWT implementation**: Custom JWT handling with Spring Security

## API Endpoints

### Users
- `GET /users/{id}/public-key` - Get user's public key

### Devices
- `GET /devices/{id}` - Get device by ID
- `GET /devices/user/{userId}` - Get devices for user
- `POST /devices` - Create new device
- `PUT /devices/{id}` - Update device
- `DELETE /devices/{id}` - Delete device

### Collections
- `GET /collections/{id}` - Get collection by ID
- `GET /collections/organization/{organizationId}` - Get collections for organization
- `POST /collections` - Create new collection
- `PUT /collections/{id}` - Update collection
- `DELETE /collections/{id}` - Delete collection

## Security

The application uses Spring Security with:
- JWT-based authentication
- Method-level security with `@PreAuthorize`
- CORS configuration for cross-origin requests
- BCrypt password encoding

## Error Handling

Global exception handling provides consistent error responses:
- `NotFoundException` → 404 Not Found
- `IllegalArgumentException` → 400 Bad Request
- Validation errors → 400 Bad Request with field details
- Generic exceptions → 500 Internal Server Error

## Development Notes

### Missing Components (To Be Implemented)
- Organization entities and services
- Cipher/Vault entities and services
- Billing integration
- Two-factor authentication
- WebAuthn support
- Complete authentication flow
- Database migrations
- Comprehensive test suite

### External Service Integration
- Stripe Java SDK for payment processing
- SendGrid Java SDK for email delivery
- Custom integrations for other services as needed

## Migration Guide

For teams migrating from the C# version:

1. **Database Schema**: The database schema remains largely the same
2. **API Contracts**: REST API endpoints maintain the same contracts
3. **Configuration**: Environment variables and configuration structure adapted to Spring Boot conventions
4. **Deployment**: Standard Spring Boot deployment patterns (JAR, Docker, etc.)

## Contributing

When adding new features:
1. Follow the existing package structure
2. Use Spring annotations consistently
3. Implement proper error handling
4. Add appropriate validation
5. Follow Java naming conventions
6. Add unit tests for new functionality

## License

This project maintains the same license as the original Bitwarden server codebase.
