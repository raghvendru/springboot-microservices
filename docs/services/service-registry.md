---
layout: default
title: Service Registry
parent: Services
nav_order: 1
permalink: /docs/services/service-registry
---

# Service Registry

**Folder:** `service-registry/service-registry/` · **Port:** `8761` · **Dashboard:** `http://localhost:8761`

Every other service registers here on startup, so this must be the first thing you run.

## Configuration

```properties
spring.application.name=SERVICE-REGISTRY
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

Both flags are `false` because the registry doesn't need to register with itself or fetch a copy of
its own registry.

## Run

```bash
cd service-registry/service-registry
./mvnw spring-boot:run
```

## How clients find it

```properties
eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/
```

{: .note }
> The dashboard is the fastest debugging tool in this project. If a gateway route returns 503 or a
> Feign call fails, open `http://localhost:8761` first — nine times out of ten the target simply
> isn't registered.
