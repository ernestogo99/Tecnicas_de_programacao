# Regras de Negócio — Capital Gains

## 1. Objetivo

Este documento descreve as regras de negócio que orientam o cálculo do imposto sobre operações de compra e venda de ações.

As regras foram extraídas do desafio original do Nubank. As decisões adicionais de implementação estão identificadas separadamente, para evitar confundir requisitos do desafio com escolhas técnicas do projeto.

## 2. Conceitos fundamentais

| Conceito | Descrição |
|---|---|
| Operação | Evento de compra (`buy`) ou venda (`sell`) de ações |
| Custo unitário | Preço pago ou recebido por ação na operação |
| Quantidade | Número de ações negociadas |
| Preço médio ponderado | Custo médio de aquisição das ações atualmente mantidas |
| Lucro | Resultado positivo de uma venda em relação ao preço médio |
| Prejuízo | Resultado negativo de uma venda em relação ao preço médio |
| Prejuízo acumulado | Soma dos prejuízos anteriores ainda não compensados |
| Valor bruto da venda | Custo unitário da venda multiplicado pela quantidade |
| Imposto | Valor devido sobre o lucro tributável de uma operação |

## 3. Regras gerais

### RN01 — Tipos de operação

O sistema deve reconhecer dois tipos de operação:

- `buy`: compra de ações.
- `sell`: venda de ações.

As operações devem ser processadas na ordem em que foram recebidas.

### RN02 — Compra não gera imposto

Uma operação de compra nunca gera imposto.

O resultado associado a uma compra deve ser sempre:

`tax = 0`

### RN03 — Recalcular o preço médio ponderado

Sempre que ocorrer uma compra, o preço médio ponderado deve ser recalculado considerando:

- A quantidade de ações que já está na carteira.
- O preço médio ponderado atual.
- A quantidade de ações compradas.
- O custo unitário da nova compra.

Fórmula:

```text
PM_novo = ((Q_atual × PM_atual) + (Q_compra × C_compra)) / (Q_atual + Q_compra)
```

Onde:

- `PM_novo`: novo preço médio ponderado.
- `Q_atual`: quantidade atual de ações.
- `PM_atual`: preço médio atual.
- `Q_compra`: quantidade comprada.
- `C_compra`: custo unitário da compra.

**Exemplo:**

1. Compra de 10 ações por R$ 20,00.
2. Venda de 5 ações.
3. Compra de mais 5 ações por R$ 10,00.

Após a venda, restam 5 ações com preço médio de R$ 20,00.

O novo preço médio será:

```text
PM_novo = ((5 × 20) + (5 × 10)) / (5 + 5)
        = 150 / 10
        = 15
```

O novo preço médio será R$ 15,00.

### RN04 — Calcular o resultado de uma venda

O resultado financeiro de uma venda deve ser calculado comparando o custo unitário da venda com o preço médio ponderado atual.

Fórmula:

```text
Resultado = (C_venda - PM) × Q_venda
```

Onde:

- `C_venda`: custo unitário da venda.
- `PM`: preço médio ponderado atual.
- `Q_venda`: quantidade vendida.

A interpretação é:

| Resultado | Classificação |
|---|---|
| Maior que zero | Lucro |
| Igual a zero | Sem lucro ou prejuízo |
| Menor que zero | Prejuízo |

### RN05 — Venda com prejuízo

Quando o preço de venda for inferior ao preço médio ponderado, a operação gera prejuízo.

O sistema deve:

1. Calcular o prejuízo da operação.
2. Adicionar esse valor ao prejuízo acumulado.
3. Retornar imposto igual a zero.

O prejuízo acumulado poderá ser utilizado para compensar lucros tributáveis de operações futuras.

### RN06 — Acumular e compensar prejuízos

Os prejuízos anteriores devem ser utilizados para reduzir lucros tributáveis futuros.

A compensação pode ocorrer em várias operações, até que todo o prejuízo acumulado seja utilizado.

Quando houver lucro tributável:

```text
Lucro_tributavel = max(0, Lucro - Prejuizo_acumulado)
```

