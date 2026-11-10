# Java Stream API

## Índice

1. [Introdução](#introdução)
2. [O que é Stream API?](#o-que-é-stream-api)
3. [Características das Streams](#características-das-streams)
4. [Criando Streams](#criando-streams)
5. [Pipeline de execução](#pipeline-de-execução)
6. [Operações intermediárias](#operações-intermediárias)
7. [Operações terminais](#operações-terminais)
8. [Collectors](#collectors)
9. [Optional e Streams](#optional-e-streams)
10. [Streams de tipos primitivos](#streams-de-tipos-primitivos)
11. [Parallel Streams](#parallel-streams)
12. [Stream vs Collections](#stream-vs-collections)
13. [Boas práticas](#boas-práticas)
14. [Exemplo prático](#exemplo-prático)

---

## Introdução

A **Stream API** foi introduzida no Java 8 e permite processar coleções de dados de maneira declarativa, utilizando operações como filtragem, transformação, ordenação e agregação.

Ela facilita a manipulação de dados, reduzindo a necessidade de estruturas tradicionais como `for` e `while`.

```java
List<String> names = List.of("Ana", "Carlos", "Maria", "João");

List<String> filteredNames = names.stream()
    .filter(name -> name.startsWith("M"))
    .toList();

System.out.println(filteredNames); // [Maria]
```

## O que é Stream API?

Uma Stream representa uma sequência de elementos que pode ser processada por meio de operações encadeadas.

Diferentemente de uma coleção, uma Stream não armazena os elementos. Ela apenas fornece uma maneira de processá-los a partir de uma fonte de dados.

Exemplo:

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5);

numbers.stream()
    .filter(number -> number % 2 == 0)
    .forEach(System.out::println);
```

Resultado:

```text
2
4
```

## Características das Streams

| Característica | Descrição |
|---|---|
| Declarativa | Define o que deve ser feito, não necessariamente como. |
| Lazy Evaluation | Operações intermediárias só são executadas quando uma operação terminal é chamada. |
| Não armazenam dados | Processam elementos provenientes de uma fonte. |
| Não modificam a fonte | As operações não alteram diretamente a coleção original. |
| Encadeáveis | Permitem combinar diversas operações. |
| Uma única utilização | Uma Stream não pode ser reutilizada após uma operação terminal. |

### Uma Stream não pode ser reutilizada

```java
Stream<String> stream = Stream.of("Java", "Python", "JavaScript");

stream.forEach(System.out::println);

// Erro: a Stream já foi consumida.
stream.forEach(System.out::println);
```

Para realizar outro processamento, crie uma nova Stream a partir da fonte.

---

## Criando Streams

Existem diversas maneiras de criar uma Stream no Java.

### 1. A partir de Collections

```java
List<String> languages = List.of("Java", "Python", "JavaScript");

Stream<String> stream = languages.stream();
```

### 2. Utilizando Stream.of()

```java
Stream<String> stream = Stream.of("Java", "Python", "JavaScript");

Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5);
```

### 3. A partir de Arrays

```java
String[] languages = {"Java", "Python", "JavaScript"};

Stream<String> stream = Arrays.stream(languages);
```

### 4. Utilizando Stream.empty()

Cria uma Stream vazia.

```java
Stream<String> stream = Stream.empty();
```

### 5. Utilizando Stream.generate()

Gera elementos utilizando um `Supplier`.

```java
Stream<Double> randomNumbers = Stream.generate(Math::random);

randomNumbers
    .limit(5)
    .forEach(System.out::println);
```

### 6. Utilizando Stream.iterate()

Gera uma sequência a partir de um valor inicial e uma função de transformação.

```java
Stream<Integer> numbers = Stream.iterate(
    1,
    number -> number + 1
);

numbers
    .limit(5)
    .forEach(System.out::println);
```

Resultado:

```text
1
2
3
4
5
```

---

## Pipeline de execução

Uma Stream geralmente é composta por três etapas:

1. **Fonte:** origem dos dados.
2. **Operações intermediárias:** processamento dos elementos.
3. **Operação terminal:** executa o processamento e produz um resultado ou efeito.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

List<Integer> result = numbers.stream() // Fonte
    .filter(number -> number % 2 == 0)  // Intermediária
    .map(number -> number * 2)          // Intermediária
    .toList();                          // Terminal

System.out.println(result); // [4, 8, 12]
```

### Lazy Evaluation

As operações intermediárias são avaliadas de maneira preguiçosa. Isso significa que elas não são executadas até que uma operação terminal seja invocada.

```java
List<Integer> numbers = List.of(1, 2, 3, 4);

Stream<Integer> stream = numbers.stream()
    .filter(number -> {
        System.out.println("Filtrando: " + number);
        return number % 2 == 0;
    });

System.out.println("Stream criada");

stream.forEach(System.out::println);
```

A mensagem `Stream criada` será exibida antes das mensagens de filtragem.

---

## Operações intermediárias

São operações que transformam uma Stream em outra Stream.

Elas são **lazy**, ou seja, não executam o processamento imediatamente.

### filter()

Filtra elementos de acordo com uma condição.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

List<Integer> result = numbers.stream()
    .filter(number -> number > 3)
    .toList();

// [4, 5, 6]
```

### map()

Transforma cada elemento em outro valor.

```java
List<String> names = List.of("ana", "carlos", "maria");

List<String> result = names.stream()
    .map(String::toUpperCase)
    .toList();

// [ANA, CARLOS, MARIA]
```

### flatMap()

Transforma cada elemento em uma Stream e depois combina todas as Streams em uma única sequência.

```java
List<List<Integer>> numbers = List.of(
    List.of(1, 2),
    List.of(3, 4),
    List.of(5, 6)
);

List<Integer> result = numbers.stream()
    .flatMap(List::stream)
    .toList();

// [1, 2, 3, 4, 5, 6]
```

**Diferença entre map() e flatMap():**

- `map()`: transforma cada elemento individualmente.
- `flatMap()`: transforma e achata estruturas aninhadas.

### distinct()

Remove elementos duplicados.

```java
List<Integer> numbers = List.of(1, 2, 2, 3, 3, 4);

List<Integer> result = numbers.stream()
    .distinct()
    .toList();

// [1, 2, 3, 4]
```

### sorted()

Ordena os elementos.

```java
List<Integer> numbers = List.of(5, 2, 8, 1, 3);

List<Integer> ascending = numbers.stream()
    .sorted()
    .toList();

// [1, 2, 3, 5, 8]
```

Ordenação personalizada:

```java
List<String> names = List.of("Carlos", "Ana", "Maria");

List<String> result = names.stream()
    .sorted(Comparator.reverseOrder())
    .toList();

// [Maria, Carlos, Ana]
```

### limit()

Limita a quantidade de elementos processados.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5);

List<Integer> result = numbers.stream()
    .limit(3)
    .toList();

// [1, 2, 3]
```

### skip()

Ignora os primeiros elementos.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5);

List<Integer> result = numbers.stream()
    .skip(2)
    .toList();

// [3, 4, 5]
```

### peek()

Permite observar os elementos durante o processamento, geralmente para depuração.

```java
List<Integer> numbers = List.of(1, 2, 3);

List<Integer> result = numbers.stream()
    .peek(number -> System.out.println("Antes: " + number))
    .map(number -> number * 2)
    .peek(number -> System.out.println("Depois: " + number))
    .toList();
```

Evite utilizar `peek()` para implementar regras de negócio ou efeitos colaterais.

### takeWhile()

Mantém elementos enquanto a condição for verdadeira. Disponível desde Java 9.

```java
List<Integer> numbers = List.of(2, 4, 6, 7, 8, 10);

List<Integer> result = numbers.stream()
    .takeWhile(number -> number % 2 == 0)
    .toList();

// [2, 4, 6]
```

### dropWhile()

Ignora elementos enquanto a condição for verdadeira. Disponível desde Java 9.

```java
List<Integer> numbers = List.of(2, 4, 6, 7, 8, 10);

List<Integer> result = numbers.stream()
    .dropWhile(number -> number % 2 == 0)
    .toList();

// [7, 8, 10]
```

---

## Operações terminais

São operações que encerram o processamento de uma Stream e produzem um resultado ou efeito.

### forEach()

Executa uma ação para cada elemento.

```java
List<String> names = List.of("Ana", "Carlos", "Maria");

names.stream()
    .forEach(System.out::println);
```

### toList()

Converte os elementos da Stream em uma lista não modificável.

```java
List<Integer> numbers = List.of(1, 2, 3, 4);

List<Integer> result = numbers.stream()
    .filter(number -> number > 2)
    .toList();
```

> Disponível desde Java 16. A lista retornada por `toList()` não permite modificações estruturais.

Caso precise de uma lista modificável:

```java
List<Integer> result = numbers.stream()
    .filter(number -> number > 2)
    .collect(Collectors.toCollection(ArrayList::new));
```

### collect()

Acumula os elementos em uma estrutura ou resultado utilizando um Collector.

```java
List<String> names = List.of("Ana", "Carlos", "Maria");

List<String> result = names.stream()
    .filter(name -> name.length() > 3)
    .collect(Collectors.toList());
```

### count()

Retorna a quantidade de elementos.

```java
long count = Stream.of(1, 2, 3, 4, 5)
    .filter(number -> number > 2)
    .count();

// 3
```

### reduce()

Combina os elementos da Stream em um único resultado.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5);

int sum = numbers.stream()
    .reduce(0, Integer::sum);

// 15
```

Outro exemplo:

```java
int multiplication = numbers.stream()
    .reduce(1, (a, b) -> a * b);

// 120
```

Também é possível utilizar `reduce()` sem valor inicial, retornando um `Optional`.

```java
Optional<Integer> result = numbers.stream()
    .reduce(Integer::sum);
```

### min() e max()

Retornam o menor ou maior elemento de acordo com um Comparator.

```java
List<Integer> numbers = List.of(5, 2, 8, 1, 3);

Optional<Integer> min = numbers.stream()
    .min(Integer::compareTo);

Optional<Integer> max = numbers.stream()
    .max(Integer::compareTo);
```

### anyMatch()

Verifica se pelo menos um elemento atende à condição.

```java
boolean result = numbers.stream()
    .anyMatch(number -> number > 4);

// true
```

### allMatch()

Verifica se todos os elementos atendem à condição.

```java
boolean result = numbers.stream()
    .allMatch(number -> number > 0);

// true
```

### noneMatch()

Verifica se nenhum elemento atende à condição.

```java
boolean result = numbers.stream()
    .noneMatch(number -> number < 0);

// true
```

### findFirst()

Retorna o primeiro elemento da Stream em um `Optional`.

```java
Optional<Integer> result = numbers.stream()
    .filter(number -> number > 3)
    .findFirst();
```

### findAny()

Retorna algum elemento da Stream em um `Optional`. É especialmente útil em operações paralelas, quando não é necessário garantir qual elemento será retornado.

```java
Optional<Integer> result = numbers.stream()
    .filter(number -> number > 3)
    .findAny();
```

---

## Collectors

A classe `Collectors` fornece implementações prontas de coletores para operações comuns de agregação.

### toList()

Coleta os elementos em uma lista.

```java
List<String> result = names.stream()
    .collect(Collectors.toList());
```

### toSet()

Coleta os elementos em um conjunto, eliminando duplicados.

```java
Set<Integer> result = numbers.stream()
    .collect(Collectors.toSet());
```

### joining()

Concatena elementos de texto.

```java
List<String> names = List.of("Ana", "Carlos", "Maria");

String result = names.stream()
    .collect(Collectors.joining(", "));

// Ana, Carlos, Maria
```

Também aceita prefixo e sufixo:

```java
String result = names.stream()
    .collect(Collectors.joining(", ", "[", "]"));

// [Ana, Carlos, Maria]
```

### groupingBy()

Agrupa elementos de acordo com uma propriedade.

```java
List<String> names = List.of("Ana", "Amanda", "Carlos", "Arthur");

Map<Character, List<String>> result = names.stream()
    .collect(Collectors.groupingBy(name -> name.charAt(0)));

// A -> [Ana, Amanda, Arthur]
// C -> [Carlos]
```

### partitioningBy()

Divide os elementos em dois grupos, de acordo com uma condição.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

Map<Boolean, List<Integer>> result = numbers.stream()
    .collect(Collectors.partitioningBy(number -> number % 2 == 0));

// true  -> [2, 4, 6]
// false -> [1, 3, 5]
```

### counting()

Conta os elementos de um grupo ou de uma Stream.

```java
long result = names.stream()
    .collect(Collectors.counting());
```

### summingInt(), averagingInt() e summarizingInt()

Permitem realizar operações matemáticas sobre propriedades numéricas.

```java
List<Integer> numbers = List.of(10, 20, 30, 40);

int sum = numbers.stream()
    .collect(Collectors.summingInt(Integer::intValue));

double average = numbers.stream()
    .collect(Collectors.averagingInt(Integer::intValue));

IntSummaryStatistics statistics = numbers.stream()
    .collect(Collectors.summarizingInt(Integer::intValue));

System.out.println(statistics.getSum());     // 100
System.out.println(statistics.getAverage()); // 25.0
System.out.println(statistics.getMax());     // 40
System.out.println(statistics.getMin());     // 10
System.out.println(statistics.getCount());   // 4
```

### toMap()

Converte os elementos em um Map, definindo a chave e o valor.

```java
List<String> names = List.of("Ana", "Carlos", "Maria");

Map<String, Integer> result = names.stream()
    .collect(Collectors.toMap(
        name -> name,
        String::length
    ));
```

Quando existem chaves duplicadas, é necessário informar uma função de resolução de conflito:

```java
Map<Integer, String> result = names.stream()
    .collect(Collectors.toMap(
        String::length,
        name -> name,
        (first, second) -> first
    ));
```

---

## Optional e Streams

A Stream API utiliza `Optional` em operações que podem não encontrar um resultado.

Exemplos:

- `findFirst()`
- `findAny()`
- `min()`
- `max()`
- `reduce()` sem valor inicial

```java
List<String> names = List.of("Ana", "Carlos", "Maria");

Optional<String> result = names.stream()
    .filter(name -> name.startsWith("J"))
    .findFirst();
```

Tratando o possível resultado:

```java
String name = result.orElse("Não encontrado");
```

Ou utilizando `ifPresent()`:

```java
result.ifPresent(System.out::println);
```

Também podemos utilizar `map()` e `orElse()`:

```java
String upperCaseName = result
    .map(String::toUpperCase)
    .orElse("NÃO ENCONTRADO");
```

---

## Streams de tipos primitivos

A Stream API oferece três implementações especializadas para trabalhar com tipos primitivos:

| Stream | Tipo |
|---|---|
| `IntStream` | `int` |
| `LongStream` | `long` |
| `DoubleStream` | `double` |

Essas implementações evitam o boxing e unboxing constante dos tipos primitivos.

### IntStream

```java
int sum = IntStream.range(1, 6)
    .sum();

// 15
```

`range()` não inclui o limite final.

```java
IntStream.range(1, 5); // 1, 2, 3, 4
```

Já `rangeClosed()` inclui o limite final.

```java
IntStream.rangeClosed(1, 5); // 1, 2, 3, 4, 5
```

### Operações matemáticas

```java
IntStream numbers = IntStream.of(10, 20, 30, 40);

int sum = numbers.sum();
```

Outras operações disponíveis:

```java
IntStream numbers = IntStream.of(10, 20, 30, 40);

OptionalInt min = numbers.min();
```

Também existem:

- `sum()`
- `average()`
- `min()`
- `max()`
- `count()`
- `summaryStatistics()`

### Conversão entre Streams

```java
List<String> numbers = List.of("10", "20", "30");

int sum = numbers.stream()
    .mapToInt(Integer::parseInt)
    .sum();
```

Convertendo de volta:

```java
List<Integer> result = IntStream.rangeClosed(1, 5)
    .boxed()
    .toList();
```

O método `boxed()` converte os valores primitivos em seus respectivos Wrappers.

---

## Parallel Streams

Uma Parallel Stream permite processar elementos utilizando múltiplas threads, aproveitando o `ForkJoinPool` comum do Java.

### Stream sequencial

```java
List<Integer> numbers = IntStream.rangeClosed(1, 1000)
    .boxed()
    .toList();

long sum = numbers.stream()
    .mapToLong(Integer::longValue)
    .sum();
```

### Stream paralela

```java
long sum = numbers.parallelStream()
    .mapToLong(Integer::longValue)
    .sum();
```

Também é possível utilizar `parallel()`:

```java
numbers.stream()
    .parallel()
    .forEach(System.out::println);
```

### Diferenças

| Sequencial | Paralela |
|---|---|
| Processamento em sequência | Processamento concorrente |
| Menor complexidade | Maior complexidade |
| Geralmente adequada para operações simples | Pode beneficiar operações custosas e grandes volumes |
| Mantém a ordem em operações ordenadas | Pode não preservar a ordem em operações como `forEach()` |

### Quando utilizar?

Parallel Streams podem ser interessantes quando:

- Existe um volume significativo de dados.
- As operações são independentes entre si.
- O processamento é computacionalmente custoso.
- O custo de paralelização é compensado pelo ganho de desempenho.

### Cuidados

- Não utilize variáveis compartilhadas mutáveis sem sincronização.
- Evite efeitos colaterais nas operações.
- Não presuma que uma Stream paralela será sempre mais rápida.
- Considere o impacto no `ForkJoinPool` comum.
- Faça benchmarks antes de adotar paralelismo.

---

## Stream vs Collections

| Collections | Streams |
|---|---|
| Armazenam elementos | Processam elementos |
| Permitem adicionar e remover dados | Não permitem modificar diretamente a fonte |
| Podem ser percorridas várias vezes | São consumidas uma única vez |
| Geralmente utilizam iteração explícita ou externa | Utilizam operações declarativas |
| Possuem estruturas como List, Set e Map | Possuem operações como filter, map e reduce |

Exemplo com Collection:

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5);

List<Integer> result = new ArrayList<>();

for (Integer number : numbers) {
    if (number % 2 == 0) {
        result.add(number * 2);
    }
}
```

Exemplo com Stream:

```java
List<Integer> result = numbers.stream()
    .filter(number -> number % 2 == 0)
    .map(number -> number * 2)
    .toList();
```

As duas abordagens resolvem o mesmo problema, mas a Stream permite expressar o processamento de maneira mais declarativa.

---

## Boas práticas

1. **Prefira operações puras:** evite modificar variáveis externas dentro das operações.
2. **Evite reutilizar Streams:** crie uma nova Stream sempre que precisar de outro processamento.
3. **Utilize map() para transformações:** não utilize `forEach()` para construir coleções manualmente.
4. **Prefira métodos especializados:** utilize `IntStream`, `LongStream` e `DoubleStream` quando apropriado.
5. **Tenha cuidado com peek():** utilize principalmente para depuração.
6. **Evite processamento desnecessário:** utilize `limit()` e `filter()` quando fizer sentido.
7. **Não abuse do encadeamento:** código legível é mais importante que encadear muitas operações.
8. **Utilize Parallel Streams com critério:** paralelismo não garante melhor desempenho.
9. **Não modifique a fonte durante o processamento:** isso pode gerar comportamentos inesperados.
10. **Escolha a operação adequada:** `reduce()` para redução, `collect()` para acumulação e `map()` para transformação.

---

## Exemplo prático

Considere uma aplicação que possui uma lista de funcionários e precisa encontrar os funcionários ativos, com salário superior a R$ 3.000,00, ordená-los pelo nome e calcular a soma dos salários.

### Model

```java
public record Employee(
    String name,
    BigDecimal salary,
    boolean active
) {}
```

### Processamento

```java
List<Employee> employees = List.of(
    new Employee("Carlos", new BigDecimal("5000.00"), true),
    new Employee("Ana", new BigDecimal("4000.00"), true),
    new Employee("Maria", new BigDecimal("2500.00"), true),
    new Employee("João", new BigDecimal("6000.00"), false)
);
```

Filtrando e ordenando:

```java
List<Employee> result = employees.stream()
    .filter(Employee::active)
    .filter(employee -> employee.salary()
        .compareTo(new BigDecimal("3000.00")) > 0)
    .sorted(Comparator.comparing(Employee::name))
    .toList();
```

Calculando a soma dos salários:

```java
BigDecimal totalSalary = result.stream()
    .map(Employee::salary)
    .reduce(BigDecimal.ZERO, BigDecimal::add);
```

Resultado:

```text
Funcionários:
Ana
Carlos

Total dos salários:
9000.00
```

---

## Resumo das principais operações

| Operação | Tipo | Finalidade |
|---|---|---|
| `filter()` | Intermediária | Filtrar elementos |
| `map()` | Intermediária | Transformar elementos |
| `flatMap()` | Intermediária | Transformar e achatar Streams |
| `distinct()` | Intermediária | Remover duplicados |
| `sorted()` | Intermediária | Ordenar elementos |
| `limit()` | Intermediária | Limitar elementos |
| `skip()` | Intermediária | Ignorar elementos |
| `peek()` | Intermediária | Inspecionar elementos |
| `takeWhile()` | Intermediária | Manter enquanto a condição for verdadeira |
| `dropWhile()` | Intermediária | Ignorar enquanto a condição for verdadeira |
| `forEach()` | Terminal | Executar uma ação |
| `toList()` | Terminal | Converter em lista |
| `collect()` | Terminal | Acumular elementos |
| `count()` | Terminal | Contar elementos |
| `reduce()` | Terminal | Reduzir a um único resultado |
| `min()` / `max()` | Terminal | Encontrar extremos |
| `anyMatch()` | Terminal | Verificar se algum elemento atende |
| `allMatch()` | Terminal | Verificar se todos atendem |
| `noneMatch()` | Terminal | Verificar se nenhum atende |
| `findFirst()` | Terminal | Encontrar o primeiro elemento |
| `findAny()` | Terminal | Encontrar algum elemento |

## Exercícios para praticar

1. Dada uma lista de números inteiros, retorne somente os números pares.
2. Dada uma lista de nomes, converta todos para letras maiúsculas e ordene alfabeticamente.
3. Dada uma lista de números, encontre o maior e o menor valor.
4. Dada uma lista de funcionários, filtre os ativos e agrupe-os por departamento.
5. Dada uma lista de produtos, calcule o valor total dos produtos em estoque utilizando `BigDecimal`.
6. Dada uma lista de palavras, remova duplicatas e concatene os resultados utilizando `joining()`.
7. Dada uma lista de listas de números, transforme-a em uma única lista utilizando `flatMap()`.
8. Dada uma lista de usuários, verifique se todos possuem e-mail válido utilizando `allMatch()`.
9. Dada uma lista de pedidos, agrupe-os por status utilizando `groupingBy()`.
10. Compare o desempenho de uma Stream sequencial e uma Parallel Stream em um processamento de grande volume.

---

## Referências

- [Documentação oficial — Stream API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/package-summary.html)
- [Documentação oficial — Stream](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/Stream.html)
- [Documentação oficial — Collectors](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/Collectors.html)
- [Documentação oficial — IntStream](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/IntStream.html)
