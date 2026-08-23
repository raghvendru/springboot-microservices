---
layout: default
title: Home
nav_order: 0
description: "Employee Microservices — Spring Boot and Spring Cloud architecture, setup, and service reference."
permalink: /
---

# Employee Microservices
{: .fs-9 }

Six Spring Boot services managing Employees, Departments, and Organizations — wired together with
Eureka discovery, a Config Server, and a Spring Cloud Gateway.
{: .fs-6 .fw-300 }

[Get started](/springboot-microservices/docs/getting-started){: .btn .btn-primary .fs-5 .mb-4 .mb-md-0 .mr-2 }
[View on GitHub](https://github.com/raghvendru/springboot-microservices){: .btn .fs-5 .mb-4 .mb-md-0 }

---

## The system at a glance

| Service | Application name | Port |
|:--------|:-----------------|:-----|
| Service Registry | `SERVICE-REGISTRY` | 8761 |
| Config Server | `CONFIG-SERVER` | 8888 |
| API Gateway | `api-gateway` | 9191 |
| Employee Service | `EMPLOYEE-SERVICE` | from config |
| Department Service | `DEPARTMENT-SERVICE` | from config |
| Organization Service | `ORGANIZATION-SERVICE` | from config |

---

## Where to go

| Section | What's there |
|:--------|:-------------|
| [🚀 Getting Started](/springboot-microservices/docs/getting-started) | Prerequisites, startup order, verification, troubleshooting |
| [🏗️ Architecture](/springboot-microservices/docs/architecture) | Request flow, why each component exists, design trade-offs |
| [🧩 Services](/springboot-microservices/docs/services/) | Per-service configuration and endpoints |
| [🔌 API Reference](/springboot-microservices/docs/api) | Every endpoint in one table |

{: .warning }
> There is no parent POM and no `docker-compose.yml` in this repository. Each service is a
> standalone Maven project and must be started on its own — see
> [Getting Started](/springboot-microservices/docs/getting-started).

---

## Tech stack

`Java 17` · `Spring Boot 3` · `Spring Cloud Gateway` · `Netflix Eureka` · `Spring Cloud Config` · `OpenFeign` · `Resilience4j` · `RabbitMQ` · `MySQL`