O prejuízo acumulado após a compensação será:

```text
Prejuizo_restante = max(0, Prejuizo_acumulado - Lucro)
```

**Exemplo:**

1. Uma venda gera prejuízo de R$ 25.000,00.
2. Uma venda posterior gera lucro de R$ 30.000,00.

Compensação:

```text
30.000 - 25.000 = 5.000
```

O lucro tributável será R$ 5.000,00.

O imposto será:

```text
5.000 × 20% = 1.000
```

O prejuízo acumulado será zerado.

### RN07 — Alíquota de imposto

A alíquota definida pelo desafio é de 20% sobre o lucro tributável.

```text
Imposto = Lucro_tributavel × 0,20
```

O imposto somente será cobrado quando houver lucro tributável e a operação não estiver abrangida pela regra de isenção.

### RN08 — Isenção para vendas de até R$ 20.000,00

Uma venda cujo valor bruto seja menor ou igual a R$ 20.000,00 não gera imposto.

O valor bruto deve ser calculado por:

```text
Valor_bruto = C_venda × Q_venda
```

A comparação deve considerar o valor bruto da operação, e não o lucro obtido.

Portanto:

```text
Valor_bruto ≤ 20.000
```

implica:

```text
Imposto = 0
```

**Importante:** a isenção não significa que o lucro será utilizado para compensar prejuízos acumulados.

### RN09 — Lucro em operação isenta não consome prejuízos

Quando uma venda estiver dentro do limite de isenção e gerar lucro, esse lucro não deve ser utilizado para reduzir o prejuízo acumulado.

O saldo de prejuízos permanece inalterado.

### RN10 — Prejuízo em operação isenta continua sendo acumulado

Quando uma venda estiver dentro do limite de isenção e gerar prejuízo, esse prejuízo deve ser adicionado ao saldo acumulado.

A regra de acumulação de prejuízos continua válida independentemente do valor bruto da venda.

### RN11 — Atualização da carteira após vendas

Uma venda reduz a quantidade de ações mantidas na carteira.

O preço médio ponderado das ações restantes não é alterado diretamente pela venda.

Quando todas as ações forem vendidas, a quantidade em carteira será zero.

Se ocorrer uma nova compra posteriormente, o preço médio deverá considerar apenas a nova posição, passando a corresponder ao custo unitário dessa compra.

### RN12 — Simulações independentes

Cada simulação deve começar com estado próprio e vazio.

O processamento de uma simulação não pode interferir em outra.

Isso significa que cada nova simulação deve iniciar com:

- Quantidade de ações igual a zero.
- Preço médio ponderado inicial igual a zero.
- Prejuízo acumulado igual a zero.

## 4. Fluxo de processamento

Cada operação deve ser processada individualmente, mantendo o estado atualizado da carteira e do prejuízo acumulado.

### 4.1. Fluxo de compra

1. Receber a operação `buy`.
2. Consultar a quantidade atual da carteira.
3. Calcular o novo preço médio ponderado.
4. Atualizar a quantidade de ações.
5. Retornar imposto igual a zero.

### 4.2. Fluxo de venda

1. Receber a operação `sell`.
2. Calcular o valor bruto da venda.
3. Calcular o resultado financeiro com base no preço médio atual.
4. Atualizar a quantidade de ações da carteira.
5. Identificar se houve lucro, prejuízo ou resultado zero.
6. Aplicar as regras de isenção e compensação de prejuízos.
7. Calcular o imposto, quando devido.
8. Registrar o imposto correspondente à operação.

### 4.3. Ordem de decisão para vendas

A seguinte ordem deve ser respeitada:

1. Se houver prejuízo, acumular o valor e não cobrar imposto.
2. Se o resultado for zero, não cobrar imposto.
3. Se o valor bruto da venda for menor ou igual a R$ 20.000,00, não cobrar imposto e não consumir prejuízos acumulados.
4. Caso contrário, compensar o lucro com os prejuízos acumulados.
5. Aplicar 20% sobre o lucro restante, se houver.

## 5. Casos de validação do desafio

