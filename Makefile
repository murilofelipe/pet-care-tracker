.PHONY: help up down logs-api logs-db test clean clean-all run-local install-frontend dev-frontend

# --- Variáveis ---
FRONTEND_DIR = frontend
BACKEND_DIR = backend

# Mostra esta mensagem de ajuda por padrão
help:
	@echo "🛠️  Comandos do Pet Care Tracker (Full-stack):"
	@echo "  make up               - Sobe todo o ecossistema (API + Vue + DB) via Docker"
	@echo "  make down             - Derruba todos os contêineres e redes"
	@echo "  make logs-api         - Logs da API Spring Boot"
	@echo "  make install-all      - Instala dependências do front e prepara o back"
	@echo "  make dev-frontend     - Roda o Vue localmente (Vite)"
	@echo "  make test             - Executa testes do Backend"
	@echo "  make clean-all        - Limpa builds do Java e node_modules do Vue"

# --- Docker Compose (Fluxo Principal) ---

up:
	docker compose up --build -d

down:
	docker compose down

logs-api:
	docker compose logs -f petcare-api

logs-db:
	docker compose logs -f petcare-db

# --- Desenvolvimento Backend (Java 21) ---

test: check-java
	cd $(BACKEND_DIR) && ./mvnw test

clean: check-java
	cd $(BACKEND_DIR) && ./mvnw clean

run-local: check-java
	cd $(BACKEND_DIR) && ./mvnw spring-boot:run

check-java:
	@java -version 2>&1 | grep -q "21" || (echo "Erro: Requer Java 21 " && exit 1)

# --- Desenvolvimento Frontend (Vue 3 + Vite) ---

install-frontend:
	cd $(FRONTEND_DIR) && npm install

dev-frontend:
	cd $(FRONTEND_DIR) && npm run dev

install-all: install-frontend
	cd $(BACKEND_DIR) && ./mvnw install -DskipTests

# --- Limpeza Total ---

clean-all:
	cd $(BACKEND_DIR) && ./mvnw clean
	cd $(FRONTEND_DIR) && rm -rf dist node_modules
	@echo "✨ Limpeza completa realizada."