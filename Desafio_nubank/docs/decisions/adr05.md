

# ADR-005 — Representação de valores monetários e arredondamento

**Status:** Proposta aceita

## Contexto

O cálculo do preço médio ponderado e do imposto envolve operações decimais.

Tipos como `float` e `double` podem apresentar imprecisões em cálculos financeiros.

Além disso, o desafio exige arredondamento para duas casas decimais.

## Decisão

Utilizar `BigDecimal` para todos os valores monetários.

Adotar:

- Escala monetária de duas casas decimais.
- `RoundingMode.HALF_UP` como política de arredondamento.
- `BigDecimal` criado a partir de strings ou valores decimais seguros.
- `compareTo()` para comparações numéricas.

Exemplo:

```java
BigDecimal price = new BigDecimal("20.00");
BigDecimal quantity = new BigDecimal("10");

BigDecimal total = price.multiply(quantity);
```

Para divisão:

```java
BigDecimal average = total.divide(
    quantity,
    2,
    RoundingMode.HALF_UP
);
```

## Consequências

- Maior precisão nos cálculos.
- Controle explícito de escala e arredondamento.
- Código ligeiramente mais verboso do que operações com tipos primitivos.

---

# Resumo das decisões

| ADR | Decisão | Status |
|---|---|---|
| ADR-001 | Java Swing | Aceita |
| ADR-002 | MVC + Service | Aceita |
| ADR-003 | Java 21, Maven, Jackson, JUnit 5 e BigDecimal | Proposta aceita como baseline |
| ADR-004 | Interfaces úteis e injeção por construtor | Proposta aceita |
| ADR-005 | BigDecimal e arredondamento HALF_UP | Proposta aceita |
