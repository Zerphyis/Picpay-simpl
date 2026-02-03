# 💸 PicPay Simplificado - Backend Challenge

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white)

Este projeto é uma implementação robusta do **Desafio Backend PicPay**, simulando uma plataforma de pagamentos simplificada. O foco principal foi aplicar conceitos de **Clean Code**, **SOLID** e garantir a integridade dos dados em transações financeiras.

---

## 🚀 Visão Geral

A API permite o fluxo completo de pagamentos entre dois tipos de usuários (Comuns e Lojistas), validando saldos, consultando serviços de autorização externos e garantindo a notificação dos envolvidos.

### 🧠 Regras de Negócio Implementadas

* **Tipagem de Usuários:** Diferenciação entre usuários `COMMON` e `MERCHANT` (Lojistas).
* **Restrições de Envio:** Lojistas apenas recebem transferências; usuários comuns podem enviar e receber.
* **Consistência:** Validação rigorosa de saldo antes de qualquer operação.
* **Autorizador Externo:** Integração com serviço HTTP para autorização de transações.
* **Resiliência (Transacionalidade):** Uso de `@Transactional` para garantir que, em caso de falha no fluxo, a transferência seja revertida (**Rollback**).
* **Notificações:** Simulação de envio de notificações assíncronas para o recebedor.

---

## 🏗️ Arquitetura da Aplicação

### Controller (Presentation Layer)
Responsável por:
- Expor os endpoints REST  
- Receber e validar requisições HTTP  
- Retornar respostas apropriadas  

➡️ **Não contém regras de negócio**


### Service (Application Layer)
Responsável por:
- Conter os casos de uso da aplicação  
- Orquestrar o fluxo de negócio  
- Executar validações  
- Realizar chamadas para serviços externos:
  - Autorizador  
  - Notificação  


### Domain / Model
Representa o núcleo do negócio, incluindo:
- Entidades  
- Enums  
- Regras fundamentais, como:
  - Tipagem de usuários (`COMMON` e `MERCHANT`)
  - Restrições de transferência
  - Regras de saldo
  - Consistência financeira  


### Repository (Persistence Layer)
Responsável por:
- Acesso ao banco de dados via **Spring Data JPA**
- Manter o domínio desacoplado da infraestrutura de persistência  


### Integrações Externas (Infra)
Consumo de serviços HTTP externos por meio de **clients dedicados**, como:
- Autorizador  
- Notificação  

Essa abordagem permite:
- Facilidade de mock em testes  
- Isolamento de falhas externas  
- Evolução futura para:
  - Mensageria
  - Retry policies  


### Benefícios da Arquitetura
- Mudanças na infraestrutura **não impactam** diretamente as regras de negócio  
- Facilita:
  - Testes unitários
  - Testes de integração  
- Arquitetura mais **manutenível, escalável e testável**

---

## 🛠️ Stack Tecnológica

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3
* **Banco de Dados:** MySQL 8.x
* **Persistência:** Spring Data JPA / Hibernate
* **Testes:** JUnit 5, Mockito
* **Gerenciador de Dependências:** Maven

  ---

## 📂 Estrutura do Projeto

A arquitetura segue o padrão de camadas para facilitar a manutenção e testabilidade:

