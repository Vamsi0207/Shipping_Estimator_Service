# 📦 Shipping Estimator API

A Spring Boot based REST API that calculates shipping charges by combining:

- Nearest warehouse identification  
- Distance-based transport selection  
- Weight-based pricing  
- Delivery speed adjustments  

Designed using clean layered architecture and extensible design principles.

---

## 🚀 Tech Stack

- Java 21  
- Spring Boot  
- Spring Data JPA  
- H2 In-Memory Database  
- Maven  
- Lombok  

---

## 🏗 Architecture

The project follows a layered architecture:

Controller → Service → Repository → Database

### Key Components

- ShippingService – Core business logic  
- WarehouseService – Nearest warehouse calculation  
- DistanceUtil – Haversine distance formula implementation  
- Strategy Pattern – Delivery type handling (Standard / Express)  
- Global Exception Handling – Clean error responses  

---

## 📍 Features

- Calculates real-world distance using Haversine formula  
- Selects transport type based on distance:  
  - 0–100 km → Mini Van  
  - 100–500 km → Truck  
  - 500+ km → Aeroplane  
- Strategy Pattern for delivery types  
- Proper HTTP status codes with structured error responses  
- DTO-based API responses  
- Clean and modular code structure  

---

## ⚙️ How to Run the Application

### Using IDE

1. Open the project  
2. Run ShippingEstimatorApplication  
3. Application starts at:

http://localhost:8080

---

### Using Maven

From project root directory:

mvn clean install  
mvn spring-boot:run  

---

## 🗄 Database Access (H2 Console)

Open in browser:

http://localhost:8080/h2-console

JDBC URL:
jdbc:h2:mem:testdb

Username:
sa

Password:
(empty)

---

## 📡 API Endpoints

### 1️⃣ Find Nearest Warehouse

GET  
/api/v1/warehouse/nearest?sellerId=1&productId=1

---

### 2️⃣ Calculate Shipping Charge (Direct)

GET  
/api/v1/shipping-charge?warehouseId=1&customerId=1&productId=1&deliverySpeed=standard

---

### 3️⃣ Combined Shipping Calculation

POST  
/api/v1/shipping-charge/calculate

Request Body:

{
  "sellerId": 1,
  "customerId": 1,
  "productId": 1,
  "deliverySpeed": "standard"
}

Response:

{
  "shippingCharge": 180.00,
  "nearestWarehouse": {
    "warehouseId": 1,
    "warehouseLocation": {
      "lat": 12.99999,
      "lng": 37.923273
    }
  }
}

---

## ❗ Error Handling

Example: Invalid warehouse ID

{
  "status": 404,
  "error": "Not Found",
  "message": "Warehouse not found"
}

---

## 🧠 Design Decisions

- Used Strategy Pattern for delivery type extensibility  
- Separated business logic into service layer  
- Centralized exception handling 
- Used DTOs to avoid exposing entities directly  

---

## 📄 Testing & Execution Guide

Refer to:

TESTING_GUIDE.md

for complete testing instructions.

---

## 👨‍💻 Author

Vamsi Krishna
