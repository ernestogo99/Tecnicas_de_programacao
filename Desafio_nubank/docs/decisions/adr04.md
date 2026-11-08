
# ADR-004 — Uso de interfaces e injeção por construtor

**Status:** Proposta aceita

## Contexto

A aplicação terá componentes com responsabilidades distintas e algumas dependências entre camadas.

É importante permitir substituição de implementações e facilitar os testes sem introduzir um container de injeção de dependências.

## Decisão

Utilizar interfaces somente quando representarem contratos úteis entre componentes.

As dependências serão fornecidas por meio de injeção por construtor.

Interfaces inicialmente previstas:

| Interface | Implementação inicial | Responsabilidade |
|---|---|---|
| `OperationParser` | `JacksonOperationParser` | Interpretar operações recebidas em JSON. |
| `TaxCalculator` | `CapitalGainsCalculator` | Executar os cálculos de imposto. |

As implementações serão criadas e conectadas manualmente na inicialização da aplicação.

Não será utilizado Spring ou outro container de injeção de dependências.

## Exemplo conceitual

```java
public interface OperationParser {
    List<Operation> parse(String json);
}
```

Implementação:

```java
public class JacksonOperationParser implements OperationParser {
    // Implementação utilizando Jackson
}
```

Injeção por construtor:

```java
public class SimulationController {

    private final OperationParser parser;
    private final CapitalGainsService service;

    public SimulationController(
        OperationParser parser,
        CapitalGainsService service
    ) {
        this.parser = parser;
        this.service = service;
    }
}
```

## Consequências

- Menor acoplamento entre componentes.
- Facilidade para substituir implementações.
- Testes mais simples.
- Ausência de infraestrutura adicional.

**Restrição:** não criar interfaces para todas as classes. Uma interface deverá existir quando houver uma necessidade real de abstração, substituição ou isolamento de dependência.

---