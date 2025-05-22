
# 📦 ms-cliente-service

Microserviço responsável por gerenciar informações de **clientes** e **dados de pagamento** da aplicação do Challenge FIAP - Pós-Tech.

---

## 🧱 Arquitetura

O projeto segue o padrão **Clean Architecture**, garantindo desacoplamento e alta manutenibilidade, organizado em:

- `domain`: entidades de negócio puras (Cliente, Endereço, DadosPagamento)
- `gateway`: comunicação com banco de dados (JPA), mapeadores (Mappers) e interfaces (Gateways)
- `usecases`: regras de negócio específicas de cada entidade
- `controller`: entrada da aplicação via API REST
- `dto`: objetos de transporte entre cliente e API
- `config`: configuração geral da aplicação (se necessário)

---

## 🚀 Tecnologias

- Java 21
- Spring Boot 3
- PostgreSQL
- Flyway (Migrations)
- Maven
- Docker & Docker Hub
- Jacoco (Cobertura de testes)
- JUnit 5 + Mockito

---

## 🏗️ Executar localmente

### 🔧 Pré-requisitos

- JDK 21
- PostgreSQL rodando na porta 5432 (local ou via Docker)
- Maven 3.8+

### ▶️ Rodar aplicação

```bash
./mvnw spring-boot:run
```

Ou via sua IDE (IntelliJ ou VS Code), executando a classe:

```plaintext
MsClienteServiceApplication
```

---

## 🧪 Testes

### Executar testes unitários e de integração:

```bash
./mvnw test
```

### Gerar relatório de cobertura (Jacoco):

```bash
./mvnw verify
```

### Acessar relatório:

```plaintext
target/site/jacoco/index.html
```

---

## 🐳 Docker

### 📦 Gerar imagem local

```bash
docker build -t luskafer/ms-cliente-service .
```

### 🚀 Publicar no Docker Hub

```bash
docker push luskafer/ms-cliente-service
```

### ▶️ Executar container

```bash
docker run -p 8081:8081 luskafer/ms-cliente-service
```

### 📥 Baixar imagem (seu grupo/avaliador)

```bash
docker pull luskafer/ms-cliente-service:latest
```

---

## 📡 API - Endpoints

| Método | Endpoint                   | Descrição                       |
| ------ | --------------------------- | ------------------------------- |
| GET    | `/clientes`                 | Lista todos os clientes         |
| POST   | `/clientes`                 | Cadastra um novo cliente        |
| GET    | `/clientes/{id}`            | Busca cliente por ID            |
| GET    | `/clientes/cpf/{cpf}`       | Busca cliente por CPF           |
| PUT    | `/clientes/{id}`            | Atualiza cliente                |
| DELETE | `/clientes/{id}`            | Remove cliente                  |
| POST   | `/pagamentos`               | Cadastra dados de pagamento     |
| GET    | `/pagamentos/cliente/{id}`  | Busca pagamento por cliente ID  |
| PUT    | `/pagamentos/{id}`          | Atualiza dados de pagamento     |
| DELETE | `/pagamentos/{id}`          | Remove dados de pagamento       |

---

## 🔗 Banco de Dados (PostgreSQL)

As tabelas são criadas automaticamente via **Flyway**, ao iniciar a aplicação.

| Tabela             | Descrição                      |
| ------------------ | -------------------------------|
| `cliente`          | Dados do cliente               |
| `endereco`         | Endereço associado             |
| `dados_pagamento`  | Dados de pagamento do cliente  |

---

## 👨‍💻 Desenvolvido por

**Lucas Godoy** — [@luskafer](https://github.com/luskafer)  
FIAP Pós-Tech — Arquitetura de Software com Java

---
