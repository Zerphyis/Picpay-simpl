# 💸 PicPay Simplificado - Backend Challenge

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
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
* **Persistência:** Spring Data JPA
* **Banco de Dados:** MySql (Produção)
* **Gerenciador de Dependências:** Maven
* **Testes:** JUnit 5, Mockito e AssertJ.

---

