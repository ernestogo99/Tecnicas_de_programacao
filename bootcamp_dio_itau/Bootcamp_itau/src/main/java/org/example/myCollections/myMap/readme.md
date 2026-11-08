# Map e Wrapper Classes em Java

## Sumário

- [1. Introdução](#1-introdução)
- [2. Map em Java](#2-map-em-java)
    - [2.1. O que é Map?](#21-o-que-é-map)
    - [2.2. Como declarar um Map](#22-como-declarar-um-map)
    - [2.3. Principais implementações](#23-principais-implementações)
    - [2.4. Principais métodos](#24-principais-métodos)
    - [2.5. Percorrendo um Map](#25-percorrendo-um-map)
    - [2.6. Diferenças entre Map, List e Set](#26-diferenças-entre-map-list-e-set)
- [3. Wrapper Classes](#3-wrapper-classes)
    - [3.1. O que são Wrapper Classes?](#31-o-que-são-wrapper-classes)
    - [3.2. Tipos primitivos e wrappers](#32-tipos-primitivos-e-wrappers)
    - [3.3. Autoboxing e Unboxing](#33-autoboxing-e-unboxing)
    - [3.4. Conversão de Strings para números](#34-conversão-de-strings-para-números)
    - [3.5. Comparação entre wrappers](#35-comparação-entre-wrappers)
    - [3.6. Wrappers e Collections](#36-wrappers-e-collections)
- [4. Relação entre Map e Wrappers](#4-relação-entre-map-e-wrappers)
- [5. Boas práticas](#5-boas-práticas)
- [6. Resumo](#6-resumo)

---

## 1. Introdução

O Java Collections Framework oferece estruturas para armazenar e manipular dados. Entre elas, temos o `Map`, utilizado para associar chaves a valores.

Além disso, Java possui tipos primitivos, como `int`, `double` e `boolean`, e classes correspondentes chamadas **Wrapper Classes**, que representam esses valores como objetos.

Neste material, estudaremos:

- Como armazenar dados utilizando `Map`.
- As diferenças entre `HashMap`, `LinkedHashMap` e `TreeMap`.
- Como adicionar, consultar, atualizar e remover elementos.
- O que são Wrapper Classes e por que existem.
- Como funcionam autoboxing e unboxing.
- Como utilizar wrappers em coleções e mapas.

---

# 2. Map em Java

## 2.1. O que é Map?

`Map` é uma interface do pacote `java.util` que representa uma estrutura de dados baseada em pares de **chave e valor** (*key-value*).

Cada chave identifica um valor associado.

Por exemplo, podemos armazenar estudantes e suas respectivas notas:

| Chave | Valor |
|---|---:|
| Ana | 9.5 |
| Carlos | 8.0 |
| Maria | 7.5 |

Nesse exemplo, o nome do estudante é a chave, e a nota é o valor.

Diferentemente de uma `List`, um `Map` não utiliza índices numéricos para acessar os elementos. A consulta é realizada pela chave.

Exemplo:

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Double> notas = new HashMap<>();

        notas.put("Ana", 9.5);
        notas.put("Carlos", 8.0);
        notas.put("Maria", 7.5);

        System.out.println(notas.get("Ana")); // 9.5
    }
}
```

Observe a declaração:

```java
Map<String, Double> notas = new HashMap<>();
```

- `Map`: interface que define o comportamento do mapa.
- `String`: tipo das chaves.
- `Double`: tipo dos valores.
- `HashMap`: implementação concreta da interface.

### Principais características

- Cada chave é única segundo as regras de igualdade da implementação.
- Valores podem se repetir.
- Uma chave existente pode receber um novo valor.
- O acesso aos valores é realizado por meio das chaves.
- A ordem dos elementos depende da implementação utilizada.

**Importante:** se adicionarmos novamente uma chave existente, o valor anterior será substituído.

```java
Map<String, Integer> idades = new HashMap<>();

idades.put("Ana", 20);
idades.put("Ana", 25);

System.out.println(idades.get("Ana")); // 25
System.out.println(idades.size());     // 1
```

O mapa continua tendo apenas uma chave chamada `"Ana"`.

## 2.2. Como declarar um Map

A declaração normalmente utiliza a interface `Map` e uma implementação concreta.

```java
Map<Integer, String> alunos = new HashMap<>();
```

Nesse caso:

- `Integer`: tipo da chave.
- `String`: tipo do valor.
- `HashMap`: implementação utilizada.

Podemos utilizar diferentes tipos de dados:

```java
Map<String, Integer> estoque = new HashMap<>();
Map<Integer, String> usuarios = new HashMap<>();
Map<String, Boolean> status = new HashMap<>();
Map<String, Double> precos = new HashMap<>();
```

Observe que as chaves e os valores utilizam tipos de referência. Por isso, não podemos declarar:

```java
// Não compila:
Map<int, String> dados;
```

O correto é utilizar o wrapper:

```java
Map<Integer, String> dados = new HashMap<>();
```

Esse conceito será explicado na seção sobre Wrapper Classes.

## 2.3. Principais implementações

As implementações mais comuns de `Map` são `HashMap`, `LinkedHashMap` e `TreeMap`.

| Implementação | Organização | Característica |
|---|---|---|
| `HashMap` | Tabela hash | Não garante ordem de iteração |
| `LinkedHashMap` | Tabela hash e estrutura encadeada | Preserva a ordem de inserção por padrão |
| `TreeMap` | Árvore balanceada | Mantém as chaves ordenadas |

### HashMap

`HashMap` é uma implementação de `Map` baseada em tabela hash.

Características:

- Não garante uma ordem específica de iteração.
- Não permite chaves duplicadas.
- Permite uma chave `null` e valores `null`.
- Oferece desempenho médio geralmente constante para operações básicas, considerando uma boa distribuição dos códigos hash.

Exemplo:

```java
Map<String, Integer> estoque = new HashMap<>();

estoque.put("Teclado", 10);
estoque.put("Mouse", 15);
estoque.put("Monitor", 5);

System.out.println(estoque.get("Mouse")); // 15
```

**Quando utilizar?**

Utilize `HashMap` quando precisar associar chaves a valores sem depender de uma ordem específica.

### LinkedHashMap

`LinkedHashMap` mantém a ordem de inserção das entradas por padrão.

Exemplo:

```java
Map<Integer, String> alunos = new LinkedHashMap<>();

alunos.put(3, "Carlos");
alunos.put(1, "Ana");
alunos.put(2, "Maria");

System.out.println(alunos);
// {3=Carlos, 1=Ana, 2=Maria}
```

As chaves não são ordenadas numericamente. A ordem apresentada corresponde à ordem de inserção.

**Quando utilizar?**

Utilize `LinkedHashMap` quando precisar manter uma ordem previsível de iteração, especialmente a ordem de inserção.

### TreeMap

`TreeMap` mantém as entradas ordenadas pelas chaves, utilizando a ordem natural das chaves ou um `Comparator`.

Exemplo:

```java
Map<Integer, String> alunos = new TreeMap<>();

alunos.put(3, "Carlos");
alunos.put(1, "Ana");
alunos.put(2, "Maria");

System.out.println(alunos);
// {1=Ana, 2=Maria, 3=Carlos}
```

Características:

- Ordena as entradas pelas chaves.
- As operações básicas têm custo tipicamente \(O(\log n)\).
- Não aceita chaves `null` com o comparador natural.
- Pode aceitar valores `null`.

**Quando utilizar?**

Utilize `TreeMap` quando precisar consultar ou percorrer as entradas em ordem de chave.

## 2.4. Principais métodos

Considere:

```java
Map<String, Integer> estoque = new HashMap<>();
```

| Método | Finalidade |
|---|---|
| `put(chave, valor)` | Adiciona uma entrada ou substitui seu valor |
| `get(chave)` | Retorna o valor associado à chave |
| `getOrDefault(chave, valorPadrao)` | Retorna o valor ou um padrão caso a chave não exista |
| `containsKey(chave)` | Verifica se a chave existe |
| `containsValue(valor)` | Verifica se algum valor corresponde ao informado |
| `remove(chave)` | Remove uma entrada pela chave |
| `putIfAbsent(chave, valor)` | Insere se a chave não possuir valor associado |
| `replace(chave, valor)` | Substitui o valor de uma chave existente |
| `size()` | Retorna a quantidade de entradas |
| `isEmpty()` | Verifica se o mapa está vazio |
| `clear()` | Remove todas as entradas |
| `keySet()` | Retorna um conjunto com as chaves |
| `values()` | Retorna uma coleção com os valores |
| `entrySet()` | Retorna um conjunto de entradas de chave e valor |

Exemplo:

```java
Map<String, Integer> estoque = new HashMap<>();

estoque.put("Teclado", 10);
estoque.put("Mouse", 15);

System.out.println(estoque.get("Teclado")); // 10
System.out.println(estoque.containsKey("Mouse")); // true
System.out.println(estoque.getOrDefault("Monitor", 0)); // 0

estoque.put("Teclado", 8);

System.out.println(estoque.get("Teclado")); // 8

estoque.remove("Mouse");

System.out.println(estoque.size()); // 1
```

### Diferença entre `containsKey()` e `containsValue()`

- `containsKey()` verifica se uma chave está presente.
- `containsValue()` verifica se um valor está presente.

```java
Map<Integer, String> usuarios = new HashMap<>();

usuarios.put(1, "Ana");
usuarios.put(2, "Carlos");

System.out.println(usuarios.containsKey(1));       // true
System.out.println(usuarios.containsValue("Ana")); // true
```

### Diferença entre `get()` e `getOrDefault()`

Se uma chave não existir, `get()` normalmente retorna `null`.

```java
System.out.println(estoque.get("Monitor")); // null
```

Já `getOrDefault()` permite definir um valor padrão:

```java
System.out.println(estoque.getOrDefault("Monitor", 0)); // 0
```

Atenção: `getOrDefault()` também pode retornar `null` se a chave estiver explicitamente associada a `null`.

## 2.5. Percorrendo um Map

Existem diferentes maneiras de percorrer as entradas de um mapa.

### Percorrendo as chaves

```java
Map<String, Integer> notas = new HashMap<>();

notas.put("Ana", 9);
notas.put("Carlos", 8);
notas.put("Maria", 10);

for (String nome : notas.keySet()) {
    System.out.println(nome);
}
```

### Percorrendo os valores

```java
for (Integer nota : notas.values()) {
    System.out.println(nota);
}
```

### Percorrendo chaves e valores com entrySet()

Essa é uma abordagem muito útil quando precisamos dos dois elementos.

```java
for (Map.Entry<String, Integer> entrada : notas.entrySet()) {
    System.out.println(
        entrada.getKey() + ": " + entrada.getValue()
    );
}
```

- `getKey()`: retorna a chave.
- `getValue()`: retorna o valor.

### Utilizando forEach e expressão lambda

```java
notas.forEach((nome, nota) ->
    System.out.println(nome + ": " + nota)
);
```

Nesse exemplo, a expressão lambda recebe a chave e o valor de cada entrada.

## 2.6. Diferenças entre Map, List e Set

| Característica | List | Set | Map |
|---|---|---|---|
| Estrutura | Sequência de elementos | Conjunto de elementos únicos | Pares de chave e valor |
| Duplicatas | Permitidas | Não permitidas | Chaves únicas; valores podem repetir |
| Acesso por índice | Sim | Não | Não |
| Consulta por chave | Não como operação própria da interface | Não como operação própria da interface | Sim |
| Exemplos | `ArrayList` | `HashSet` | `HashMap` |

Exemplos de uso:

```java
List<String> nomes = new ArrayList<>();
nomes.add("Ana");
nomes.add("Ana"); // Duplicata permitida

Set<String> linguagens = new HashSet<>();
linguagens.add("Java");
linguagens.add("Java"); // Elemento repetido não é adicionado

Map<Integer, String> alunos = new HashMap<>();
alunos.put(1, "Ana");
alunos.put(2, "Carlos"); // Cada chave identifica um valor
```

**Observação:** `Map` faz parte do Java Collections Framework, mas não estende a interface `Collection`.

---

# 3. Wrapper Classes

## 3.1. O que são Wrapper Classes?

Wrapper Classes são classes que representam os tipos primitivos de Java como objetos.

Java possui tipos primitivos, como `int`, `double`, `boolean` e `char`. Cada um deles possui uma classe wrapper correspondente, como `Integer`, `Double`, `Boolean` e `Character`.

As wrappers permitem utilizar valores primitivos em contextos que exigem objetos, como coleções genéricas.

Exemplo:

```java
int idade = 25;

Integer idadeObjeto = Integer.valueOf(idade);
```

Nesse caso:

- `int` é um tipo primitivo.
- `Integer` é uma classe que representa um valor inteiro como objeto.

Os objetos wrapper são imutáveis: depois de criados, seu valor não pode ser alterado. Uma atribuição a uma variável wrapper passa a referenciar outro valor, em vez de modificar o objeto existente.

## 3.2. Tipos primitivos e wrappers

Java possui oito tipos primitivos e suas respectivas classes wrapper.

| Tipo primitivo | Wrapper Class |
|---|---|
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | `Integer` |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

Exemplo:

```java
int numero = 10;
Integer numeroObjeto = 10;

double preco = 19.99;
Double precoObjeto = 19.99;

boolean ativo = true;
Boolean ativoObjeto = true;
```

Observe que os tipos primitivos começam com letra minúscula, enquanto as classes wrapper utilizam a convenção PascalCase.

### Diferenças entre primitivos e wrappers

| Característica | Primitivo | Wrapper |
|---|---|---|
| Exemplo | `int` | `Integer` |
| É objeto? | Não | Sim |
| Pode ser `null`? | Não | Sim |
| Pode ser usado como tipo genérico? | Não | Sim |
| Possui métodos de classe? | Não | Sim |

Exemplo:

```java
int numero = 10;

// Não compila:
// List<int> numeros;

List<Integer> numeros = new ArrayList<>();
numeros.add(10);
```

## 3.3. Autoboxing e Unboxing

Java oferece conversões automáticas entre tipos primitivos e suas classes wrapper.

### Autoboxing

É a conversão automática de um tipo primitivo para sua classe wrapper correspondente.

```java
int numero = 10;

Integer numeroObjeto = numero;
```

O compilador realiza a conversão necessária para utilizar o valor como um `Integer`.

Também podemos escrever:

```java
Integer idade = 25;
```

### Unboxing

É a conversão automática de um objeto wrapper para o tipo primitivo correspondente.

```java
Integer numeroObjeto = 10;

int numero = numeroObjeto;
```

O valor armazenado no `Integer` é convertido para `int`.

### Exemplo completo

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numero = 10;

        // Autoboxing
        Integer numeroObjeto = numero;

        // Unboxing
        int outroNumero = numeroObjeto;

        List<Integer> numeros = new ArrayList<>();
        numeros.add(numero);

        int valor = numeros.get(0);

        System.out.println(numeroObjeto); // 10
        System.out.println(outroNumero);  // 10
        System.out.println(valor);        // 10
    }
}
```

### Cuidado com NullPointerException

Se um wrapper tiver valor `null`, tentar convertê-lo automaticamente para um primitivo causará `NullPointerException`.

```java
Integer numero = null;

// Causa NullPointerException:
int valor = numero;
```

Isso acontece porque não existe um valor inteiro primitivo que corresponda a `null`.

Quando um wrapper pode ser nulo, verifique-o antes de realizar o unboxing:

```java
Integer numero = null;

if (numero != null) {
    int valor = numero;
}
```

## 3.4. Conversão de Strings para números

As Wrapper Classes oferecem métodos para converter textos em valores numéricos.

### Integer.parseInt()

Retorna um `int` primitivo.

```java
String texto = "123";

int numero = Integer.parseInt(texto);

System.out.println(numero + 10); // 133
```

### Integer.valueOf()

Retorna um objeto `Integer`.

```java
String texto = "123";

Integer numero = Integer.valueOf(texto);

System.out.println(numero); // 123
```

### Double.parseDouble()

Converte um texto para `double`.

```java
String texto = "19.99";

double preco = Double.parseDouble(texto);

System.out.println(preco); // 19.99
```

### Outras conversões

| Método | Resultado |
|---|---|
| `Integer.parseInt(texto)` | `int` |
| `Integer.valueOf(texto)` | `Integer` |
| `Long.parseLong(texto)` | `long` |
| `Double.parseDouble(texto)` | `double` |
| `Float.parseFloat(texto)` | `float` |
| `Boolean.parseBoolean(texto)` | `boolean` |

**Importante:** passar um texto inválido para métodos de conversão numérica pode causar `NumberFormatException`.

```java
String texto = "abc";

// Causa NumberFormatException:
int numero = Integer.parseInt(texto);
```

Para entradas de usuário, valide o conteúdo ou trate a exceção quando necessário.

## 3.5. Comparação entre wrappers

Um erro comum é utilizar `==` para comparar objetos wrapper.

Veja:

```java
Integer a = 1000;
Integer b = 1000;

System.out.println(a == b);      // Geralmente false
System.out.println(a.equals(b)); // true
```

O operador `==`, quando aplicado a referências de objetos, verifica se ambas apontam para o mesmo objeto.

O método `equals()` verifica a igualdade de valores conforme a implementação da classe.

Alguns valores inteiros pequenos podem ser armazenados em cache pelo Java, fazendo com que certas comparações com `==` retornem `true`. Por isso, não devemos utilizar esse comportamento como regra geral.

**Regra prática:** para comparar o valor de wrappers, prefira `equals()` ou converta para um tipo primitivo quando isso for adequado e seguro.

Se o wrapper puder ser `null`, lembre-se de que chamar `equals()` diretamente nele pode causar `NullPointerException`.

## 3.6. Wrappers e Collections

As coleções genéricas do Java trabalham com tipos de referência, não diretamente com tipos primitivos.

Por isso, utilizamos wrappers para armazenar números e valores booleanos em listas, conjuntos e mapas.

Exemplo com List:

```java
List<Integer> numeros = new ArrayList<>();

numeros.add(10);
numeros.add(20);
numeros.add(30);
```

Exemplo com Set:

```java
Set<Double> precos = new HashSet<>();

precos.add(19.99);
precos.add(29.99);
```

Exemplo com Map:

```java
Map<String, Integer> estoque = new HashMap<>();

estoque.put("Teclado", 10);
estoque.put("Mouse", 15);
```

No último exemplo, `String` representa o tipo da chave e `Integer` representa o tipo do valor.

---

# 4. Relação entre Map e Wrappers

`Map` e Wrapper Classes são frequentemente utilizados juntos.

Imagine que uma aplicação precise armazenar a quantidade de produtos disponíveis em um estoque.

Podemos utilizar:

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> estoque = new HashMap<>();

        estoque.put("Teclado", 10);
        estoque.put("Mouse", 20);
        estoque.put("Monitor", 5);

        Integer quantidade = estoque.get("Teclado");

        System.out.println("Quantidade: " + quantidade);
        // Quantidade: 10
    }
}
```

Nesse exemplo:

- `Map` organiza os dados em pares de chave e valor.
- `String` identifica os produtos.
- `Integer` armazena as quantidades.
- `HashMap` implementa a estrutura do mapa.

### Exemplo: contar a frequência de palavras

Um uso comum de `Map` é contar quantas vezes cada palavra aparece em um texto.

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] palavras = {
            "Java", "Python", "Java", "JavaScript", "Python", "Java"
        };

        Map<String, Integer> frequencias = new HashMap<>();

        for (String palavra : palavras) {
            frequencias.put(
                palavra,
                frequencias.getOrDefault(palavra, 0) + 1
            );
        }

        System.out.println(frequencias);
    }
}
```

Resultado conceitual:

```text
{Java=3, Python=2, JavaScript=1}
```

A ordem de exibição pode variar, pois utilizamos `HashMap`.

O método `getOrDefault()` retorna a frequência atual da palavra ou `0` quando ela ainda não existe. Em seguida, adicionamos `1` e atualizamos o mapa.

Esse exemplo demonstra como `Map<String, Integer>` permite associar uma palavra à sua quantidade de ocorrências.

---

# 5. Boas práticas

1. **Declare mapas pela interface:** prefira `Map<String, Integer> dados = new HashMap<>();`.

2. **Escolha a implementação adequada:** utilize `HashMap` quando a ordem não for importante, `LinkedHashMap` para preservar a inserção e `TreeMap` para ordenar as chaves.

3. **Utilize wrappers em generics:** use `Integer`, `Double` ou `Boolean` em vez dos respectivos tipos primitivos.

4. **Evite comparar wrappers com `==`:** utilize `equals()` para comparar valores de objetos.

5. **Tenha cuidado com wrappers nulos:** o unboxing de um valor `null` provoca `NullPointerException`.

6. **Valide entradas numéricas:** métodos como `Integer.parseInt()` podem lançar `NumberFormatException`.

7. **Conheça o comportamento de `put()`:** inserir uma chave existente substitui seu valor anterior.

8. **Use `entrySet()` para percorrer chaves e valores:** essa abordagem é apropriada quando precisamos dos dois dados durante a iteração.

9. **Entenda a diferença entre `get()` e `containsKey()`:** `get()` pode retornar `null` tanto quando a chave está ausente quanto quando está associada a `null`. Se essa distinção for necessária, verifique `containsKey()`.

10. **Evite wrappers desnecessários em cálculos intensivos:** quando o contexto permitir, tipos primitivos podem ser mais apropriados para cálculos numéricos.

---

# 6. Resumo

## Map

- Representa associações entre chaves e valores.
- Cada chave é única.
- Os valores podem se repetir.
- `put()` insere ou atualiza uma entrada.
- `get()` consulta um valor pela chave.
- `HashMap` não garante ordem de iteração.
- `LinkedHashMap` preserva a ordem de inserção por padrão.
- `TreeMap` mantém as chaves ordenadas.

## Wrapper Classes

- Representam tipos primitivos como objetos.
- Permitem utilizar valores em coleções genéricas.
- Exemplos: `Integer`, `Double`, `Boolean` e `Character`.
- Autoboxing converte automaticamente de primitivo para wrapper.
- Unboxing converte automaticamente de wrapper para primitivo.
- `equals()` é apropriado para comparar valores de objetos wrapper.
- Um wrapper `null` não pode ser convertido automaticamente em primitivo sem causar `NullPointerException`.

### Conceito fundamental

Utilize `Map` quando precisar associar uma chave a um valor e utilize Wrapper Classes quando precisar representar tipos primitivos como objetos, especialmente em coleções genéricas.

Esses conceitos são fundamentais para trabalhar com o Java Collections Framework, desenvolver aplicações orientadas a objetos e manipular dados de maneira organizada e eficiente.
