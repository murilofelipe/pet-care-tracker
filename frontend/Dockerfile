# Estágio 1: Build
FROM node:20.12-slim AS build-stage
WORKDIR /app

# Copia os arquivos de dependências primeiro (otimiza o cache das camadas)
COPY package*.json ./
RUN npm install

# Copia o restante dos arquivos e compila o projeto
COPY . .
RUN npm run build

# Estágio 2: Produção (Servidor Nginx)
FROM nginx:stable-alpine AS production-stage

# Copia os arquivos compilados do estágio anterior para a pasta do Nginx
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Expõe a porta 80 (padrão do Nginx)
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]