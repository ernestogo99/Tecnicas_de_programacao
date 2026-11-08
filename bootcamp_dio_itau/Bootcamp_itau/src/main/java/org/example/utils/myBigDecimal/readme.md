# Java: BigDecimal

## 📚 Sumário

1. [O que é BigDecimal?](#-o-que-é-bigdecimal)
2. [Problema de precisão com double e float](#-problema-de-precisão-com-double-e-float)
3. [Criando objetos BigDecimal](#-criando-objetos-bigdecimal)
4. [Principais métodos](#-principais-métodos)
5. [Operações matemáticas](#-operações-matemáticas)
6. [Arredondamento e escala](#-arredondamento-e-escala)
7. [Comparação entre BigDecimal](#-comparação-entre-bigdecimal)
8. [BigDecimal com Spring Boot](#-bigdecimal-com-spring-boot)
9. [Boas práticas](#-boas-práticas)
---

# 💰 O que é BigDecimal?

`BigDecimal` é uma classe do pacote `java.math` utilizada para representar números decimais com precisão arbitrária.

É especialmente importante em aplicações que exigem precisão nos cálculos, como:

- Sistemas bancários;
- Aplicações financeiras;
- Cálculo de juros;
- Valores monetários;
- Impostos;
- Descontos;
- Contabilidade.

Diferentemente de `double` e `float`, o `BigDecimal` permite controlar a precisão e o arredondamento das operações decimais.

```java
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal valor = new BigDecimal("150.75");

        System.out.println(valor);
    }
}
```

## Características

- É uma classe imutável.
- Permite representar números decimais com precisão arbitrária.
- Possui métodos próprios para operações matemáticas.
- Permite definir regras de arredondamento.
- Permite controlar a escala decimal.
- É amplamente utilizado em sistemas financeiros.

**Importante:** por ser imutável, as operações retornam novos objetos. O valor original não é modificado.

---

# ⚠️ Problema de precisão com double e float

Os tipos primitivos `double` e `float` utilizam representação binária de ponto flutuante.

Alguns valores decimais não podem ser representados exatamente nessa forma, provocando pequenas diferenças nos resultados.

Exemplo:

```java
public class Main {
    public static void main(String[] args) {
        double valor1 = 0.1;
        double valor2 = 0.2;

        System.out.println(valor1 + valor2);
    }
}
```

Resultado:

```text
0.30000000000000004
```

Embora matematicamente a soma seja `0.3`, a representação em ponto flutuante pode produzir esse resultado aproximado.

### Utilizando BigDecimal

```java
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal valor1 = new BigDecimal("0.1");
        BigDecimal valor2 = new BigDecimal("0.2");

        BigDecimal resultado = valor1.add(valor2);

        System.out.println(resultado); // 0.3
    }
}
```

Nesse exemplo, o resultado é exatamente `0.3`.

---

# 🏗️ Criando objetos BigDecimal

Existem diferentes maneiras de criar objetos `BigDecimal`.

## 1. Utilizando String (recomendado)

```java
BigDecimal valor = new BigDecimal("10.50");
```

Essa é a maneira recomendada para representar valores decimais, pois evita importar imprecisões de tipos de ponto flutuante.

## 2. Utilizando int ou long

```java
BigDecimal valor1 = BigDecimal.valueOf(10);
BigDecimal valor2 = BigDecimal.valueOf(150);
```

Também é possível utilizar o método `valueOf()` para números inteiros.

## 3. Utilizando double (evite)

```java
BigDecimal valor = new BigDecimal(0.1);

System.out.println(valor);
```

O resultado pode ser algo semelhante a:

```text
0.1000000000000000055511151231257827021181583404541015625
```

Isso acontece porque o valor `double` já possui uma representação aproximada antes de ser convertido para `BigDecimal`.

**Recomendação:** utilize `new BigDecimal("0.1")` ou `BigDecimal.valueOf(0.1)`.

## 4. Utilizando constantes

A classe disponibiliza algumas constantes úteis:

```java
BigDecimal zero = BigDecimal.ZERO;
BigDecimal um = BigDecimal.ONE;
BigDecimal dez = BigDecimal.TEN;
```

Essas constantes evitam a necessidade de criar objetos repetidamente para esses valores.

---

# 🔧 Principais métodos

| Método | Descrição |
|---|---|
| `add()` | Realiza uma adição |
| `subtract()` | Realiza uma subtração |
| `multiply()` | Realiza uma multiplicação |
| `divide()` | Realiza uma divisão |
| `remainder()` | Retorna o resto da divisão |
| `pow()` | Calcula uma potência inteira |
| `abs()` | Retorna o valor absoluto |
| `negate()` | Inverte o sinal do valor |
| `compareTo()` | Compara dois valores numericamente |
| `equals()` | Compara valor e escala |
| `setScale()` | Define a escala decimal e o arredondamento |
| `stripTrailingZeros()` | Remove zeros desnecessários no final da representação |
| `toPlainString()` | Converte para String sem notação científica |
| `toString()` | Converte para String, podendo utilizar notação científica |
| `min()` | Retorna o menor valor entre dois BigDecimals |
| `max()` | Retorna o maior valor entre dois BigDecimals |

---

# ➕ Operações matemáticas

Diferentemente dos tipos primitivos, não podemos utilizar diretamente os operadores `+`, `-`, `*` e `/` entre objetos `BigDecimal`.

Devemos utilizar os métodos disponibilizados pela classe.

## Adição — add()

```java
BigDecimal valor1 = new BigDecimal("10.50");
BigDecimal valor2 = new BigDecimal("5.25");

BigDecimal resultado = valor1.add(valor2);

System.out.println(resultado); // 15.75
```

## Subtração — subtract()

```java
BigDecimal valor1 = new BigDecimal("20.00");
BigDecimal valor2 = new BigDecimal("8.50");

BigDecimal resultado = valor1.subtract(valor2);

System.out.println(resultado); // 11.50
```

## Multiplicação — multiply()

```java
BigDecimal valor1 = new BigDecimal("10.00");
BigDecimal valor2 = new BigDecimal("3");

BigDecimal resultado = valor1.multiply(valor2);

System.out.println(resultado); // 30.00
```

## Divisão — divide()

```java
BigDecimal valor1 = new BigDecimal("10");
BigDecimal valor2 = new BigDecimal("2");

BigDecimal resultado = valor1.divide(valor2);

System.out.println(resultado); // 5
```

### Atenção à divisão

Algumas divisões possuem resultados decimais infinitos.

Por exemplo:

```java
BigDecimal valor1 = new BigDecimal("10");
BigDecimal valor2 = new BigDecimal("3");

BigDecimal resultado = valor1.divide(valor2);
```

Esse código lança `ArithmeticException`, pois o resultado não possui uma representação decimal exata e não foi definida uma regra de arredondamento.

Para resolver:

```java
BigDecimal resultado = valor1.divide(
    valor2,
    2,
    RoundingMode.HALF_UP
);

System.out.println(resultado); // 3.33
```

Nesse exemplo:

- `2`: quantidade de casas decimais.
- `RoundingMode.HALF_UP`: arredonda para o valor mais próximo; em caso de empate, afasta de zero.

---

# 🎯 Arredondamento e escala

Uma das principais vantagens do `BigDecimal` é permitir controlar a quantidade de casas decimais e a regra de arredondamento.

## O que é scale?

A escala representa a quantidade de casas decimais de um `BigDecimal`.

```java
BigDecimal valor = new BigDecimal("150.750");

System.out.println(valor.scale()); // 3
```

Nesse exemplo, o número possui três casas decimais.

## Método setScale()

Permite definir a quantidade de casas decimais de um valor.

```java
BigDecimal valor = new BigDecimal("10.5678");

BigDecimal resultado = valor.setScale(
    2,
    RoundingMode.HALF_UP
);

System.out.println(resultado); // 10.57
```

**Importante:** como `BigDecimal` é imutável, `setScale()` retorna um novo objeto.

## Principais modos de arredondamento

| Modo | Comportamento |
|---|---|
| `RoundingMode.UP` | Arredonda para longe de zero, quando necessário |
| `RoundingMode.DOWN` | Descarta as casas excedentes, em direção a zero |
| `RoundingMode.CEILING` | Arredonda em direção ao infinito positivo |
| `RoundingMode.FLOOR` | Arredonda em direção ao infinito negativo |
| `RoundingMode.HALF_UP` | Arredonda para o mais próximo; empate afasta de zero |
| `RoundingMode.HALF_DOWN` | Arredonda para o mais próximo; empate aproxima de zero |
| `RoundingMode.HALF_EVEN` | Arredonda para o mais próximo; empate para o número par |
| `RoundingMode.UNNECESSARY` | Exige que não seja necessário arredondar |

Exemplo:

```java
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        BigDecimal valor = new BigDecimal("10.555");

        System.out.println(
            valor.setScale(2, RoundingMode.HALF_UP)
        ); // 10.56

        System.out.println(
            valor.setScale(2, RoundingMode.DOWN)
        ); // 10.55

        System.out.println(
            valor.setScale(2, RoundingMode.HALF_EVEN)
        ); // 10.56
    }
}
```

---

# ⚖️ Comparação entre BigDecimal

Uma particularidade importante da classe é a diferença entre `equals()` e `compareTo()`.

## Método equals()

Compara o valor numérico e a escala.

```java
BigDecimal valor1 = new BigDecimal("10.0");
BigDecimal valor2 = new BigDecimal("10.00");

System.out.println(valor1.equals(valor2)); // false
```

Apesar de os valores serem numericamente equivalentes, possuem escalas diferentes.

- `10.0`: escala 1.
- `10.00`: escala 2.

## Método compareTo()

Compara os valores numericamente, ignorando diferenças de escala.

```java
BigDecimal valor1 = new BigDecimal("10.0");
BigDecimal valor2 = new BigDecimal("10.00");

System.out.println(valor1.compareTo(valor2)); // 0
```

O método retorna:

| Retorno | Significado |
|---|---|
| `-1` | Primeiro valor menor que o segundo |
| `0` | Valores numericamente iguais |
| `1` | Primeiro valor maior que o segundo |

Exemplo:

```java
BigDecimal valor1 = new BigDecimal("15.50");
BigDecimal valor2 = new BigDecimal("10.00");

if (valor1.compareTo(valor2) > 0) {
    System.out.println("Valor 1 é maior");
}
```

**Dica para provas:**

- `equals()` → compara valor e escala.
- `compareTo()` → compara apenas o valor numérico.

---

# 🏦 BigDecimal com Spring Boot

Em aplicações Spring Boot, `BigDecimal` é bastante utilizado para representar valores monetários em entidades e DTOs.

Exemplo de entidade:

```java
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(precision = 15, scale = 2)
    private BigDecimal preco;

    // Getters e Setters
}
```

### Entendendo precision e scale

Na anotação `@Column`:

- `precision`: quantidade total de dígitos.
- `scale`: quantidade de dígitos depois da vírgula decimal.

Exemplo:

```java
@Column(precision = 15, scale = 2)
private BigDecimal preco;
```

Permite armazenar até 15 dígitos no total, sendo 2 depois do separador decimal.

Exemplo de valor:

```text
1234567890123.45
```

A precisão e a escala devem ser compatíveis com o tipo de coluna utilizado no banco de dados.

---

# 💡 Boas práticas

1. **Prefira String na criação:** utilize `new BigDecimal("10.50")` para evitar imprecisões.
2. **Evite utilizar double diretamente:** a conversão pode carregar imprecisões da representação binária.
3. **Utilize os métodos matemáticos:** `add()`, `subtract()`, `multiply()` e `divide()`.
4. **Defina o arredondamento:** especialmente em operações financeiras.
5. **Lembre-se da imutabilidade:** atribua o resultado das operações a uma variável.
6. **Utilize compareTo() para comparações numéricas:** especialmente quando a escala pode variar.
7. **Defina precision e scale no banco:** garanta que os valores sejam armazenados adequadamente.
8. **Não confunda escala com precisão:** escala é a quantidade de casas decimais; precisão é a quantidade total de dígitos.



# 📌 Resumo

| Conceito | Definição |
|---|---|
| `BigDecimal` | Classe para representação de números decimais com precisão arbitrária |
| `add()` | Adição |
| `subtract()` | Subtração |
| `multiply()` | Multiplicação |
| `divide()` | Divisão |
| `setScale()` | Define a escala decimal |
| `RoundingMode` | Define a regra de arredondamento |
| `precision()` | Retorna a quantidade total de dígitos de precisão |
| `scale()` | Retorna a escala decimal |
| `equals()` | Compara valor e escala |
| `compareTo()` | Compara valores numericamente |
| `valueOf()` | Cria um BigDecimal a partir de um valor suportado |

**Regra para memorizar:**

- `double` e `float` → ponto flutuante, com possíveis imprecisões.
- `BigDecimal` → precisão decimal arbitrária e controle de arredondamento.
- `equals()` → compara valor e escala.
- `compareTo()` → compara valor numérico.
- `setScale()` → controla casas decimais.

**Dica para provas:** lembre-se de que `BigDecimal` é imutável e que `divide()` pode lançar `ArithmeticException` quando o resultado é uma dízima decimal e nenhuma regra de arredondamento é fornecida.