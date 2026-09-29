[Português](README.md) · **English**

# 🐾 Pet Care Tracker - Full Stack

A full ecosystem built with **Java 21** and **Vue.js 3**, focused on pet management with an event-driven architecture.

## 🚀 Technology and Architecture

### Backend (REST API)

- **Language & Framework:** Java 21, Spring Boot 3.5
- **Database:** PostgreSQL 16
- **Messaging:** Apache Kafka (backend integration)
- **Quality:** JUnit 5, Mockito and **Testcontainers**

### Frontend (Dashboard)

- **Framework:** Vue.js 3 (Vite)
- **Language:** TypeScript
- **State/Routing:** Pinia and Vue Router
- **Styling:** Modern CSS / Nginx (Production/Docker)

---

## ⚙️ Prerequisites

- **Docker** and **Docker Compose**
- **Node.js 20+** (local frontend development)
- **Java 21** (local backend development)

---

## 🛠️ Developer Experience (Makefile)

Use the `Makefile` at the repo root:

| Command               | Description                                                      |
| :-------------------- | :--------------------------------------------------------------- |
| `make up` / `down`    | Start / stop the whole ecosystem (API + Vue + DB) on Docker      |
| `make logs-api`       | Tail backend logs                                                |
| `make logs-db`        | Tail database logs                                               |
| `make run-local`      | Run the backend locally (`spring-boot:run`)                      |
| `make dev-frontend`   | Start the Vue dev server (Vite)                                  |
| `make test`           | Run the unit and integration test suite                          |
| `make install-all`    | Install frontend and backend dependencies                        |
| `make clean-all`      | Remove build output and `node_modules`                           |

---

## 📡 Default Ports

- **Frontend:** [http://localhost:8082](http://localhost:8082) (Docker/Nginx) or `:5173` (Vite in dev)
- **Backend:** [http://localhost:8080](http://localhost:8080) (mapped to the app's port 8081 inside the container)
- **Postgres:** `localhost:5432`

---

## 🧪 Testing Strategy

The project uses **Testcontainers** to spin up ephemeral PostgreSQL and Kafka containers during integration tests, so validation matches production.

## 🌿 Branch flow

Branch from `develop`; open PRs back to `develop`. `main` only receives merges from `develop`.

_Developed by Murilo Silva Felipe._
