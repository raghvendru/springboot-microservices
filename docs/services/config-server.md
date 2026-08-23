---
layout: default
title: Config Server
parent: Services
nav_order: 2
permalink: /docs/services/config-server
---

# Config Server

**Folder:** `config-server/config-server/` · **Port:** `8888`

Serves configuration to the business services from a **separate Git repository**.

## Configuration

```properties
spring.application.name=CONFIG-SERVER
server.port=8888
eureka.instance.client.serverUrl.defaultZone=http://localhost:8761/eureka/

spring.cloud.config.server.git.uri=https://github.com/raghvendru/config-serve-repo
spring.cloud.config.server.git.clone-on-start=true
spring.cloud.config.server.git.default-label=main
```

## Client side

Each business service imports its config at startup:

```properties
spring.config.import=optional:configserver:http://localhost:8888
```

## File naming in the config repo

| File | Applies to |
|:-----|:-----------|
| `application.properties` | Every service (shared defaults) |
| `EMPLOYEE-SERVICE.properties` | Just Employee Service |
| `EMPLOYEE-SERVICE-prod.properties` | Employee Service with the `prod` profile active |

The file name must match `spring.application.name` exactly, including case.

## Refreshing without a restart

Controllers annotated `@RefreshScope` can pick up new values by POSTing to the actuator:

```bash
curl -X POST http://localhost:9191/actuator/refresh
```

{: .warning }
> The `optional:` prefix means a service starts even when the Config Server is unreachable — it
> just comes up with its properties missing. That failure surfaces later and confusingly. Drop
> `optional:` if you'd rather fail loudly at startup.
