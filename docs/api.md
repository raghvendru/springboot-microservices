---
layout: default
title: API Reference
nav_order: 4
permalink: /docs/api
---

# API Reference

All requests route through the gateway at `http://localhost:9191`.

## Employees

| Method | Path | Description |
|:-------|:-----|:------------|
| `POST` | `/api/employees` | Create an employee |
| `GET` | `/api/employees/{id}` | Get an employee, with their department resolved via Feign |

```bash
curl -X POST http://localhost:9191/api/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Asha","lastName":"Rao","email":"asha@example.com","departmentCode":"D001"}'
```

## Departments

| Method | Path | Description |
|:-------|:-----|:------------|
| `POST` | `/api/departments` | Create a department |
| `GET` | `/api/departments/{department-code}` | Get a department by its code |

```bash
curl http://localhost:9191/api/departments/D001
```

## Organizations

| Method | Path | Description |
|:-------|:-----|:------------|
| `POST` | `/api/organizations` | Create an organization |
| `GET` | `/api/organizations/{code}` | Get an organization by its code |

## Config demo endpoints

Both Employee and Department Service expose a `@RefreshScope` endpoint that returns a value served
by the Config Server — useful for confirming configuration is actually flowing.

| Service | Path |
|:--------|:-----|
| Employee Service | `/users/message` |
| Department Service | `/message` |
