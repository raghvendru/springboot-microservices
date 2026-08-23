---
layout: default
title: Architecture
nav_order: 2
permalink: /docs/architecture
---

# Architecture
{: .no_toc }

1. TOC
{:toc}

---

## Request flow

```
Client
  │
  ▼
API Gateway :9191   ── matches Path=/api/employees/** → lb://EMPLOYEE-SERVICE
  │
  ├─ asks Eureka where EMPLOYEE-SERVICE currently lives
  ▼
Employee Service
  │
  ├─ OpenFeign call → DEPARTMENT-SERVICE (also resolved via Eureka)
  └─ pulled its configuration from Config Server :8888 at startup
```

## Why each piece exists

### Service Registry (Eureka)

Service addresses change whenever something restarts or scales. The registry lets a caller ask for
`DEPARTMENT-SERVICE` by name and get back a live address, so no hostname is ever hard-coded.

### API Gateway

One public door. Routing, CORS, and load balancing live here once instead of being repeated in
every service. Clients only ever need to know `localhost:9191`.

### Config Server

Configuration lives in its own Git repository (`config-serve-repo`). Changing a database URL means
editing one versioned file rather than redeploying three services.

## Inter-service communication

Employee Service calls Department Service through a declarative Feign client:

```java
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
```

The `name` is the Eureka application name, not a URL. Spring Cloud resolves it at call time and
load-balances across instances.

## Resilience

Employee Service wraps that outbound call in a Resilience4j circuit breaker:

```properties
resilience4j.circuitbreaker.instances.EMPLOYEE-SERVICE.failureRateThreshold=50
resilience4j.circuitbreaker.instances.EMPLOYEE-SERVICE.minimumNumberOfCalls=5
resilience4j.circuitbreaker.instances.EMPLOYEE-SERVICE.automaticTransitionFromOpenToHalfOpenEnabled=true
```

Once half of the last five calls fail, the breaker opens and further calls fail fast into the
fallback instead of piling up against a service that's already struggling.

## Design trade-offs

| Decision | Benefit | Cost |
|:---------|:--------|:-----|
| Database per service | Real independence, no shared schema | Cross-service joins and transactions get hard |
| Synchronous Feign calls | Simple to write and debug | Failures cascade without a circuit breaker |
| Gateway as sole entry | Policy enforced in one place | Single point of failure unless replicated |
| External config repo | One versioned source of truth | A second repository to keep in sync |

{: .note }
> A microservices split earns its complexity when separate teams need to deploy independently.
> For a single developer, a well-structured monolith usually ships faster — this project is
> valuable as a demonstration of the patterns.