```text
src/main/java/com/picpay/
├── config/       
├── controller/   
├── dto/           
├── exception/     
├── model/        
├── repository/    
└── service/
````
---

## 📦 Como Executar

### ⚙️ Configuração do application.properties (Sem Docker)
Para rodar localmente sem Docker, você deve ter um servidor MySQL ativo. Edite o arquivo src/main/resources/application.properties:

````
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/picpay_db?createDatabaseIfNotExist=true
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# URLs dos Serviços Externos (Mock)
client.authorization.service.url=[https://util.it38.com/v2/authorize](https://util.it38.com/v2/authorize)
client.notification.service.url=[https://util.it38.com/v1/notify](https://util.it38.com/v1
````

### 🚀 Execução Local (Maven)
Clone o repositório:
````
git clone [https://github.com/seu-usuario/picpay-desafio.git](https://github.com/seu-usuario/picpay-desafio.git)
cd picpay-desafio
````

Compile e instale:

````
mvn clean install
````

Inicie a aplicação:

````
mvn spring-boot:run
````

## 🐳 Execução com Docker
Se você deseja subir a aplicação e o banco de dados MySQL de forma automatizada, utilize o Docker Compose:

Gere o JAR:
````
mvn clean package -DskipTests
````
Suba os serviços:

````
docker-compose up -d
````
(Certifique-se de ter um arquivo docker-compose.yml configurado com a imagem do MySQL e da aplicação).

---

##  Endpoints Principais

### 👤 Usuários (/api/v1/users)
#### 📌 Criar usuário

 POST /api/v1/users
Cadastra um novo usuário no sistema.

Request

````
{
  "fullname": "Maria Oliveira",
  "document": "123456789",
  "email": "maria.oliveira@email.com",
  "password": "123456",
  "balance": 1000.00,
  "userType": "COMMON"
}
````

Response – 201 Created

````
{
  "id": 1,
  "fullname": "Maria Oliveira",
  "document": "123456789",
  "email": "maria.oliveira@email.com",
  "balance": 1000.00,
  "userType": "COMMON",
  "createdAt": "2026-02-03T14:20:00"
}
````

#### 📌 Listar usuários

GET /api/v1/users
Lista todos os usuários cadastrados.

Response – 200 OK
````
[
  {
    "id": 1,
    "fullname": "Maria Oliveira",
    "document": "123456789",
    "email": "maria.oliveira@email.com",
    "balance": 1000.00,
    "userType": "COMMON"
  },
  {
    "id": 2,
    "fullname": "João Silva",
    "document": "987654321",
    "email": "joao.silva@email.com",
    "balance": 2500.00,
    "userType": "MERCHANT"
  }
]
````

---

### 💰 Transações 
#### 📌 Criar transação (transferência)

POST /api/transactions
Cria uma transferência entre usuários.

Request
````
{
  "value": 100.0,
  "payerId": 4,
  "payeeId": 15
}
````
Response – 201 Created
````
{
  "id": 10,
  "value": 100.0,
  "payerId": 4,
  "payeeId": 15,
  "status": "COMPLETED",
  "createdAt": "2026-02-03T14:32:00"
}
````
#### 📌 Listar transações do usuário

GET /api/transactions?userId=4
Consulta todas as transações relacionadas ao usuário (como pagador ou recebedor).
````
Response – 200 OK

[
  {
    "id": 10,
    "value": 100.0,
    "payerId": 4,
    "payeeId": 15,
    "status": "COMPLETED",
    "createdAt": "2026-02-03T14:32:00"
  }
]
````
#### 🔄 Estorno (refund de transação)

POST /api/transactions/{transactionId}/refund
Realiza o estorno de uma transação existente.
````
Response – 200 OK

{
  "id": 11,
  "originalTransactionId": 10,
  "value": 100.0,
  "payerId": 15,
  "payeeId": 4,
  "status": "REFUNDED",
  "createdAt": "2026-02-03T15:10:00"
}
````

---

## 🧪 Testes Unitários e de Integração

A cobertura de testes foca nos fluxos críticos de negócio, garantindo que as regras de validação e a integridade financeira sejam respeitadas.

### O que é testado:
* **Validação de Transação:** Garante que lojistas (`MERCHANT`) não possam enviar dinheiro.
* **Saldo Insuficiente:** Verifica se o sistema impede transferências acima do saldo disponível.
* **Consistência de Dados:** Valida se o saldo do pagador diminui e o do recebedor aumenta após a operação.
* **Serviços Externos:** Mocks para simular o comportamento do Autorizador e do Serviço de Notificação (incluindo cenários de falha).
* **Rollback Transacional:** Garante que, se a notificação falhar ou o banco cair, o dinheiro não saia da conta do usuário sem o fluxo completo.

### Ferramentas utilizadas:
* **JUnit 5:** Framework principal de testes.
* **Mockito:** Para criação de mocks de serviços e repositórios.

### Como rodar os testes:
```bash
mvn test
````

---

## 📎 Desafio Original

Este projeto é uma solução para o desafio técnico backend da PicPay.

🔗 Repositório oficial do desafio:  
https://github.com/PicPay/picpay-desafio-backend
