# Estágio 1: Build da aplicação (Usa a imagem completa do Maven)
FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copia o pom.xml e baixa as dependências (otimiza o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e compila o .jar (ignorando testes para ser rápido)
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Imagem final de Execução (Usa apenas o JRE leve)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia APENAS o arquivo .jar gerado no Estágio 1
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Comando para iniciar o Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]