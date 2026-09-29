**Português** · [English](README.en.md)

# 🐾 Pet Care Tracker - Full Stack

Ecossistema completo desenvolvido em **Java 21** e **Vue.js 3**, focado em gerenciamento de pets com arquitetura orientada a eventos.

## 🚀 Tecnologias e Arquitetura

### Backend (API REST)

- **Linguagem & Framework:** Java 21, Spring Boot 3.5
- **Banco de Dados:** PostgreSQL 16
- **Mensageria:** Apache Kafka (integração no backend)
- **Qualidade:** JUnit 5, Mockito e **Testcontainers**

### Frontend (Dashboard)

- **Framework:** Vue.js 3 (Vite)
- **Linguagem:** TypeScript
- **Estado/Roteamento:** Pinia e Vue Router
- **Estilização:** CSS Moderno / Nginx (Produção/Docker)

---

## ⚙️ Pré-requisitos

- **Docker** e **Docker Compose**
- **Node.js 20+** (para desenvolvimento local do frontend)
- **Java 21** (para desenvolvimento local do backend)

---

## 🛠️ Developer Experience (Makefile)

Utilize o `Makefile` na raiz para gerenciar o projeto:

| Comando               | Descrição                                                        |
| :-------------------- | :--------------------------------------------------------------- |
| `make up` / `down`    | Sobe / derruba todo o ecossistema (API + Vue + DB) no Docker     |
| `make logs-api`       | Acompanha os logs do backend em tempo real                       |
| `make logs-db`        | Acompanha os logs do banco                                       |
| `make run-local`      | Roda o backend localmente (`spring-boot:run`)                    |
| `make dev-frontend`   | Inicia o servidor de desenvolvimento do Vue (Vite)               |
| `make test`           | Executa a suíte de testes unitários e de integração              |
| `make install-all`    | Instala dependências do frontend e do backend                    |
| `make clean-all`      | Remove arquivos de build e `node_modules`                        |

---

## 📡 Portas Padrão

- **Frontend:** [http://localhost:8082](http://localhost:8082) (Docker/Nginx) ou `:5173` (Vite em dev)
- **Backend:** [http://localhost:8080](http://localhost:8080) (mapeado para a 8081 da aplicação no contêiner)
- **Postgres:** `localhost:5432`

---

## 🧪 Estratégia de Testes

O projeto utiliza **Testcontainers** para subir contêineres efêmeros de PostgreSQL e Kafka durante os testes de integração, garantindo que a validação seja idêntica ao ambiente de produção.

## 🌿 Fluxo de branches

Branches a partir de `develop`; PR de volta para `develop`. A `main` só recebe merge da `develop`.

_Desenvolvido por Murilo Silva Felipe._
