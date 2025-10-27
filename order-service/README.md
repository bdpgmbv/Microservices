# Order Service

A Spring Boot microservice for managing customer orders in an e-commerce system.

## 🚀 Features

- ✅ Full CRUD operations for orders
- ✅ Order status management (PENDING → CONFIRMED → SHIPPED → DELIVERED)
- ✅ RESTful API design
- ✅ PostgreSQL database integration
- ✅ Input validation with business rules
- ✅ Order status transition validation
- ✅ Global exception handling
- ✅ API documentation (Swagger/OpenAPI)
- ✅ Health checks and monitoring (Actuator)
- ✅ Docker support

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
- Docker & Docker Compose
- Gradle 8.14+ (or use included wrapper)

## 🏃 Quick Start

### 1. Start PostgreSQL Database

```bash
docker-compose up -d postgres
```

### 2. Run the Application

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8082`

## 📡 API Endpoints

### Order Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/orders` | Create a new order |
| GET | `/api/v1/orders/{id}` | Get order by ID |
| GET | `/api/v1/orders` | Get all orders |
| PUT | `/api/v1/orders/{id}` | Update order |
| DELETE | `/api/v1/orders/{id}` | Cancel order |
| PATCH | `/api/v1/orders/{id}/status` | Update order status |
| GET | `/api/v1/orders/customer/{customerId}` | Get orders by customer |
| GET | `/api/v1/orders/status/{status}` | Get orders by status |
| GET | `/api/v1/orders/recent` | Get recent orders |

### Order Status Values
- `PENDING` - Order created, awaiting confirmation
- `CONFIRMED` - Order confirmed, preparing for shipment
- `SHIPPED` - Order shipped, in transit
- `DELIVERED` - Order delivered successfully
- `CANCELLED` - Order cancelled

## 📚 API Documentation

Access Swagger UI at:
```
http://localhost:8082/swagger-ui.html
```

## 🧪 Testing Examples

### Create an Order
```bash
curl -X POST http://localhost:8082/api/v1/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": "CUST001",
    "productId": 1,
    "productName": "iPhone 15 Pro",
    "quantity": 2,
    "unitPrice": 999.99
  }'
```

### Update Order Status
```bash
curl -X PATCH http://localhost:8082/api/v1/orders/1/status \
  -H "Content-Type: application/json" \
  -d '{"status": "CONFIRMED"}'
```

### Get Customer Orders
```bash
curl http://localhost:8082/api/v1/orders/customer/CUST001
```

### Get Orders by Status
```bash
curl http://localhost:8082/api/v1/orders/status/PENDING
```

## 📊 Data Model

```json
{
  "id": 1,
  "customerId": "CUST001",
  "productId": 1,
  "productName": "iPhone 15 Pro",
  "quantity": 2,
  "unitPrice": 999.99,
  "totalAmount": 1999.98,
  "status": "PENDING",
  "statusDescription": "Order created, awaiting confirmation",
  "orderDate": "2025-10-26T10:30:00",
  "updatedAt": "2025-10-26T10:30:00"
}
```

## 🔄 Order Status Transitions

Valid transitions:
- PENDING → CONFIRMED or CANCELLED
- CONFIRMED → SHIPPED or CANCELLED
- SHIPPED → DELIVERED
- CANCELLED → (No transitions allowed)
- DELIVERED → (No transitions allowed)

## 🐳 Docker

### Run with Docker Compose
```bash
docker-compose up -d
```

Ports:
- Order Service: 8082
- PostgreSQL: 5433
- pgAdmin: 5051

## 🏗️ Project Structure

```
order-service/
├── src/main/java/com/vyshali/order_service/
│   ├── config/              # Configuration
│   ├── controller/          # REST controllers
│   ├── domain/              # Entities & Enums
│   ├── dto/                 # Data Transfer Objects
│   ├── exception/           # Exception handling
│   ├── repository/          # Data repositories
│   └── service/             # Business logic
├── src/main/resources/
│   ├── application.yml
│   └── application-dev.yml
└── docker-compose.yml
```

## 👤 Author

**Vyshali** - Senior Software Engineer @ Morgan Stanley

