# Product Catalog Service

A Spring Boot microservice for managing product catalog in an e-commerce system.

## 🚀 Features

- ✅ Full CRUD operations for products
- ✅ RESTful API design
- ✅ PostgreSQL database integration
- ✅ Input validation
- ✅ Global exception handling
- ✅ API documentation (Swagger/OpenAPI)
- ✅ Health checks and monitoring (Actuator)
- ✅ Docker support
- ✅ Production-ready architecture

## 🛠️ Technology Stack

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL 15**
- **Gradle 8.14.3**
- **Docker & Docker Compose**
- **Swagger/OpenAPI 3.0**
- **Lombok**

## 📋 Prerequisites

- Java 21 or higher
- Docker & Docker Compose (for database)
- Gradle 8.14+ (or use included wrapper)

## 🏃 Quick Start

### 1. Start PostgreSQL Database

```bash
docker-compose up -d postgres
```

### 2. Run the Application

Using Gradle wrapper:
```bash
./gradlew bootRun
```

Or using Gradle:
```bash
gradle bootRun
```

The application will start on `http://localhost:8081`

## 📡 API Endpoints

### Product Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/products` | Create a new product |
| GET | `/api/v1/products/{id}` | Get product by ID |
| GET | `/api/v1/products` | Get all products |
| PUT | `/api/v1/products/{id}` | Update product |
| DELETE | `/api/v1/products/{id}` | Delete product |
| GET | `/api/v1/products/search?term=xxx` | Search products |
| GET | `/api/v1/products/in-stock` | Get products in stock |

### Monitoring

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/actuator/health` | Health check |
| GET | `/actuator/info` | Application info |
| GET | `/actuator/metrics` | Metrics |

## 📚 API Documentation

Access Swagger UI at:
```
http://localhost:8081/swagger-ui.html
```

Access OpenAPI JSON at:
```
http://localhost:8081/v3/api-docs
```

## 🧪 Testing the API

### Create a Product

```bash
curl -X POST http://localhost:8081/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "description": "High-performance gaming laptop",
    "price": 1299.99,
    "stockQuantity": 10
  }'
```

### Get All Products

```bash
curl http://localhost:8081/api/v1/products
```

### Get Product by ID

```bash
curl http://localhost:8081/api/v1/products/1
```

### Update Product

```bash
curl -X PUT http://localhost:8081/api/v1/products/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Gaming Laptop",
    "description": "Ultra high-performance gaming laptop",
    "price": 1499.99,
    "stockQuantity": 15
  }'
```

### Delete Product

```bash
curl -X DELETE http://localhost:8081/api/v1/products/1
```

### Search Products

```bash
curl "http://localhost:8081/api/v1/products/search?term=laptop"
```

### Health Check

```bash
curl http://localhost:8081/actuator/health
```

## 🐳 Docker

### Build Docker Image

```bash
docker build -t product-service:latest .
```

### Run with Docker Compose

```bash
docker-compose up -d
```

This will start:
- Product Service (port 8081)
- PostgreSQL (port 5432)
- pgAdmin (port 5050)

### Access pgAdmin

1. Open http://localhost:5050
2. Login with:
   - Email: `admin@admin.com`
   - Password: `admin`
3. Add server:
   - Host: `postgres`
   - Port: `5432`
   - Database: `productdb`
   - Username: `postgres`
   - Password: `postgres`

## 🏗️ Project Structure

```
product-service/
├── src/
│   ├── main/
│   │   ├── java/com/vyshali/product_service/
│   │   │   ├── config/              # Configuration classes
│   │   │   ├── controller/          # REST controllers
│   │   │   ├── domain/              # JPA entities
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── exception/           # Exception handling
│   │   │   ├── repository/          # Data repositories
│   │   │   ├── service/             # Business logic
│   │   │   └── ProductServiceApplication.java
│   │   └── resources/
│   │       ├── application.yml      # Main configuration
│   │       └── application-dev.yml  # Development config
│   └── test/                        # Test classes
├── build.gradle.kts                 # Gradle build file
├── docker-compose.yml               # Docker Compose config
├── Dockerfile                       # Docker image definition
└── README.md                        # This file
```

## 📝 Configuration

### Database Configuration

Edit `application.yml` to change database settings:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/productdb
    username: postgres
    password: postgres
```

### Server Port

To change the server port, edit `application.yml`:

```yaml
server:
  port: 8081
```

## 🔧 Development

### Build the Project

```bash
./gradlew build
```

### Run Tests

```bash
./gradlew test
```

### Clean Build

```bash
./gradlew clean build
```

## 📊 Data Model

### Product Entity

```json
{
  "id": 1,
  "name": "Laptop",
  "description": "Gaming laptop",
  "price": 1299.99,
  "stockQuantity": 10,
  "createdAt": "2025-01-15T10:30:00",
  "updatedAt": "2025-01-15T10:30:00"
}
```

## 🎯 Next Steps

This is Iteration 1 of the microservices project. Future iterations will add:

- ✨ Service Discovery (Eureka)
- ✨ API Gateway
- ✨ Kafka messaging
- ✨ Circuit breakers (Resilience4j)
- ✨ Distributed tracing (Zipkin)
- ✨ Security (OAuth 2.0)
- ✨ Caching (Redis)
- ✨ Advanced monitoring

## 👤 Author

**Vyshali**
- Senior Software Engineer @ Morgan Stanley

## 📄 License

This project is part of a microservices learning journey.
