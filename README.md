# 📦 ms-cliente-service

Microserviço responsável por gerenciar informações de clientes da aplicação do Challenge FIAP - Pós-Tech.

---

## 🧱 Arquitetura

Este projeto segue o padrão **Clean Architecture**, com as seguintes camadas:

- `domain`: entidades e repositórios
- `application`: serviços e DTOs
- `interfaces`: controllers (entrada REST)
- `infrastructure`: configurações e integrações futuras

---

## 🚀 Tecnologias

- Java 21
- Spring Boot 3
- PostgreSQL
- Flyway (migrations)
- Maven
- Docker & Docker Hub
- Jacoco (cobertura de testes)

---

## 🧪 Rodar localmente

### 🔧 Pré-requisitos

- JDK 21
- PostgreSQL rodando na porta 5432 (ou via Docker)
- Maven 3.8+

### 💻 Executar

```bash
./mvnw spring-boot:run
```

Ou via IntelliJ: clique no `MsClienteServiceApplication`.

---

## 🧪 Testes

Execute:

```bash
./mvnw test
```

Gere cobertura:

```bash
./mvnw verify
```

Abra o relatório:

```
target/site/jacoco/index.html
```

---

## 🐳 Docker

### Build local:

```bash
docker build -t luskafer/ms-cliente-service .
```

### Subir pro Docker Hub:

```bash
docker push luskafer/ms-cliente-service
```

### Rodar:

```bash
docker run -p 8081:8081 luskafer/ms-cliente-service
```

---

## 📥 Pull da Imagem (para seu grupo)

```bash
docker pull luskafer/ms-cliente-service:latest
```

---

## 🧪 Endpoints

| Método | Endpoint                 | Descrição                       |
| ------ | ------------------------ | ------------------------------- |
| GET    | /clientes                | Lista todos os clientes         |
| POST   | /clientes                | Cadastra um novo cliente        |
| GET    | /clientes/{id}           | Busca cliente por ID            |
| GET    | /clientes/cpf/{cpf}      | Busca cliente por CPF           |
| PUT    | /clientes/{id}           | Atualiza cliente                |
| DELETE | /clientes/{id}           | Remove cliente                  |
| POST   | /pagamentos              | Cadastra dados de pagamento     |
| GET    | /pagamentos/cliente/{id} | Busca pagamento pelo cliente ID |
| PUT    | /pagamentos/{id}         | Atualiza pagamento              |
| DELETE | /pagamentos/{id}         | Remove pagamento                |

---

## 👨‍💻 Desenvolvido por

Lucas Godoy — `luskafer`  
FIAP Pós-Tech - Arquitetura de Software com Java

---
