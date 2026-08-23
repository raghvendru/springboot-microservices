---
layout: default
title: Services
nav_order: 3
has_children: true
permalink: /docs/services/
---

# Services

| Service | Folder | Port | Role |
|:--------|:-------|:-----|:-----|
| [Service Registry](/springboot-microservices/docs/services/service-registry) | `service-registry/service-registry/` | 8761 | Eureka discovery server |
| [Config Server](/springboot-microservices/docs/services/config-server) | `config-server/config-server/` | 8888 | Centralized configuration |
| [API Gateway](/springboot-microservices/docs/services/api-gateway) | `api-gateway/api-gateway/` | 9191 | Routing and CORS |
| [Business Services](/springboot-microservices/docs/services/business-services) | `*-service/*-service/` | from config | Employee, Department, Organization |

{: .note }
> Adding a page? Copy any file in this folder, change `title` and `nav_order`, and the sidebar
> updates itself on the next build.
