

# ADR-003 — Seleção das tecnologias e bibliotecas

**Status:** Proposta aceita como baseline

## Contexto

O desafio recomenda simplicidade, clareza, testes e o uso moderado de bibliotecas externas.

A seleção tecnológica deverá atender aos requisitos sem introduzir frameworks ou dependências que não agreguem valor ao projeto.

## Decisão

Utilizar as seguintes tecnologias:

| Tecnologia | Finalidade | Justificativa |
|---|---|---|
| Java 21 | Linguagem | Versão LTS, com recursos modernos e compatibilidade com Swing. |
| Maven | Gerenciamento de dependências e build | Padroniza a compilação, execução e testes. |
| Java Swing | Interface gráfica | Disponível no JDK e suficiente para a interface proposta. |
| Jackson | Processamento JSON | Facilita a conversão entre JSON e objetos Java. |
| JUnit 5 | Testes automatizados | Permite criar testes unitários e de integração. |
| BigDecimal | Cálculos monetários | Evita os problemas de precisão de `float` e `double`. |

## Justificativas individuais

### Java 21

Será a versão utilizada no desenvolvimento.

A escolha permite utilizar recursos modernos da linguagem sem depender de uma versão não LTS.

### Maven

Será utilizado para:

- Gerenciar dependências.
- Compilar o projeto.
- Executar testes.
- Empacotar a aplicação.

Não será utilizado outro sistema de build.

### Swing

Será utilizado exclusivamente na camada de apresentação.

Como faz parte do JDK, não será necessário adicionar uma biblioteca externa para construir a interface.

### Jackson

Será responsável pela interpretação das operações JSON e pela serialização dos resultados.

A biblioteca será isolada atrás de contratos próprios da aplicação, evitando que as regras de negócio dependam diretamente de detalhes de implementação do Jackson.

### JUnit 5

Será utilizado para validar:

- Regras de negócio.
- Cálculos financeiros.
- Processamento de operações.
- Integração entre os componentes principais.

### BigDecimal

Será utilizado para representar valores monetários e executar cálculos financeiros.

A escolha evita imprecisões comuns em operações com ponto flutuante binário.

## Bibliotecas e frameworks não adotados

| Tecnologia | Motivo |
|---|---|
| Spring Boot | Desnecessário para uma aplicação desktop sem API ou servidor. |
| Lombok | O projeto possui poucos modelos; não justifica adicionar uma dependência. |
| Hibernate / JPA | Não existe necessidade de persistência em banco de dados. |
| Mockito | Inicialmente, os testes poderão utilizar implementações simples e objetos reais. Poderá ser introduzido caso exista necessidade concreta de simulação de dependências. |
| JavaFX | Swing atende ao escopo sem dependência adicional. |
| AssertJ | JUnit 5 oferece recursos suficientes para as verificações iniciais. |

## Consequências positivas

- Baixa quantidade de dependências.
- Menor complexidade de configuração.
- Facilidade de execução em ambientes diferentes.
- Dependências justificadas por necessidades concretas.

## Consequências negativas

- Algumas funcionalidades precisarão ser implementadas manualmente.
- Swing oferece menos recursos visuais modernos do que frameworks gráficos mais recentes.

---