Os nove casos abaixo são os exemplos apresentados na documentação original e devem ser reproduzidos pelos testes automatizados.

| Caso | Cenário principal | Resultado esperado |
|---|---|---|
| 1 | Vendas com valor bruto inferior a R$ 20.000,00 | Todos os impostos iguais a zero |
| 2 | Lucro seguido de prejuízo | Imposto de R$ 10.000,00 na segunda operação |
| 3 | Prejuízo compensado por lucro posterior | Imposto de R$ 1.000,00 na última operação |
| 4 | Recalcular preço médio após compras | Impostos iguais a zero |
| 5 | Venda após recalcular preço médio | Imposto de R$ 10.000,00 na última operação |
| 6 | Prejuízo compensado em várias vendas | Imposto de R$ 3.000,00 na última operação |
| 7 | Nova compra após liquidar a posição | Impostos de R$ 3.700,00 e R$ 0,00 nas duas últimas vendas |
| 8 | Operações sucessivas com posições independentes | Impostos de R$ 80.000,00 e R$ 60.000,00 nas vendas |
| 9 | Isenção sem consumir prejuízo acumulado | Impostos de R$ 1.000,00 e R$ 2.400,00 nas duas últimas vendas |

Os dados completos de entrada e saída desses exemplos devem ser mantidos como referência na suíte de testes.

## 6. Invariantes do domínio

As seguintes condições devem permanecer verdadeiras durante o processamento:

- A quantidade de ações em carteira nunca deve ser negativa.
- O prejuízo acumulado nunca deve ser negativo.
- O imposto nunca deve ser negativo.
- Operações de compra sempre retornam imposto zero.
- Uma venda com prejuízo sempre retorna imposto zero.
- O preço médio ponderado deve refletir as compras que compõem a posição atual.
- Uma venda não altera diretamente o preço médio das ações restantes.
- O estado de uma simulação não pode interferir em outra.
- Cada operação processada deve possuir exatamente um resultado de imposto.

## 7. Precisão e arredondamento

O desafio determina que os valores decimais sejam arredondados para duas casas decimais.

Exemplo apresentado na documentação:

```text
((10 × 20) + (5 × 10)) / 15
= 250 / 15
= 16,666...
```

Resultado arredondado:

`16.67`

### Decisão de implementação proposta

- Utilizar `BigDecimal` para representar valores monetários.
- Evitar `double` e `float` nos cálculos financeiros.
- Utilizar duas casas decimais na representação dos valores monetários.
- Adotar `RoundingMode.HALF_UP` como regra de arredondamento do projeto.

**Ponto de atenção:** o documento original determina duas casas decimais, mas não especifica o modo de arredondamento nem todos os detalhes de precisão intermediária. A escolha de `HALF_UP` é uma decisão técnica do projeto, não uma regra expressa pelo Nubank.

## 8. Premissas e limitações

### Premissas expressas no desafio

- Nenhuma venda excederá a quantidade de ações disponível.
- As operações estarão ordenadas cronologicamente.
- Os valores monetários terão duas casas decimais.
- A entrada original será composta por operações JSON válidas.
- O estado da aplicação será mantido em memória.
- Não será necessário utilizar banco de dados externo.

### Tratamentos adicionais do projeto

Embora o desafio permita assumir que as entradas são válidas, a aplicação com Swing terá tratamento para situações como:

- JSON malformado.
- Tipo de operação desconhecido.
- Quantidade inválida.
- Custo unitário inválido.
- Tentativa de vender mais ações do que as disponíveis.

Esses tratamentos são melhorias de robustez da aplicação e não devem alterar os resultados dos casos oficiais.

## 9. Referência para implementação

As regras deste documento serão a referência para a implementação dos serviços de domínio e para a construção dos testes automatizados.

A lógica financeira deve permanecer independente da interface Swing.

Assim, a aplicação poderá apresentar os resultados graficamente sem que a camada de visualização conheça ou implemente regras de cálculo de imposto.

Documentação de referência: *Code Challenge: Ganho de Capital*, Nubank, páginas 1 a 11.