---
layout: default
title: Business Services
parent: Services
nav_order: 4
permalink: /docs/services/business-services
---

# Business Services
{: .no_toc }

1. TOC
{:toc}

---

Three services share the same shape: registered with Eureka, configured from the Config Server,
backed by MySQL, with RabbitMQ wired in.

| Service | Application name | Base path |
|:--------|:-----------------|:----------|
| Employee | `EMPLOYEE-SERVICE` | `/api/employees` |
| Department | `DEPARTMENT-SERVICE` | `/api/departments` |
| Organization | `ORGANIZATION-SERVICE` | `/api/organizations` |

## Employee Service

**Folder:** `employee-service/employee-service/`

The only service that calls another. It fetches the employee's department through OpenFeign:

```java
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
```

That call is protected by a circuit breaker:

```properties
management.health.circuitbreakers.enabled=true
management.endpoint.health.show-details=always

resilience4j.circuitbreaker.instances.EMPLOYEE-SERVICE.registerHealthIndicator=true
resilience4j.circuitbreaker.instances.EMPLOYEE-SERVICE.failureRateThreshold=50
resilience4j.circuitbreaker.instances.EMPLOYEE-SERVICE.minimumNumberOfCalls=5
resilience4j.circuitbreaker.instances.EMPLOYEE-SERVICE.automaticTransitionFromOpenToHalfOpenEnabled=true
```

Watch the breaker state at `/actuator/health`.

## Department Service

**Folder:** `department-service/department-service/`

| Method | Path |
|:-------|:-----|
| `POST` | `/api/departments` |
| `GET` | `/api/departments/{department-code}` |

## Organization Service

**Folder:** `organisation-service/organisation-service/`

| Method | Path |
|:-------|:-----|
| `POST` | `/api/organizations` |
| `GET` | `/api/organizations/{code}` |

{: .warning }
> The folder is spelled `organisation-service` (British) but the application name is
> `ORGANIZATION-SERVICE` and the route is `/api/organizations` (American). Both spellings are
> load-bearing in different places — don't "fix" one without the other.

## Shared configuration

```properties
spring.config.import=optional:configserver:http://localhost:8888

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

Datasource settings are commented out in each service's local `application.properties` — they're
expected to come from the Config Server instead.
