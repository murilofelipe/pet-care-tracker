.PHONY: help up down logs-api logs-db test clean run-local

# Mostra esta mensagem de ajuda por padrão
help:
	@echo "🛠️  Comandos disponíveis para o Pet Care Tracker:"
	@echo "  make up          - Compila a API e sobe todo o ecossistema (API + DB) via Docker"
	@echo "  make down        - Derruba todos os contêineres e redes do projeto"
	@echo "  make logs-api    - Acompanha os logs da API Spring Boot em tempo real"
	@echo "  make logs-db     - Acompanha os logs do banco de dados PostgreSQL"
	@echo "  make test        - Executa a suíte de testes unitários e de integração (Maven)"
	@echo "  make clean       - Limpa a pasta target/ local"
	@echo "  make run-local   - Roda a API localmente fora do Docker (requer make db-up primeiro)"

# --- Comandos do Docker Compose (Ecossistema Completo) ---

up:
	docker compose up --build -d

down:
	docker compose down

logs-api:
	docker compose logs -f petcare-api

logs-db:
	docker compose logs -f postgres-db

# --- Comandos de Desenvolvimento Local (Maven) ---

test:
	./mvnw test

clean:
	./mvnw clean

run-local:
	./mvnw spring-boot:run