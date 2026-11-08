
# ADR-002 — Adoção da arquitetura MVC + Service

**Status:** Aceita

## Contexto

A aplicação possui responsabilidades distintas:

- Receber operações fornecidas pelo usuário.
- Interpretar os dados de entrada.
- Processar operações de compra e venda.
- Calcular preços médios, lucros, prejuízos e impostos.
- Apresentar os resultados.

Concentrar essas responsabilidades em uma única classe dificultaria a manutenção, os testes e a evolução da aplicação.

## Decisão

Adotar uma arquitetura baseada em **MVC (Model-View-Controller) com uma camada Service** para concentrar as regras de negócio.

A arquitetura será organizada em quatro responsabilidades principais.

### Model

Representa os dados e o estado do domínio.

Exemplos:

- `Operation`
- `Portfolio`
- `TaxResult`

O Model não deverá depender da interface gráfica.

### View

Responsável pela apresentação e interação com o usuário.

Exemplos:

- `MainFrame`
- `OperationPanel`
- `ResultPanel`

A View não deverá implementar cálculos financeiros ou regras de negócio.

### Controller

Responsável por receber eventos da View, coordenar as operações e encaminhar os resultados para apresentação.

Exemplo:

- `SimulationController`

O Controller não deverá implementar diretamente os cálculos de imposto.

### Service

Responsável por executar os casos de uso e concentrar as regras de negócio.

Exemplos:

- `CapitalGainsService`
- `TaxCalculationService`

Os serviços deverão trabalhar com os modelos do domínio, sem conhecer componentes Swing.

## Fluxo arquitetural

O fluxo principal será:

1. O usuário interage com a View.
2. A View comunica a ação ao Controller.
3. O Controller valida o fluxo de entrada e chama o Service.
4. O Service processa as operações utilizando os modelos do domínio.
5. O resultado retorna ao Controller.
6. O Controller atualiza a View.

Representação:

```text
           USUÁRIO
              |
              v
          +-------+
          | VIEW  |
          +-------+
              |
              v
        +------------+
        | CONTROLLER |
        +------------+
              |
              v
         +---------+
         | SERVICE |
         +---------+
              |
              v
         +---------+
         |  MODEL  |
         +---------+
              |
              v
        Resultado
              |
              v
        +------------+
        | CONTROLLER |
        +------------+
              |
              v
          +-------+
          | VIEW  |
          +-------+
```

**Observação:** o Model não chama o Controller. O desenho representa o fluxo de execução, não uma cadeia de dependências bidirecionais.

## Alternativas consideradas

| Alternativa | Justificativa |
|---|---|
| MVC tradicional sem Service | Poderia concentrar regras no Model ou Controller, aumentando suas responsabilidades. |
| MVP | É uma alternativa válida para aplicações Swing, mas adicionaria uma abstração de apresentação que não consideramos necessária neste escopo. |
| Arquitetura hexagonal | Oferece isolamento mais amplo, mas adicionaria abstrações desproporcionais ao tamanho do desafio. |
| MVC + Service | Escolhido por oferecer separação clara de responsabilidades sem introduzir complexidade excessiva. |

## Consequências positivas

- Responsabilidades bem definidas.
- Regras de negócio independentes da interface.
- Facilidade para realizar testes unitários.
- Possibilidade de reutilizar os serviços em outras interfaces.
- Estrutura simples e adequada ao tamanho do desafio.

## Consequências negativas

- Existência de classes adicionais para separar responsabilidades.
- Necessidade de manter os contratos entre Controller, Service e View bem definidos.

---