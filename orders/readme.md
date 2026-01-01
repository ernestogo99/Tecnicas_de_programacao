## Desafio BTG

Este é um microsserviço criado para resolver o seguinte desafio:

```
https://github.com/buildrun-tech/buildrun-desafio-backend-btg-pactual
```


## Estrutura de pastas


```
tech.buildrun.btg.orders/
├── config/        ← Configurações da aplicação (ex: beans, segurança, mensageria)
├── controller/    ← Camada de entrada (REST controllers)
├── dto/           ← Objetos de transferência de dados
├── entity/        ← Entidades de domínio / JPA
├── listener/      ← Listeners (ex: eventos, filas, mensagens)
├── mapper/        ← Conversores entre Entity ↔ DTO
├── repository/    ← Acesso a dados (MongoRepository)
├── service/       ← Regras de negócio
└── OrdersApplication.java ← Classe principal (Spring Boot)
```

## Destaques do projeto

Consome dados de uma fila rabbitmq e lista as seguintes informações

- Valor Total do pedido
- Quantidade de pedidos por cliente
- Lista de pedidos realizados por cliente



## Tecnologias utilizadas

- Java:  Linguagem principal utilizada no desenvolvimento do sistema.
- Spring boot: Framework para o back-end.
- Mongodb: Banco de dados não relacional escolhido para armazenar os dados
- RabbitMq: tecnologia utilizada para messageria
- Swagger: Utilizado para documentar a api
