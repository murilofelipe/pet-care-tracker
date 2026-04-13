# 🐾 Pet Care Tracker - API REST

API robusta desenvolvida em **Java 17** com **Spring Boot 3**, focada em boas práticas de Engenharia de Software, Clean Code e Arquitetura Orientada a Eventos.

Este microsserviço gerencia o cadastro de pets e publica eventos de forma assíncrona para integrações futuras, utilizando uma infraestrutura 100% conteinerizada.

## 🚀 Tecnologias e Arquitetura

- **Linguagem & Framework:** Java 17, Spring Boot 3
- **Banco de Dados:** PostgreSQL (Produção/Docker) e H2 Database (Testes isolados em memória)
- **Mensageria:** Apache Kafka (KRaft mode nativo)
- **Boas Práticas:** DTOs imutáveis (records), Bean Validation, Tratamento de Exceções, Injeção de Dependências.
- **Automação & Infra:** Docker (Multi-stage build), Docker Compose, Makefile.
- **Qualidade & Testes:** JUnit 5, Mockito e **Testcontainers** (Testes de integração de alta fidelidade com contêineres reais).

---

## ⚙️ Pré-requisitos

Para rodar este projeto, você precisará apenas de:

- **Docker** e **Docker Compose** instalados na máquina.
- _Opcional:_ Java 17 e Maven (caso deseje rodar a aplicação fora do contêiner).

---

## 🛠️ Como Executar (Developer Experience)

O projeto conta com um Makefile configurado para abstrair a complexidade da infraestrutura. Abra o seu terminal na raiz do projeto e utilize os comandos abaixo:

### 1. Subir a Aplicação Completa

`make up`
_Este comando compila o código via Maven, constrói a imagem Docker (multi-stage) e sobe a API conectada ao banco de dados PostgreSQL._

### 2. Acompanhar os Logs

`make logs-api`
_Visualiza os logs da API em tempo real (útil para ver o disparo de eventos para o Kafka)._

### 3. Encerrar a Aplicação

`make down`
_Derruba os contêineres e limpa a rede local._

---

## 🧪 Estrutura de Testes Automatizados

A suíte de testes foi desenhada pensando em resiliência e integração contínua (CI/CD). Para rodar todos os testes, execute:

`make test`

A estratégia foi dividida em três camadas:

1. **Testes Unitários (@ExtendWith(MockitoExtension.class)):** Testam a regra de negócio (Service) de forma isolada em milissegundos, "mockando" o repositório e o Kafka.
2. **Testes de Integração Rápidos (H2 + @MockBean):** Sobem o contexto do Spring com um banco em memória, validando a camada HTTP (Controller) e os DTOs sem depender de infraestrutura de rede.
3. **Testes de Alta Fidelidade (Testcontainers):** Utilizam a anotação @ServiceConnection para subir contêineres efêmeros do **PostgreSQL** e do **Apache Kafka Nativo** durante a execução. Validam o fluxo ponta a ponta (E2E), garantindo que os drivers de banco e os tópicos de mensageria estão funcionando como em produção.

---

## 📡 Endpoints Principais

A API responde na porta padrão 8080. Abaixo estão os principais fluxos:

### Cadastrar Pet

`POST /api/pets`

**Payload (Exemplo):**
{
"nome": "Aura",
"especie": "Gato",
"raca": "SDR",
"dataNascimento": "2022-05-05",
"pesoAtual": 4.0
}

**Comportamento Interno:**

1. Valida o Payload.
2. Salva o registro no PostgreSQL.
3. Dispara assincronamente a mensagem para o tópico `pet-cadastrado-topic` no Kafka.
4. Retorna **201 Created**.

### Listar Pets

`GET /api/pets`
Retorna a lista de todos os pets cadastrados (**200 OK**).

---

_Desenvolvido por Murilo Silva Felipe._
