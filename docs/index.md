---
layout: default
title: Home
nav_order: 0
description: "Employee Microservices — Spring Boot and Spring Cloud architecture, setup, and service reference."
permalink: /
---

<div class="hero">
  <h1>Employee Microservices</h1>
  <p>Six Spring Boot services managing Employees, Departments, and Organizations — wired together with Eureka discovery, a Config Server, and a Spring Cloud Gateway.</p>
  <div class="hero-badges">
    <span>Java 17</span>
    <span>Spring Boot 3</span>
    <span>Eureka</span>
    <span>OpenFeign</span>
    <span>Resilience4j</span>
    <span>RabbitMQ</span>
  </div>
  <div class="hero-buttons">
    <a href="{{ '/docs/getting-started' | relative_url }}" class="btn btn-primary">Get Started</a>
    <a href="https://github.com/raghvendru/springboot-microservices" class="btn">View on GitHub</a>
  </div>
</div>

## The system

<div class="card-grid">
  <a href="{{ '/docs/services/service-registry' | relative_url }}" class="svc-card card-indigo">
    <span class="icon">🧭</span>
    <h3>Service Registry</h3>
    <p>Eureka discovery server. Every service registers here on startup — start it first.</p>
    <span class="port">:8761</span>
  </a>

  <a href="{{ '/docs/services/config-server' | relative_url }}" class="svc-card card-teal">
    <span class="icon">⚙️</span>
    <h3>Config Server</h3>
    <p>Serves configuration to every business service from a separate Git repository.</p>
    <span class="port">:8888</span>
  </a>

  <a href="{{ '/docs/services/api-gateway' | relative_url }}" class="svc-card card-amber">
    <span class="icon">🚪</span>
    <h3>API Gateway</h3>
    <p>Single entry point. Routes <code>/api/**</code> to the right service and handles CORS.</p>
    <span class="port">:9191</span>
  </a>

  <a href="{{ '/docs/services/business-services' | relative_url }}" class="svc-card card-green">
    <span class="icon">🧩</span>
    <h3>Business Services</h3>
    <p>Employee, Department, and Organization — each backed by MySQL and RabbitMQ.</p>
    <span class="port">from config</span>
  </a>
</div>

## Documentation

<div class="card-grid">
  <a href="{{ '/docs/getting-started' | relative_url }}" class="svc-card card-indigo">
    <span class="icon">🚀</span>
    <h3>Getting Started</h3>
    <p>Prerequisites, the startup order that actually matters, verification, and troubleshooting.</p>
  </a>

  <a href="{{ '/docs/architecture' | relative_url }}" class="svc-card card-rose">
    <span class="icon">🏗️</span>
    <h3>Architecture</h3>
    <p>How a request flows, why each component exists, and the trade-offs behind the design.</p>
  </a>

  <a href="{{ '/docs/api' | relative_url }}" class="svc-card card-teal">
    <span class="icon">🔌</span>
    <h3>API Reference</h3>
    <p>Every endpoint in one table, with curl examples you can paste straight into a terminal.</p>
  </a>
</div>

---

## Quick start

```bash
git clone https://github.com/raghvendru/springboot-microservices.git
cd springboot-microservices/service-registry/service-registry
./mvnw spring-boot:run
```

Then start the Config Server, the three business services, and finally the gateway. Full order and
commands are in [Getting Started]({{ '/docs/getting-started' | relative_url }}).

{: .warning }
> There is no parent POM and no `docker-compose.yml` in this repository. Each service is a
> standalone Maven project and must be started on its own.
