# Employee Microservices Project

This project implements a **scalable microservices architecture** for managing Employees, Departments, and Organizations. The system is built using **Spring Boot, Spring Cloud, and other modern technologies**, ensuring **fault tolerance, distributed tracing, and inter-service communication**.

## 🚀 Architecture Overview
The project consists of multiple microservices communicating with each other using **REST APIs, WebClient, and Spring Cloud OpenFeign**. It is containerized using **Docker** and deployed with **Docker Compose & Docker Networks**.

### 🏗 Microservices Implemented
- **Employee Service** – Handles employee-related operations.
- **Department Service** – Manages department-related functionalities.
- **Organization Service** – Handles organizational-level operations.

Each microservice is independently deployable and registered with **Spring Cloud Netflix Eureka Server**.

## 🔗 Inter-Service Communication
The services interact using:
- **RestTemplate** – Traditional synchronous communication.
- **WebClient** – Reactive, non-blocking communication.
- **Spring Cloud OpenFeign** – Declarative REST client.

## ⚡ Key Features
### 🗂 Service Discovery & Load Balancing
- **Spring Cloud Netflix Eureka** – Service registry for automatic service discovery.

### 🌍 API Gateway
- **Spring Cloud API Gateway** – Handles routing, authentication, and load balancing.

### ⚙️ Centralized Configuration Management
- **Spring Cloud Config Server** – Manages configurations centrally, allowing **dynamic updates**.

### 📊 Distributed Tracing & Monitoring
- **Spring Cloud Sleuth** – Adds trace IDs for monitoring inter-service calls.
- **Zipkin** – Provides a **UI dashboard** for visualizing trace information.

### 🛡️ Circuit Breaker & Resilience Patterns
- **Resilience4j** – Implements:
  - **Circuit Breaking (Open-Close Fallback Mechanism).**
  - **Retry Pattern** to gracefully handle transient failures.

### 🔄 Version Migration & Upgrades
- Migrated from **Spring Boot 2.x** to **Spring Boot 3.x**.
- Addressed compatibility issues and applied best practices.

### 📦 Containerization & Deployment
- **Dockerized all microservices** for easy deployment.
- Used **Docker Compose & Docker Networks** for efficient inter-container communication.

### 📩 Event-Driven Architecture with Apache Kafka
- Implemented **Kafka Producers & Consumers**.
- Supports **both String & JSON** message formats.
- Used Kafka topics for **asynchronous** microservices communication.

## 🛠 Tech Stack
- **Spring Boot** – Framework for building microservices.
- **Spring Cloud** – Microservices tools (**Eureka, OpenFeign, API Gateway, Config Server, Sleuth, Zipkin, Resilience4j**).
- **MySQL** – Database for persistent storage.
- **JUnit 5** – Unit & Integration testing.
- **Apache Kafka** – Asynchronous messaging system.
- **Docker & Docker Compose** – Containerization and deployment.

## 🏁 Getting Started
### 🔧 Prerequisites
Ensure you have the following installed:
- **Java 17+**
- **Docker & Docker Compose**
- **MySQL**
- **Kafka** (optional for event-driven messaging)

### 🏃 Running the Services
1. Clone the repository:
   ```bash
   git clone https://github.com/raghvendru/springboot-microservices.git
   cd springboot-microservices
   ```
2. Start the services using Docker Compose:
   ```bash
   docker-compose up -d
   ```
3. Access the services:
   - **Eureka Dashboard:** `http://localhost:8761`
   - **API Gateway:** `http://localhost:8080`
   - **Zipkin UI:** `http://localhost:9411`
   
### 🔍 Testing the APIs
Use **Postman** or **cURL** to test the API endpoints.

## 📜 Conclusion
This project **demonstrates a robust microservices architecture** with **fault tolerance, distributed tracing, and event-driven communication**, making it **highly scalable and resilient** for enterprise applications. 🚀

---
### 📬 Need Help?
If you encounter any issues, feel free to create an issue in the repository or reach out to me. 😊

