# 🐾 Pet Care Tracker - Full Stack

Ecossistema completo desenvolvido em **Java 21** e **Vue.js 3**, focado em gerenciamento de pets com arquitetura orientada a eventos.

## 🚀 Tecnologias e Arquitetura

### Backend (API REST)

- [cite_start]**Linguagem & Framework:** Java 21, Spring Boot 3
- **Banco de Dados:** PostgreSQL 16
- **Mensageria:** Apache Kafka (KRaft mode)
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
- [cite_start]**Java 21** (para desenvolvimento local do backend)

---

## 🛠️ Developer Experience (Makefile)

Utilize o `Makefile` na raiz para gerenciar o projeto:

| Comando             | Descrição                                                                 |
| :------------------ | :------------------------------------------------------------------------ |
| `make up`           | [cite_start]Build e deploy de todo o ecossistema no Docker [cite: 1]      |
| `make dev-frontend` | Inicia o servidor de desenvolvimento do Vue (Vite)                        |
| `make logs-api`     | Acompanha os logs do backend em tempo real                                |
| `make test`         | [cite_start]Executa a suíte de testes unitários e de integração [cite: 1] |
| `make clean-all`    | Remove arquivos de build e `node_modules`                                 |

---

## 📡 Portas Padrão

- **Frontend:** [http://localhost:8082](http://localhost:8082) (Mapeado para evitar conflitos com Apache local)
- **Backend:** [http://localhost:8080](http://localhost:8080) (Mapeado internamente para a 8081 da aplicação)
- **Postgres:** `localhost:5432`

---

## 🧪 Estratégia de Testes

O projeto utiliza **Testcontainers** para subir contêineres efêmeros de PostgreSQL e Kafka durante os testes de integração, garantindo que a validação seja idêntica ao ambiente de produção.

_Desenvolvido por Murilo Silva Felipe._
