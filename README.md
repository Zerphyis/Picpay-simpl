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
