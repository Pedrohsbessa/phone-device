# Phone Inventory Management System

A Spring Boot application for managing phone inventory with PostgreSQL database.

## Technologies

- Java 21
- Spring Boot 3.4.4
- PostgreSQL
- Flyway
- Docker Compose
- JPA/Hibernate

## Setup

1. Clone the repository:
```bash
git clone https://github.com/SEU_USERNAME/phone-inventory.git
```

2. Start the PostgreSQL database:
```bash
docker compose up -d
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

## Database Schema

The application uses a PostgreSQL database with the following schema:

- **phones** table:
  - id (UUID)
  - name (VARCHAR)
  - brand (VARCHAR)
  - state (ENUM: AVAILABLE, IN_USE, INACTIVE)
  - creation_time (TIMESTAMP)

## API Documentation

[Em desenvolvimento]