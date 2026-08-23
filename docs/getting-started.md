---
layout: default
title: Getting Started
nav_order: 1
permalink: /docs/getting-started
---

# Getting Started
{: .no_toc }

1. TOC
{:toc}

---

## Prerequisites

| Requirement | Version | Check with |
|:------------|:--------|:-----------|
| Java (JDK) | 17+ | `java -version` |
| MySQL | 8+ | `mysql --version` |
| RabbitMQ | 3+ | `rabbitmqctl status` |
| Maven | not needed | each service ships `mvnw` |

## Clone

```bash
git clone https://github.com/raghvendru/springboot-microservices.git
cd springboot-microservices
```

{: .note }
> Each service sits one folder deeper than you'd expect — for example the Employee Service lives at
> `employee-service/employee-service/`. That's where the `pom.xml` and `mvnw` are.

## Startup order

Order matters. The registry has to be running before anything can register with it, and the
gateway needs the registry populated before it can route.

```bash
# 1 ─ Service Registry (must be first)
cd service-registry/service-registry
./mvnw spring-boot:run

# 2 ─ Config Server
cd config-server/config-server
./mvnw spring-boot:run

# 3 ─ Business services (any order among themselves)
cd department-service/department-service && ./mvnw spring-boot:run
cd employee-service/employee-service && ./mvnw spring-boot:run
cd organisation-service/organisation-service && ./mvnw spring-boot:run

# 4 ─ API Gateway (last)
cd api-gateway/api-gateway
./mvnw spring-boot:run
```

On Windows, substitute `mvnw.cmd spring-boot:run`.

## Verify

| Check | URL | Expected |
|:------|:----|:---------|
| Eureka dashboard | `http://localhost:8761` | Five clients listed as `UP` |
| Config Server | `http://localhost:8888/actuator/health` | `{"status":"UP"}` |
| Gateway route | `http://localhost:9191/api/departments/D001` | Department JSON |

## Configuration source

Business services don't carry their own database or port settings. They import them at startup:

```properties
spring.config.import=optional:configserver:http://localhost:8888
```

The Config Server serves those files from a **separate repository**:

```properties
spring.cloud.config.server.git.uri=https://github.com/raghvendru/config-serve-repo
spring.cloud.config.server.git.default-label=main
```

{: .warning }
> Because the import is marked `optional:`, a service will start even when the Config Server is
> down — it just comes up missing its properties, which usually surfaces later as a confusing
> null or a failed database connection. If a service behaves oddly, check the Config Server first.

## Troubleshooting

<details markdown="block">
<summary><strong>Service doesn't appear in Eureka</strong></summary>

- Confirm the Service Registry is running on `8761` before starting anything else
- Registration can take up to 30 seconds — refresh the dashboard
- Check the `eureka.instance.client.serverUrl.defaultZone` property
</details>

<details markdown="block">
<summary><strong>Gateway returns 503 Service Unavailable</strong></summary>

The route target isn't registered. Gateway routes use `lb://EMPLOYEE-SERVICE`, which resolves
through Eureka — if the service isn't in the registry, there's nothing to load-balance to.
Open `http://localhost:8761` and confirm the name matches exactly, including case.
</details>

<details markdown="block">
<summary><strong>"Could not resolve placeholder 'spring.boot.message'"</strong></summary>

That property comes from the Config Server. Either the Config Server isn't running, or
`config-serve-repo` doesn't have a file matching the service's application name.
</details>

<details markdown="block">
<summary><strong>Port already in use</strong></summary>

```bash
lsof -i :8761                    # macOS / Linux
netstat -ano | findstr :8761     # Windows
```
</details>
