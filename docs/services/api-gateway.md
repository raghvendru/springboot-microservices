---
layout: default
title: API Gateway
parent: Services
nav_order: 3
permalink: /docs/services/api-gateway
---

# API Gateway

**Folder:** `api-gateway/api-gateway/` · **Port:** `9191`

The single entry point for all client traffic. Start it last, once the registry is populated.

## Routes

```properties
spring.application.name=api-gateway
server.port=9191

spring.cloud.gateway.routes[0].id=EMPLOYEE-SERVICE
spring.cloud.gateway.routes[0].uri=lb://EMPLOYEE-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/employees/**

spring.cloud.gateway.routes[1].id=DEPARTMENT-SERVICE
spring.cloud.gateway.routes[1].uri=lb://DEPARTMENT-SERVICE
spring.cloud.gateway.routes[1].predicates[0]=Path=/api/departments/**

spring.cloud.gateway.routes[2].id=ORGANIZATION-SERVICE
spring.cloud.gateway.routes[2].uri=lb://ORGANIZATION-SERVICE
spring.cloud.gateway.routes[2].predicates[0]=Path=/api/organizations/**
```

The `lb://` scheme means "load-balance across whatever Eureka currently lists under this name."

## Adding a route

Add the next index in the sequence:

```properties
spring.cloud.gateway.routes[3].id=PAYROLL-SERVICE
spring.cloud.gateway.routes[3].uri=lb://PAYROLL-SERVICE
spring.cloud.gateway.routes[3].predicates[0]=Path=/api/payroll/**
```

{: .warning }
> Indices must be contiguous. Skipping from `[2]` to `[4]` silently drops the route.

## CORS

```properties
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedOrigins=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedMethods=GET,POST,PUT,DELETE
```

{: .warning }
> `allowedOrigins=*` is fine for local development, but browsers reject it once credentials are
> involved, and it's too permissive for anything public. Replace it with your actual frontend
> origin before deploying.
