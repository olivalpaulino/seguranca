# 🛡️ Autenticação com Spring Security – Login e Senha (Spring Boot 3.4.5 + Java 21)

Este projeto demonstra uma implementação simples e funcional de **autenticação baseada em login e senha** utilizando **Spring Security** com **Spring Boot 3.4.5** e **Java 21**, voltado para desenvolvedores iniciantes que desejam aprender como funciona o fluxo de autenticação no Spring.

A autenticação é feita via formulário HTML (login tradicional), com backend conectado a um banco de dados **H2 em memória**, onde os usuários são persistidos via **JPA**. As senhas são protegidas com o algoritmo **BCrypt**. Após o login, o usuário é redirecionado para uma área restrita (dashboard) e pode efetuar logout.

---

## ✅ Funcionalidades Implementadas

- 🔐 Autenticação de usuários via formulário customizado
- 🧩 Spring Security com `SecurityFilterChain` (sem XML ou WebSecurityConfigurerAdapter)
- 🗃️ Banco de dados H2 em memória com console web (`/h2-console`)
- 🧑‍💻 Entidade `User` com campos: `username`, `password`, `role` e `enabled`
- 🔄 Senhas criptografadas com `BCryptPasswordEncoder`
- 🧠 Implementação personalizada de `UserDetails` e `UserDetailsService`
- 🔄 Redirecionamento após login e logout
- 🚪 Logout com invalidação de sessão

---

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.5
- Spring Security 6.x
- Spring Data JPA
- H2 Database (em memória)
- Thymeleaf (para renderização das views)
- Maven

---

## 🚀 Requisitos

- Java 21 instalado
- Maven 3.8+ instalado

---

## ▶️ Como Executar o Projeto

- Download do projeto remoto, aqui neste repositório
- Importar o projeto no Intellij Idea Community 2025.1.1.1
- Executar o projeto
- Observação: Analisar o log
- Acesse primeiro:
- localhost:8080/dashboard para ser redirecionado para localhost:8080/login
- Faça a autenticação com o usuário: testuser e a senha: 12345
- Depois, faça logout e pronto. Já aplicou todos os conceitos abordados na aula.
