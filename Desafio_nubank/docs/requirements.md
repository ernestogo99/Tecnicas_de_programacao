# Especificação de Requisitos — Capital Gains

## 1. Introdução

O projeto Capital Gains tem como objetivo calcular o imposto incidente sobre operações de compra e venda de ações, considerando preço médio ponderado, lucros, prejuízos acumulados e a regra de isenção estabelecida pelo desafio.

A aplicação será desenvolvida em Java, utilizando Swing para sua interface gráfica, substituindo a interface de linha de comando proposta originalmente.

A solução deverá manter as regras de negócio independentes da camada de apresentação, permitindo futuras alterações na interface sem necessidade de modificar os cálculos tributários.

## 2. Objetivos

- Implementar corretamente as regras de cálculo de ganho de capital.
- Gerenciar o estado das operações em memória.
- Garantir independência entre simulações.
- Desenvolver uma interface gráfica desktop utilizando Swing.
- Aplicar separação de responsabilidades por meio de MVC + Service.
- Praticar encapsulamento, abstração, interfaces e tratamento de exceções.
- Garantir a qualidade por meio de testes unitários e de integração.

## 3. Requisitos funcionais

| ID | Requisito | Descrição |
|---|---|---|
| RF01 | Registrar compra | Permitir informar uma operação de compra de ações. |
| RF02 | Registrar venda | Permitir informar uma operação de venda de ações. |
| RF03 | Calcular preço médio | Recalcular o preço médio ponderado após cada compra. |
| RF04 | Calcular lucro/prejuízo | Identificar o resultado financeiro de cada venda. |
| RF05 | Acumular prejuízos | Manter o saldo de prejuízos acumulados. |
| RF06 | Compensar prejuízos | Deduzir prejuízos acumulados de lucros tributáveis futuros. |
| RF07 | Aplicar isenção | Não cobrar imposto quando o valor total da venda for menor ou igual a R$ 20.000,00, respeitando as regras de compensação do desafio. |
| RF08 | Calcular imposto | Aplicar a alíquota de 20% sobre o lucro tributável. |
| RF09 | Apresentar resultados | Exibir o imposto calculado para cada operação. |
| RF10 | Gerenciar simulações | Permitir executar simulações independentes. |
| RF11 | Reiniciar simulação | Permitir iniciar uma nova simulação sem carregar o estado anterior. |
| RF12 | Exibir erros | Apresentar mensagens compreensíveis quando ocorrerem erros de entrada ou processamento. |

## 4. Requisitos de projeto

| ID   | Requisito | Descrição |
|------|---|---|
| RP01 | Linguagem | A aplicação deverá utilizar Java 21. |
| RP02 | Interface | A interface gráfica deverá ser implementada utilizando Swing. |
| RP03 | Arquitetura | O projeto deverá adotar MVC + Service. |
| RP04 | Precisão | Operações monetárias deverão utilizar BigDecimal. |
| RP05 | Arredondamento | Valores decimais deverão respeitar duas casas decimais, conforme o desafio. |
| RP06 | Estado | O estado da aplicação deverá ser mantido exclusivamente em memória. |
| RP07 | Independência | Cada simulação deverá possuir seu próprio estado financeiro. |
| RP08 | Testabilidade | As regras de negócio deverão ser testáveis independentemente da interface gráfica. |
| RP09 | Manutenibilidade | As responsabilidades deverão estar separadas e organizadas. |
| RP10 | Tratamento de erros | Exceções relevantes deverão ser tratadas de maneira apropriada. |
| RP11 | Dependências | Bibliotecas e frameworks deverão ser utilizados somente quando houver justificativa técnica. |
| RP12 | Portabilidade | A compilação e execução deverão ser possíveis em sistemas Unix ou macOS, conforme o requisito original. |

## 5. Regras de negócio

- **RN01:** Operações de compra não geram imposto.
- **RN02:** O preço médio deverá ser recalculado utilizando a média ponderada das compras anteriores e da nova compra.
- **RN03:** Operações de venda deverão utilizar o preço médio vigente para determinar lucro ou prejuízo.
- **RN04:** O imposto corresponde a 20% do lucro tributável.
- **RN05:** Vendas com prejuízo não geram imposto e devem aumentar o saldo de prejuízos acumulados.
- **RN06:** Prejuízos acumulados poderão ser utilizados para compensar lucros tributáveis futuros.
- **RN07:** Vendas com valor total menor ou igual a R$ 20.000,00 são isentas.
- **RN08:** O limite de isenção considera o valor total da operação (preço unitário × quantidade), não o lucro.
- **RN09:** O lucro de uma venda isenta não deve consumir prejuízos acumulados.
- **RN10:** Prejuízos de vendas isentas continuam sendo acumulados.
- **RN11:** O preço médio deve considerar os eventos de compra anteriores, inclusive compras realizadas após vendas.
- **RN12:** Cada simulação deverá começar com uma carteira vazia e saldo de prejuízos igual a zero.

## 6. Restrições e premissas

### 6.1. Premissas originais

- As operações serão processadas na ordem cronológica recebida.
- Nenhuma venda ultrapassará a quantidade de ações disponível.
- Não haverá persistência em banco de dados.
- O estado será gerenciado em memória.
- Os valores decimais deverão ser arredondados para duas casas.
- O contrato original utiliza JSON para entrada e saída.

### 6.2. Adaptações do projeto

- A interface de linha de comando será substituída por uma interface gráfica Swing.
- A aplicação terá tratamento adicional de entradas inválidas.
- Serão implementadas exceções personalizadas.
- Serão utilizadas interfaces quando representarem contratos relevantes entre componentes.
- A arquitetura seguirá MVC + Service.

### 6.3. Limitação de escopo

Não fazem parte do escopo:

- Integração com corretoras.
- Consulta de cotações em tempo real.
- Persistência de operações.
- Autenticação de usuários.
- Integração com APIs externas.
- Cálculo de outros tributos ou modalidades de investimento.

## 7. Critérios de aceitação

A aplicação será considerada funcional quando:

1. Processar corretamente operações de compra e venda.
2. Calcular corretamente o preço médio ponderado.
3. Identificar lucros e prejuízos.
4. Acumular e compensar prejuízos conforme as regras.
5. Aplicar corretamente a isenção de R$ 20.000,00.
6. Calcular o imposto de 20% sobre o lucro tributável.
7. Produzir um resultado correspondente a cada operação recebida.
8. Manter simulações independentes.
9. Apresentar os resultados na interface gráfica.
10. Tratar adequadamente erros de entrada e processamento.
11. Passar pelos nove casos de exemplo fornecidos no desafio original.

## 8. Estratégia de qualidade

A qualidade será verificada por meio de:

- Testes unitários dos serviços de cálculo.
- Testes de integração do processamento de operações.
- Verificação dos nove casos fornecidos pelo Nubank.
- Testes adicionais de casos extremos.
- Verificação da independência entre simulações.

## 9. Tecnologias previstas

| Tecnologia | Finalidade |
|------------|---|
| Java 22    | Linguagem de programação |
| Swing      | Interface gráfica |
| Maven      | Build e dependências |
| JUnit 5    | Testes automatizados |
| AssertJ    | Asserções nos testes, caso adotado |

---

**Documento:** Especificação de Requisitos  
**Projeto:** Capital Gains  
**Versão:** 1.0 — Inicial  
**Status:** Planejamento