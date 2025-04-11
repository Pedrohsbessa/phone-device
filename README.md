# 1Global Phone Device Registration

A robust Spring Boot application for managing phone inventory with PostgreSQL database, implementing a clean architecture pattern and RESTful API principles.

## GitHub Repository

### Project Information
- **Repository**: https://github.com/Pedrohsbessa/phone-device
- **Author**: [Pedro Bessa](https://github.com/Pedrohsbessa)
- **Latest Version**: 0.0.1-SNAPSHOT
- **Last Update**: April 2025

### Quick Start from GitHub
1. Clone the repository:
```bash
git clone https://github.com/Pedrohsbessa/phone-device.git
cd phone-device
```

2. Build and run with Docker Compose:
```bash
docker compose up --build
```

## Technologies & Dependencies

### Core
- Java 21
- Spring Boot 3.2.12
- PostgreSQL 16
- Docker & Docker Compose

### Spring Dependencies
- Spring Web (REST API)
- Spring Data JPA (Database operations)
- Spring Validation (Input validation)
- Spring Actuator (Monitoring)
- Spring DevTools (Development)
- Spring Docker Compose (Container management)

### Database & Migration
- PostgreSQL Driver
- Flyway 10.8.1 (Database migrations)

### Documentation
- SpringDoc OpenAPI 2.0.4 (Swagger UI)

### Development
- Lombok 1.18.38 (Boilerplate reduction)
- Maven (Build tool)

## Architecture

### Clean Architecture Implementation
The project follows a clean architecture pattern with clear separation of concerns:

```
src/main/java/com/project/_global/
├── application/
│   ├── DTO/           # Data Transfer Objects
│   ├── response/      # API Response models
│   └── service/       # Business logic
├── common/
│   └── exception/     # Custom exceptions
├── config/            # Application configurations
├── controller/        # REST endpoints
├── domain/
│   └── entity/       # Domain models
└── infrastructure/
    └── repository/   # Data access layer
```

### Database Schema
```sql
CREATE TABLE phones (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    state VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE',
    creation_time TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

## Features

### Phone Management
- **States**: AVAILABLE, IN_USE, INACTIVE
- **CRUD Operations**:
  - Create new phone devices
  - Read phone information (by ID, brand, or state)
  - Update phone details (full and partial updates)
  - Delete phone devices (when not in use)

### State Management Rules
1. Devices in use can only transition to INACTIVE
2. Inactive devices can only transition to AVAILABLE
3. Available devices can transition to any state

### API Endpoints

#### Create Phone
```http
POST /api/v1/phones
Content-Type: application/json

{
  "name": "Galaxy S21",
  "brand": "Samsung"
}
```

#### Update Phone
```http
PUT /api/v1/phones/{id}
PATCH /api/v1/phones/{id}
Content-Type: application/json

{
  "name": "Galaxy S22",
  "brand": "Samsung",
  "state": "AVAILABLE"
}
```

#### Get Phones
```http
GET /api/v1/phones          # All phones (paginated)
GET /api/v1/phones/{id}     # Single phone
GET /api/v1/phones?brand=Samsung    # Filter by brand
GET /api/v1/phones?state=AVAILABLE  # Filter by state
```

#### Delete Phone
```http
DELETE /api/v1/phones/{id}
```

## Setup & Deployment

### Prerequisites
- Java 21
- Docker & Docker Compose
- Maven (or use included wrapper)

### Local Development Setup
1. Clone the repository:
```bash
git clone https://github.com/Pedrohsbessa/phone-device.git
```

2. Start PostgreSQL database:
```bash
docker compose up -d
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

### Docker Deployment
1. Build and run with Docker Compose:
```bash
docker compose up --build
```

### Access Points
- API Base URL: http://localhost:8080/api/v1/phones
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs
- Database: localhost:5432

## Configuration

### Docker Configuration
The application uses multi-stage builds for optimized container size and runs with JRE 21 Alpine for minimal footprint.

## Error Handling

The application implements global exception handling for:
- Invalid input validation
- Phone not found scenarios
- State transition violations
- General application errors

## Security Considerations
- Input validation for all endpoints
- State transition validation
- Proper error handling and logging
- No sensitive information in responses

## Monitoring & Logging
- Actuator endpoints for monitoring
- Slf4j logging implementation
- Request/Response logging for debugging

## Testing
Integration tests available for:
- Application context loading
- Database migrations
- API endpoints

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Future Improvements and Expansions

### Security Enhancements
- Implement JWT authentication
- Add role-based access control (RBAC)
- Enable HTTPS with SSL/TLS
- Add rate limiting for API endpoints
- Implement API key authentication

### Testing Improvements
- Add unit tests for all service layers
- Implement end-to-end tests
- Add performance tests using JMeter
- Implement contract testing with Spring Cloud Contract
- Add mutation testing

### Feature Expansions
- Add phone reservation system
- Implement batch operations for multiple devices
- Create audit trail for state changes
- Add support for device maintenance schedules
- Implement device allocation history
- Add support for device categories/types
- Create reporting functionality
- Add export functionality (CSV, PDF)

### Technical Enhancements
- Implement caching with Redis
- Add message queuing with RabbitMQ/Kafka
- Implement event sourcing for state changes
- Add ElasticSearch for advanced search capabilities
- Implement GraphQL API alongside REST
- Add WebSocket support for real-time updates

### Monitoring & Observability
- Add Prometheus metrics
- Implement Grafana dashboards
- Add distributed tracing with Zipkin/Jaeger
- Enhance logging with ELK stack
- Implement health check endpoints
- Add performance monitoring

### DevOps Improvements
- Set up CI/CD pipeline with GitHub Actions
- Implement automatic deployment to cloud providers
- Add Kubernetes support
- Implement infrastructure as code with Terraform
- Add automatic backup system
- Implement blue-green deployment

### Documentation
- Add API versioning documentation
- Create developer guide
- Add postman collection
- Implement automatic API documentation generation
- Add architecture decision records (ADRs)

### UI/Frontend
- Create web interface for device management
- Implement responsive mobile design
- Add real-time dashboard
- Create admin portal
- Implement user management interface

### Database Optimizations
- Implement database partitioning
- Add database replication
- Optimize query performance
- Implement connection pooling
- Add database backup/restore functionality

### Integration Capabilities
- Add support for external inventory systems
- Implement webhook notifications
- Create email notification system
- Add SMS notifications for state changes
- Implement third-party API integrations

### Environmental Improvements
- Add support for multiple environments
- Implement configuration management
- Add environment-specific settings
- Create staging environment
- Implement feature flags

### Compliance & Standards
- Implement GDPR compliance features
- Add data retention policies
- Implement audit logging
- Add data encryption at rest
- Create compliance reporting
