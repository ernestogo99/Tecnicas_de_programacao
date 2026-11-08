# CAPITAL GAINS SIMULATOR

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-GUI-007396?style=for-the-badge&logo=java&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

Aplicação desktop desenvolvida em Java para simular a tributação sobre operações de compra e venda de ações, seguindo as regras de negócio propostas pelo desafio **Ganho de Capital do Nubank**.

O sistema processa operações financeiras, calcula o preço médio ponderado das ações, controla prejuízos acumulados, aplica as regras de isenção e determina o imposto devido em cada venda.

**A proposta original de interface CLI foi adaptada para uma interface gráfica utilizando Java Swing.**

## Table of Contents

- [Funcionalidades](#funcionalidades)
- [Regras de Negócio](#regras-de-negócio)
- [Pré-requisitos](#pré-requisitos)
- [Estrutura de Pastas](#estrutura-de-pastas)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura](#arquitetura)
- [Configuração do Projeto](#configuração-do-projeto)
- [Fluxo da Aplicação](#fluxo-da-aplicação)

## Funcionalidades

- **Gerenciamento de Operações:** Cadastro de operações de compra e venda de ações, informando o tipo da operação, preço unitário e quantidade.

- **Preço Médio Ponderado:** Cálculo automático do preço médio de aquisição sempre que novas ações são compradas.

- **Cálculo de Ganho de Capital:** Identificação de lucros e prejuízos em operações de venda, considerando o preço médio das ações.

- **Cálculo de Impostos:** Aplicação da alíquota de 20% sobre o lucro tributável, respeitando as regras de isenção.

- **Compensação de Prejuízos:** Controle de prejuízos acumulados para compensação em operações tributáveis futuras.

- **Isenção de Imposto:** Aplicação da isenção para vendas cujo valor bruto seja igual ou inferior a R$ 20.000,00.

- **Resumo Financeiro:** Apresentação da quantidade de ações em carteira, preço médio, prejuízo acumulado e total de impostos.

- **Validação de Operações:** Verificação dos dados informados e das regras de negócio, incluindo a disponibilidade de ações para venda.

- **Tratamento de Exceções:** Utilização de exceções personalizadas para representar erros de validação e regras de negócio.

## Regras de Negócio

O cálculo da tributação considera as seguintes regras:

1. **Compra de ações:** Não gera incidência de imposto e atualiza o preço médio ponderado da carteira.

2. **Venda com lucro:** O lucro é calculado pela diferença entre o preço de venda e o preço médio de aquisição, multiplicada pela quantidade vendida.

3. **Venda com prejuízo:** Não gera imposto e o valor do prejuízo é acumulado para compensação futura.

4. **Alíquota:** A tributação corresponde a 20% do lucro tributável.

5. **Isenção:** Vendas com valor bruto igual ou inferior a R$ 20.000,00 são isentas de imposto.

6. **Compensação de prejuízos:** Prejuízos acumulados são utilizados para reduzir lucros tributáveis futuros.

7. **Vendas isentas com lucro:** Não consomem prejuízos acumulados.

8. **Vendas isentas com prejuízo:** O prejuízo continua sendo acumulado normalmente.

9. **Quantidade em carteira:** Cada venda reduz a quantidade de ações disponíveis, sem alterar o preço médio das ações remanescentes.

10. **Precisão monetária:** Os valores financeiros são calculados utilizando `BigDecimal`, com arredondamento para duas casas decimais e `RoundingMode.HALF_UP`.

## Pré-requisitos

Certifique-se de possuir as seguintes ferramentas instaladas:

- **JDK 21:** Ambiente de desenvolvimento e execução Java.
- **Apache Maven 3.9+:** Gerenciamento de dependências e construção do projeto.
- **Git:** Para clonar o repositório.

Verifique as instalações:

```bash
java -version
mvn -version
git --version
```

## Estrutura de Pastas

```text
capital-gains-simulator/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/example/
│   │           │
│   │           ├── controller/
│   │           │   └── SimulationController.java
│   │           │
│   │           ├── enums/
│   │           │   ├── FinancialResultType.java
│   │           │   └── OperationType.java
│   │           │
│   │           ├── exception/
│   │           │   ├── CapitalGainsException.java
│   │           │   ├── InsufficientSharesException.java
│   │           │   ├── InvalidOperationException.java
│   │           │   ├── InvalidPriceException.java
│   │           │   └── InvalidQuantityException.java
│   │           │
│   │           ├── interfaces/
│   │           │   └── CapitalGainsCalculator.java
│   │           │
│   │           ├── model/
│   │           │   ├── FinancialSummary.java
│   │           │   ├── Operation.java
│   │           │   ├── OperationResult.java
│   │           │   ├── Portfolio.java
│   │           │   └── SimulationResult.java
│   │           │
│   │           ├── service/
│   │           │   ├── CapitalGainsService.java
│   │           │   ├── OperationValidator.java
│   │           │   ├── PortfolioService.java
│   │           │   └── TaxService.java
│   │           │
│   │           ├── view/
│   │           │   ├── table/
│   │           │   │   ├── OperationsTableModel.java
│   │           │   │   └── ResultsTableModel.java
│   │           │   │
│   │           │   ├── FinancialSummaryPanel.java
│   │           │   ├── MainFrame.java
│   │           │   ├── OperationFormPanel.java
│   │           │   ├── OperationsTablePanel.java
│   │           │   └── ResultsPanel.java
│   │           │
│   │           └── Main.java
│   │
│   └── test/
│       └── java/
│           └── org/example/
│               ├── controller/
│               ├── integration/
│               │   └── CapitalGainsFlowIntegrationTest.java
│               ├── service/
│               └── view/
│                   └── table/
│
├── pom.xml
└── README.md
```

## Tecnologias Utilizadas

- **Java 21:** Linguagem utilizada no desenvolvimento da aplicação, aproveitando seus recursos de orientação a objetos, records, enums e tratamento de exceções.

- **Java Swing:** Biblioteca gráfica utilizada para construir a interface desktop, permitindo o cadastro de operações e a visualização dos resultados financeiros.

- **Apache Maven:** Ferramenta de automação de build e gerenciamento das dependências do projeto.

- **BigDecimal:** Classe utilizada para realizar operações aritméticas financeiras com precisão decimal, evitando problemas de representação associados ao uso de `double`.

- **JUnit 5:** Framework utilizado para implementar testes unitários e de integração, verificando as regras de negócio e a colaboração entre os componentes.

## Arquitetura

O projeto utiliza uma organização baseada no padrão **MVC (Model-View-Controller)**, complementada por uma camada de serviços responsável pelas regras de negócio.

### Organização das Camadas

| Camada | Responsabilidade |
|---|---|
| **Model** | Representação dos dados e estruturas utilizadas pela aplicação. |
| **View** | Construção da interface gráfica e apresentação das informações ao usuário. |
| **Controller** | Intermediação entre a interface e os serviços de negócio. |
| **Service** | Implementação das regras de negócio e processamento financeiro. |
| **Interfaces** | Definição de contratos para desacoplamento entre componentes. |
| **Exception** | Exceções personalizadas para representar erros da aplicação. |
| **Enums** | Representação de tipos e classificações utilizadas no domínio. |

### Serviços

A lógica de negócio foi dividida em serviços com responsabilidades específicas:

- **CapitalGainsService:** Atua como Facade, coordenando o processamento das operações e a construção dos resultados da simulação.

- **PortfolioService:** Responsável pelo gerenciamento da carteira, incluindo compras, vendas, preço médio e quantidade de ações.

- **TaxService:** Responsável pelo cálculo dos impostos, controle de prejuízos acumulados, compensação de lucros e aplicação das regras de isenção.

- **OperationValidator:** Responsável pela validação das operações antes do início da simulação.

### Fluxo de Comunicação

```text
          Main
           |
           v
     MainFrame (View)
           |
           v
 SimulationController
           |
           v
   CapitalGainsService
       /         \
      v           v
PortfolioService  TaxService
      \           /
       v         v
      SimulationResult
           |
           v
     MainFrame (View)
           |
      +----+----+
      |         |
      v         v
   Results   Financial
    Panel     Summary
```

A camada de serviços não possui dependência de Swing, permitindo que as regras de negócio sejam reutilizadas em outras interfaces, como uma futura aplicação CLI ou API.

### Tipos de testes

- **Testes unitários:** Verificam isoladamente os serviços, validadores e modelos de tabela.
- **Testes de integração:** Verificam a comunicação entre Controller e serviços, utilizando suas implementações reais.

## Fluxo da Aplicação

1. O usuário informa o tipo da operação, o preço unitário e a quantidade de ações.
2. A operação é validada e adicionada à tabela de operações.
3. O usuário pode adicionar, remover ou limpar operações antes de executar a simulação.
4. Ao clicar em **Calcular simulação**, as operações são encaminhadas ao `SimulationController`.
5. O `CapitalGainsService` coordena o processamento através do `PortfolioService` e do `TaxService`.
6. Os resultados individuais de cada operação são apresentados na tabela de resultados.
7. O resumo financeiro apresenta a situação final da carteira e o total de impostos calculados.

**Observação:** A aplicação mantém os dados exclusivamente em memória. Não há persistência em banco de dados ou armazenamento de simulações entre execuções.