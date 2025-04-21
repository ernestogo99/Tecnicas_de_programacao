# 📘 Projeto Java - Integração com PostgreSQL

Este projeto demonstra como conectar uma aplicação Java a um banco de dados PostgreSQL usando JDBC. A classe `DbFunctions` fornece métodos utilitários para operações básicas de banco de dados, como criar tabelas, inserir dados, atualizar registros, deletar e consultar informações.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- JDBC (Java Database Connectivity)
- PostgreSQL
- IDE recomendada: IntelliJ, Eclipse ou VSCode

---

## 📦 Funcionalidades da Classe `DbFunctions`

| Método         | Descrição                                                                 |
| -------------- | ------------------------------------------------------------------------- |
| `connecToDb`   | Conecta ao banco de dados PostgreSQL.                                     |
| `createTable`  | Cria uma tabela com campos `id`, `name` e `address`, caso ela não exista. |
| `insertRow`    | Insere um novo registro na tabela `employee`.                             |
| `updateName`   | Atualiza o nome de um funcionário.                                        |
| `deleteData`   | Deleta um registro com base no `id`.                                      |
| `searchByName` | Realiza uma busca por nome e imprime os resultados no console.            |
| `readData`     | Lista todos os registros de uma tabela.                                   |
