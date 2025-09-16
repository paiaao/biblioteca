# Sistema de Gerenciamento de Biblioteca

Sistema completo para gerenciamento de bibliotecas desenvolvido em Spring Boot com PostgreSQL, permitindo o controle de livros, funcionários, clientes, empréstimos, reservas e categorias.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- **Java 17** ou superior
- **IntelliJ IDEA** (Community ou Ultimate)
- **PostgreSQL** (versão 12 ou superior)
- **Git**

## Configuração e Execução

### 1. Clonar o Repositório

```bash
git clone https://github.com/paiaao/biblioteca
cd biblioteca
```

### 2. Configurar o Banco de Dados
- Conecte-se ao PostgreSQL (via pgAdmin ou linha de comando)
- Crie o banco de dados:
```pgadmin
CREATE DATABASE biblioteca_db;
```
### 3. Configurar as Credenciais do Banco
O arquivo application.properties já está configurado com as credenciais padrão:
```application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```
Caso suas credenciais do PostgreSQL sejam diferentes, edite o arquivo src/main/resources/application.properties conforme necessário.

### 4. Importar no IntelliJ IDEA
- Abra o IntelliJ IDEA
- Selecione File > Open
- Navegue até a pasta do projeto e selecione-a
- Aguarde o IntelliJ baixar todas as dependências do Gradle

### 5. Executar a Aplicação
- Localize a classe BibliotecaApplication.java no pacote com.github.paiaao.biblioteca
- Clique com o botão direito na classe
- Selecione Run 'BibliotecaApplication'
Ou clique no botão de execução do IntelliJ

### 6. Acessar a Aplicação
- Após a execução, acesse: http://localhost:8080
- Como o banco inicia vazio, é necessário criar manualmente um usuário administrador:
```pgadmin
INSERT INTO funcionario (email, nome, senha) 
VALUES ('admin@gmail.com', 'admin', 'admin');
```
- Use estas credenciais para fazer login no sistema

## Funcionalidades
O sistema permite gerenciar:

 Livros - Cadastro, edição e consulta

 Funcionários - Gerenciamento de equipe

 Clientes - Cadastro e controle de usuários

 Empréstimos - Controle de empréstimos de livros

 Reservas - Sistema de reservas

 Categorias - Organização de livros por categorias

## Tecnologias Utilizadas
- **Spring Boot** - Framework principal
- **PostgreSQL** - Banco de dados
- **Thymeleaf** - Template engine
- **Gradle** - Gerenciamento de dependências
- **Hibernate/JPA** - ORM e persistência de dados
