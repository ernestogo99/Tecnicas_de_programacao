## Streams

O objetivo desse projeto é praticar e entender Streams

Streams não modificam a lista original — elas produzem 
uma nova sequência de dados com base na cadeia de operações.

## Stream pipeline

consiste em uma fonte, seguida de zero ou mais operações
intermediárias e a operação terminal.


## Stream source

Streams podem ser criadas de coleções, list,sets, longs
, doubles, arrays, linhas de um arquivo.

# Stream operations are either intermediate or terminal

- intermediate operations: como o filter, map ou sort, retorna uma stream, então, podemos fazer multiplas operações intermediárias.
- terminal operations: como o forEach, collect ou reduce, retorna void ou um resultado que não é stream



# Intermediate operations

- zero ou mais operações são permitidas
- ordem importa para grandes bancos de dados: filtre primeiro, depois use o sort ou map.
- para bancos de dados muito grandes use ParallelStream para permitir multiplas threads.
- Filter:Filtra os elementos da Stream com base em uma condição booleana

```java
List<String> nomes = List.of("Ana", "Carlos", "João");
nomes.stream()
     .filter(nome -> nome.startsWith("A"))
     .forEach(System.out::println); 
```

- Anymatch:Verifica se algum elemento da stream atende a condição

```java
boolean temJoao = nomes.stream()
                       .anyMatch(nome -> nome.equals("João")); // true

```

- distinct:Remove elementos duplicados da stream(com base em equals())

```java
List<Integer> numeros = List.of(1, 2, 2, 3);
numeros.stream()
       .distinct()
       .forEach(System.out::println); // 1, 2, 3

```

- findFirst: Retorna o primeiro elemento da stream(em um optional)

```java
Optional<String> primeiro = nomes.stream()
                                 .findFirst();
```

- flatmap: Achata estruturas aninhadas em uma unica stream
- util quando você tem coleções dentro de coleções

```java
List<List<String>> listas = List.of(List.of("a", "b"), List.of("c"));
listas.stream()
.flatMap(List::stream)
.forEach(System.out::println); // a, b, c
```

- map:Transforma os elementos da stream.

```java
nomes.stream()
     .map(String::toUpperCase)
     .forEach(System.out::println); // ANA, CARLOS, JOÃO
```

- skip: pula os primeiros n elementos da stream

```java
numeros.stream()
       .skip(2)
       .forEach(System.out::println); // 2, 3 (pulou os 2 primeiros: 1, 2)

```

- sorted: ordena os elementos naturalmente ou com um comparator

```java
numeros.stream()
       .sorted()
       .forEach(System.out::println); // 1, 2, 2, 3
```



## Terminal operations

- somente uma operação terminal é permitida
- foreach aplica a mesma função para cada elemento
- collect salva os elementos em uma coleção

```java
List<String> nomes = list.stream()
                         .filter(nome -> nome.length() > 3)
                         .collect(Collectors.toList());
```


- reduce: reduz os elementos para um único valor, usando um acumulador
- pode ser usado para somar,multiplicar, concatenar, etc


```java
int soma = List.of(1, 2, 3).stream().reduce(0, Integer::sum); // resultado: 6
```


- count: retorna o número de elementos na stream

```java
long total = list.stream().count();
```

- max e min: retorna o maior ou menor elemento de acordo com  o comparator
- tipo de retorno: Optional<T>

```java
Optional<Integer> maior = List.of(2, 9, 4).stream().max(Integer::compareTo);
```


- summaryStatistics: usada em streams de tipos primitivos(int,double,long)
- retorna estatisticas como: count,sum,min,max,average.


```java
IntSummaryStatistics stats = List.of(1, 2, 3, 4).stream()
    .mapToInt(Integer::intValue)
    .summaryStatistics();

System.out.println(stats.getAverage());  // média
System.out.println(stats.getMax());      // máximo
```