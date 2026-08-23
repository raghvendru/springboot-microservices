<div align="center">

# 🌱 Employee Microservices

**A Spring Boot + Spring Cloud microservices system for managing Employees, Departments, and Organizations.**

[![Docs](https://img.shields.io/badge/📖_Read_the_Docs-2563eb?style=for-the-badge)](https://raghvendru.github.io/springboot-microservices/)
[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-Eureka_·_Gateway-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-cloud)

[![Stars](https://img.shields.io/github/stars/raghvendru/springboot-microservices?style=flat-square&color=yellow)](https://github.com/raghvendru/springboot-microservices/stargazers)
[![Forks](https://img.shields.io/github/forks/raghvendru/springboot-microservices?style=flat-square&color=blue)](https://github.com/raghvendru/springboot-microservices/network/members)
[![Issues](https://img.shields.io/github/issues/raghvendru/springboot-microservices?style=flat-square&color=red)](https://github.com/raghvendru/springboot-microservices/issues)
[![Last Commit](https://img.shields.io/github/last-commit/raghvendru/springboot-microservices?style=flat-square)](https://github.com/raghvendru/springboot-microservices/commits)

[**📖 Documentation**](https://raghvendru.github.io/springboot-microservices/) &nbsp;·&nbsp;
[**🚀 Quick Start**](#-quick-start) &nbsp;·&nbsp;
[**🏗️ Architecture**](#️-architecture) &nbsp;·&nbsp;
[**🔌 API**](#-api-endpoints)

</div>

---

## ✨ Overview

Six independently runnable Spring Boot applications. Business services register themselves with
Eureka, pull their configuration from a Config Server, and are reached through a single gateway.

| | |
|---|---|
| 🧭 **Service Discovery** | Eureka registry — services find each other by name, not by host |
| 🚪 **API Gateway** | Spring Cloud Gateway routing `/api/**` to the right service |
| ⚙️ **Config Server** | Configuration pulled from a separate Git repository |
| 🔗 **OpenFeign** | Declarative REST client — Employee Service calls Department Service |
| 🛡️ **Resilience4j** | Circuit breaker on Employee Service with health indicators |
| 🐰 **RabbitMQ** | Messaging configuration wired into the business services |

---

## 🗂️ Services

| Service | Application Name | Port | Role |
|:--------|:-----------------|:-----|:-----|
| `service-registry` | `SERVICE-REGISTRY` | **8761** | Eureka discovery server — start this first |
| `config-server` | `CONFIG-SERVER` | **8888** | Serves config from `config-serve-repo` |
| `api-gateway` | `api-gateway` | **9191** | Routes all client traffic |
| `employee-service` | `EMPLOYEE-SERVICE` | from config | Employee operations |
| `department-service` | `DEPARTMENT-SERVICE` | from config | Department operations |
| `organisation-service` | `ORGANIZATION-SERVICE` | from config | Organization operations |

---

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher
- MySQL running locally
- RabbitMQ running locally
- No Maven install needed — each service ships with the Maven wrapper

### Run

Each service is a standalone Maven project. Open **six terminals** and start them **in this order**:

```bash
# 1. Service Registry — must be first
cd service-registry/service-registry && ./mvnw spring-boot:run

# 2. Config Server
cd config-server/config-server && ./mvnw spring-boot:run

# 3. Business services
cd department-service/department-service   && ./mvnw spring-boot:run
cd employee-service/employee-service       && ./mvnw spring-boot:run
cd organisation-service/organisation-service && ./mvnw spring-boot:run

# 4. API Gateway — last
cd api-gateway/api-gateway && ./mvnw spring-boot:run
```

> On Windows use `mvnw.cmd spring-boot:run` instead of `./mvnw spring-boot:run`.

### Verify

Open the Eureka dashboard at **http://localhost:8761** — all five clients should show as `UP`.

---

## 🏗️ Architecture

```
                            Client
                              │
                              ▼
                    ┌───────────────────┐
                    │   API Gateway     │  :9191
                    └─────────┬─────────┘
                              │
        ┌─────────────────────┼─────────────────────┐
        ▼                     ▼                     ▼
┌───────────────┐   ┌───────────────┐   ┌────────────────────┐
│ EMPLOYEE-SVC  │──▶│ DEPARTMENT-SVC│   │ ORGANIZATION-SVC   │
└───────┬───────┘   └───────┬───────┘   └─────────┬──────────┘
        │  OpenFeign        │                     │
        └───────────────────┼─────────────────────┘
                            ▼
              ┌──────────────────────────────┐
              │ Eureka :8761 · Config :8888  │
              └──────────────────────────────┘
```

Employee Service reaches Department Service through an OpenFeign client
(`@FeignClient(name = "DEPARTMENT-SERVICE")`) — resolved via Eureka, never a hard-coded URL.

---

## 🔌 API Endpoints

All requests go through the gateway at `http://localhost:9191`.

| Method | Path | Description |
|:-------|:-----|:------------|
| `POST` | `/api/employees` | Create an employee |
| `GET` | `/api/employees/{id}` | Get an employee (includes their department via Feign) |
| `POST` | `/api/departments` | Create a department |
| `GET` | `/api/departments/{department-code}` | Get a department by code |
| `POST` | `/api/organizations` | Create an organization |
| `GET` | `/api/organizations/{code}` | Get an organization by code |

---

## 📁 Project Structure

```
springboot-microservices/
├── api-gateway/api-gateway/
├── config-server/config-server/
├── department-service/department-service/
├── employee-service/employee-service/
├── organisation-service/organisation-service/
├── service-registry/service-registry/
├── docs/                    # 📖 GitHub Pages documentation site
└── README.md
```

---

## 📖 Documentation

Setup, per-service reference, and troubleshooting:

### 👉 **https://raghvendru.github.io/springboot-microservices/**

---

## 🗺️ Roadmap

- [ ] Add `docker-compose.yml` and Dockerfiles for one-command startup
- [ ] Add a parent POM so all modules build together
- [ ] Add Zipkin for distributed tracing
- [ ] Add Kafka for event-driven messaging
- [ ] Integration tests with Testcontainers

---

## 🤝 Contributing

1. Fork the repository
2. Create a branch: `git checkout -b feature/my-feature`
3. Commit: `git commit -m "feat: add my feature"`
4. Push and open a Pull Request

---

<div align="center">

**Built by [@raghvendru](https://github.com/raghvendru)** — if this helped, a ⭐ goes a long way.

</div>
