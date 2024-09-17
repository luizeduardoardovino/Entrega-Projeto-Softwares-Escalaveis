# Projeto de Bloco - Engenharia de Softwares Escaláveis

## Descrição do Projeto

Este projeto tem como objetivo a implementação de um sistema de gestão de livros e notificações, utilizando uma arquitetura de **microserviços**. O sistema permite o cadastro de autores, livros e usuários, além de notificar os usuários com base nos seus temas de interesse quando novos livros são cadastrados. A arquitetura utiliza **Spring Boot**, **Spring Cloud Eureka** para **Service Discovery**, **RabbitMQ** para mensageria e um **API Gateway** para roteamento centralizado.

### Microserviços Implementados:

1. **AT Service**:
   - Responsável pela gestão de livros e autores.
   - Inclui operações de cadastro e listagem de livros e autores.

2. **NotificacaoService**:
   - Gerencia os usuários e seus interesses.
   - Envia notificações para os usuários com base nos temas de interesse relacionados a novos livros cadastrados.

3. **API Gateway**:
   - Centraliza todas as requisições e faz o roteamento para os microserviços correspondentes.
   - Usa o **Spring Cloud Gateway** para redirecionar as requisições para o **AT Service** e o **NotificacaoService**.

4. **Eureka Server**:
   - Implementa o serviço de **Service Discovery**.
   - Permite que os microserviços se registrem dinamicamente e descubram uns aos outros, facilitando a comunicação entre eles.

### Mensageria com RabbitMQ:

- Foi implementado o **RabbitMQ** para permitir a comunicação assíncrona entre os microserviços. O **NotificacaoService** utiliza o RabbitMQ para enviar notificações para os usuários interessados quando novos livros são cadastrados no **AT Service**.

### Tecnologias Utilizadas:

- **Java 21**
- **Spring Boot**
- **Spring Cloud Eureka** (Service Discovery)
- **Spring Cloud Gateway** (API Gateway)
- **RabbitMQ** (Mensageria)
- **H2 Database** (Banco de dados local)
- **Docker** e **Docker Compose**

### Funcionalidades Implementadas:

- **Cadastro e Consulta de Autores e Livros**: O AT Service oferece endpoints para cadastrar autores e livros, com dados básicos como título, ISBN, e autor associado.
- **Notificação de Novos Livros**: O NotificacaoService é responsável por notificar usuários com base nos temas de interesse relacionados a novos livros cadastrados.
- **Roteamento Centralizado**: Todas as requisições aos microserviços passam pelo API Gateway, garantindo centralização e escalabilidade.
- **Registro e Descoberta de Serviços**: O Eureka Server permite que os microserviços se registrem e sejam descobertos automaticamente para facilitar a comunicação.

### Estrutura do Projeto:

A estrutura do projeto está organizada em quatro principais serviços:

- **AT Service**: Gestão de livros e autores.
- **NotificacaoService**: Gestão de usuários e envio de notificações.
- **Eureka Server**: Serviço de registro e descoberta de microserviços.
- **API Gateway**: Roteamento centralizado das requisições aos microserviços.

Cada serviço está containerizado e gerenciado pelo **Docker Compose**, facilitando o processo de deploy e execução em diferentes ambientes.

