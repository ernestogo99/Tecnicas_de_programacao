# Set em Java

## Sumário

- [1. Introdução](#1-introdução)
- [2. O que é Set?](#2-o-que-é-set)
- [3. Principais características](#3-principais-características)
- [4. Implementações de Set](#4-implementações-de-set)
    - [4.1. HashSet](#41-hashset)
    - [4.2. LinkedHashSet](#42-linkedhashset)
    - [4.3. TreeSet](#43-treeset)
- [5. Principais métodos](#5-principais-métodos)
- [6. Percorrendo um Set](#6-percorrendo-um-set)
- [7. Operações entre conjuntos](#7-operações-entre-conjuntos)
- [8. Como funciona a remoção de duplicados?](#8-como-funciona-a-remoção-de-duplicados)
- [9. Diferenças entre Set, List e Map](#9-diferenças-entre-set-list-e-map)
- [10. Quando utilizar cada implementação?](#10-quando-utilizar-cada-implementação)
- [11. Boas práticas](#11-boas-práticas)
- [12. Resumo](#12-resumo)

---

## 1. Introdução

O Java Collections Framework disponibiliza diferentes estruturas de dados para armazenar e manipular informações.

Entre elas, temos:

- `List`: armazena elementos em uma sequência e permite duplicatas.
- `Set`: armazena elementos únicos, sem duplicatas.
- `Queue`: representa uma coleção voltada ao processamento de elementos.
- `Map`: armazena associações entre chaves e valores.

Neste material, estudaremos a interface `Set`, utilizada quando precisamos garantir que um elemento não apareça mais de uma vez na coleção.

## 2. O que é Set?

`Set` é uma interface do pacote `java.util` que representa uma coleção de elementos únicos.

Isso significa que não podemos adicionar dois elementos considerados iguais à mesma coleção.

Exemplo:

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> nomes = new HashSet<>();

        nomes.add("Ana");
        nomes.add("Carlos");
        nomes.add("Maria");
        nomes.add("Ana");

        System.out.println(nomes);
        // Os elementos aparecem uma única vez.
    }
}
```

Embora `"Ana"` tenha sido adicionada duas vezes, o `Set` mantém apenas uma ocorrência.

**Importante:** `HashSet` não garante a ordem de iteração. Portanto, não devemos depender da ordem em que os elementos serão impressos.

## 3. Principais características

As características de um `Set` incluem:

- Não permite elementos duplicados segundo o critério de igualdade da implementação.
- Não oferece acesso posicional por índice, como `List`.
- Pode permitir `null`, dependendo da implementação.
- Possui métodos para adicionar, remover e consultar elementos.
- Oferece diferentes implementações com características específicas de desempenho e ordenação.

Exemplo:

```java
Set<Integer> numeros = new HashSet<>();

numeros.add(10);
numeros.add(20);
numeros.add(10);
numeros.add(30);

System.out.println(numeros.size()); // 3
```

O método `size()` retorna a quantidade de elementos únicos armazenados.

---

## 4. Implementações de Set

As três implementações mais conhecidas são:

| Implementação | Organização | Característica principal |
|---|---|---|
| `HashSet` | Tabela hash | Não garante ordem de iteração |
| `LinkedHashSet` | Tabela hash e estrutura encadeada | Mantém a ordem de inserção |
| `TreeSet` | Árvore balanceada | Mantém os elementos ordenados |

### 4.1. HashSet

`HashSet` é uma implementação de `Set` baseada em uma tabela hash.

Ela utiliza o código hash dos objetos para organizar e localizar elementos.

Principais características:

- Não garante ordem de iteração.
- Não permite elementos duplicados.
- Permite um elemento `null`.
- Oferece operações de adição, remoção e consulta com desempenho médio geralmente constante, considerando uma boa distribuição dos códigos hash.

Exemplo:

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> linguagens = new HashSet<>();

        linguagens.add("Java");
        linguagens.add("Python");
        linguagens.add("JavaScript");
        linguagens.add("Java");

        System.out.println(linguagens);
        System.out.println(linguagens.contains("Python"));
        // true
    }
}
```

A linguagem Java aparece apenas uma vez.

**Quando utilizar?**

Utilize `HashSet` quando precisar armazenar elementos únicos e não precisar preservar uma ordem específica.

### 4.2. LinkedHashSet

`LinkedHashSet` é semelhante ao `HashSet`, mas mantém a ordem de inserção dos elementos.

Exemplo:

```java
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> nomes = new LinkedHashSet<>();

        nomes.add("Carlos");
        nomes.add("Ana");
        nomes.add("Maria");
        nomes.add("Ana");

        System.out.println(nomes);
        // [Carlos, Ana, Maria]
    }
}
```

O segundo `"Ana"` não é adicionado, e a ordem dos elementos únicos corresponde à ordem em que foram inseridos pela primeira vez.

**Quando utilizar?**

Utilize `LinkedHashSet` quando precisar evitar duplicatas e preservar a ordem de inserção.

### 4.3. TreeSet

`TreeSet` mantém os elementos ordenados conforme sua ordem natural ou conforme um `Comparator` fornecido.

Exemplo com números:

```java
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<Integer> numeros = new TreeSet<>();

        numeros.add(50);
        numeros.add(10);
        numeros.add(30);
        numeros.add(20);
        numeros.add(10);

        System.out.println(numeros);
        // [10, 20, 30, 50]
    }
}
```

Mesmo que os elementos tenham sido adicionados fora de ordem, o `TreeSet` os organiza em ordem crescente.

Exemplo com strings:

```java
Set<String> nomes = new TreeSet<>();

nomes.add("Carlos");
nomes.add("Ana");
nomes.add("Bruno");

System.out.println(nomes);
// [Ana, Bruno, Carlos]
```

Principais características:

- Mantém os elementos ordenados.
- Não permite elementos duplicados segundo seu critério de comparação.
- As operações básicas têm custo tipicamente \(O(\log n)\).
- Não aceita `null` com o comparador natural nas implementações usuais do `TreeSet`.

**Quando utilizar?**

Utilize `TreeSet` quando precisar manter elementos únicos em uma ordem definida, como números crescentes ou nomes em ordem alfabética.

---

## 5. Principais métodos

A interface `Set` herda os métodos da interface `Collection`.

Considere:

```java
Set<String> nomes = new HashSet<>();
```

| Método | Finalidade | Exemplo |
|---|---|---|
| `add(elemento)` | Adiciona um elemento se ainda não existir | `nomes.add("Ana")` |
| `remove(elemento)` | Remove um elemento | `nomes.remove("Ana")` |
| `contains(elemento)` | Verifica se o elemento existe | `nomes.contains("Carlos")` |
| `size()` | Retorna a quantidade de elementos | `nomes.size()` |
| `isEmpty()` | Verifica se a coleção está vazia | `nomes.isEmpty()` |
| `clear()` | Remove todos os elementos | `nomes.clear()` |
| `addAll(colecao)` | Adiciona elementos de outra coleção | `nomes.addAll(outraLista)` |
| `removeAll(colecao)` | Remove elementos presentes em outra coleção | `nomes.removeAll(outraLista)` |
| `retainAll(colecao)` | Mantém apenas os elementos comuns | `nomes.retainAll(outraLista)` |
| `containsAll(colecao)` | Verifica se contém todos os elementos informados | `nomes.containsAll(outraLista)` |

O método `add()` retorna um `boolean`:

- `true`: o elemento foi adicionado.
- `false`: o elemento já existia e não foi adicionado.

Exemplo:

```java
Set<String> nomes = new HashSet<>();

System.out.println(nomes.add("Ana")); // true
System.out.println(nomes.add("Ana")); // false
```

Esse retorno pode ser útil quando precisamos saber se a coleção foi efetivamente modificada.

---

## 6. Percorrendo um Set

Como `Set` não oferece acesso por índice, não podemos utilizar diretamente chamadas como `nomes.get(0)`.

Podemos percorrer seus elementos de diferentes maneiras.

### Com for-each

```java
Set<String> nomes = new HashSet<>();

nomes.add("Ana");
nomes.add("Carlos");
nomes.add("Maria");

for (String nome : nomes) {
    System.out.println(nome);
}
```

A ordem de impressão não é garantida por `HashSet`.

### Com forEach e expressão lambda

```java
nomes.forEach(nome -> System.out.println(nome));
```

Também podemos utilizar uma referência a método:

```java
nomes.forEach(System.out::println);
```

### Com Iterator

```java
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

Set<String> nomes = new HashSet<>();

nomes.add("Ana");
nomes.add("Carlos");
nomes.add("Maria");

Iterator<String> iterator = nomes.iterator();

while (iterator.hasNext()) {
    String nome = iterator.next();
    System.out.println(nome);
}
```

O `Iterator` permite percorrer a coleção e, quando necessário, remover elementos de forma segura durante a iteração usando `iterator.remove()`.

---

## 7. Operações entre conjuntos

Podemos utilizar `Set` para realizar operações semelhantes às operações matemáticas entre conjuntos.

Considere:

```java
Set<Integer> conjuntoA = new HashSet<>(
    java.util.Set.of(1, 2, 3, 4)
);

Set<Integer> conjuntoB = new HashSet<>(
    java.util.Set.of(3, 4, 5, 6)
);
```

### 7.1. União

A união reúne todos os elementos dos dois conjuntos, sem duplicatas.

```java
Set<Integer> uniao = new HashSet<>(conjuntoA);

uniao.addAll(conjuntoB);

System.out.println(uniao);
// [1, 2, 3, 4, 5, 6], em alguma ordem
```

### 7.2. Interseção

A interseção contém apenas os elementos presentes nos dois conjuntos.

```java
Set<Integer> intersecao = new HashSet<>(conjuntoA);

intersecao.retainAll(conjuntoB);

System.out.println(intersecao);
// [3, 4], em alguma ordem
```

### 7.3. Diferença

A diferença entre A e B contém os elementos que estão em A, mas não em B.

```java
Set<Integer> diferenca = new HashSet<>(conjuntoA);

diferenca.removeAll(conjuntoB);

System.out.println(diferenca);
// [1, 2], em alguma ordem
```

**Atenção:** esses métodos modificam a coleção na qual são chamados. Por isso, os exemplos criam uma cópia antes de executar a operação.

---

## 8. Como funciona a remoção de duplicados?

O comportamento de `Set` depende da forma como os elementos são considerados iguais.

### 8.1. Com tipos comuns

Para tipos como `String` e `Integer`, as implementações dessas classes já possuem regras de igualdade adequadas.

```java
Set<String> nomes = new HashSet<>();

nomes.add("Ana");
nomes.add("Ana");
nomes.add("Carlos");

System.out.println(nomes.size()); // 2
```

As duas strings `"Ana"` são consideradas iguais pelo método `equals()`.

### 8.2. Com objetos personalizados

Considere uma classe `Pessoa`:

```java
public class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
```

Se adicionarmos dois objetos distintos dessa classe a um `HashSet`, ambos poderão ser armazenados, mesmo que tenham o mesmo nome, pois a classe ainda não define igualdade por conteúdo.

```java
Set<Pessoa> pessoas = new HashSet<>();

pessoas.add(new Pessoa("Ana"));
pessoas.add(new Pessoa("Ana"));

System.out.println(pessoas.size()); // 2
```

Para que o `HashSet` reconheça pessoas com o mesmo nome como iguais, precisamos implementar `equals()` e `hashCode()` de maneira consistente.

Exemplo:

```java
import java.util.Objects;

public class Pessoa {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Pessoa outra)) {
            return false;
        }

        return Objects.equals(nome, outra.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
```

Agora, um `HashSet<Pessoa>` tratará duas pessoas com o mesmo nome como elementos iguais.

**Regra fundamental:** quando sobrescrevemos `equals()`, também devemos sobrescrever `hashCode()` de forma consistente. Objetos considerados iguais por `equals()` precisam retornar o mesmo código hash.

No `TreeSet`, a comparação também determina quais elementos são considerados equivalentes para a coleção. Por isso, é importante compreender `Comparable` e `Comparator` ao trabalhar com objetos personalizados.

---

## 9. Diferenças entre Set, List e Map

| Característica | List | Set | Map |
|---|---|---|---|
| Armazena elementos | Sim | Sim | Armazena pares de chave e valor |
| Permite duplicatas | Sim | Não, conforme a igualdade | Chaves únicas; valores podem repetir |
| Acesso por índice | Sim | Não | Não por índice |
| Exemplos de implementação | `ArrayList`, `LinkedList` | `HashSet`, `LinkedHashSet`, `TreeSet` | `HashMap`, `LinkedHashMap`, `TreeMap` |
| Uso comum | Sequências de elementos | Elementos únicos | Associação entre chaves e valores |

Exemplos:

```java
List<String> lista = new ArrayList<>();
lista.add("Java");
lista.add("Java");
// Permite duplicatas.

Set<String> conjunto = new HashSet<>();
conjunto.add("Java");
conjunto.add("Java");
// Mantém um único elemento.

Map<Integer, String> alunos = new HashMap<>();
alunos.put(1, "Ana");
alunos.put(2, "Carlos");
// Cada chave identifica um valor.
```

Observe que `Map` faz parte do Java Collections Framework, mas não implementa a interface `Collection`.

---

## 10. Quando utilizar cada implementação?

| Situação | Implementação recomendada |
|---|---|
| Precisa de elementos únicos sem uma ordem específica | `HashSet` |
| Precisa preservar a ordem de inserção | `LinkedHashSet` |
| Precisa manter os elementos ordenados | `TreeSet` |
| Precisa acessar elementos por posição e aceitar duplicatas | `ArrayList` |

A escolha depende dos requisitos da aplicação. Se a ordem não importa, `HashSet` costuma ser uma boa opção inicial. Se a ordenação é requisito funcional, escolha a implementação que ofereça o comportamento necessário.

---

## 11. Boas práticas

1. **Declare pela interface:** prefira `Set<String> nomes = new HashSet<>();` em vez de depender da implementação concreta em toda a aplicação.

2. **Escolha a implementação conforme a necessidade:** não utilize `TreeSet` apenas por ser uma estrutura ordenada se não houver necessidade dessa ordenação.

3. **Implemente `equals()` e `hashCode()` corretamente:** isso é essencial para que conjuntos baseados em hash identifiquem objetos equivalentes.

4. **Evite alterar atributos usados na igualdade enquanto os objetos estiverem em um `HashSet`:** alterações que afetam `equals()` ou `hashCode()` podem prejudicar pesquisas e remoções.

5. **Não dependa da ordem de um `HashSet`:** se a ordem for importante, utilize `LinkedHashSet` ou `TreeSet`.

6. **Utilize generics:** declare o tipo dos elementos, como `Set<Integer>` ou `Set<Pessoa>`.

7. **Cuidado com `null`:** o suporte depende da implementação e do comparador utilizado.

8. **Evite modificar a coleção diretamente durante um `for-each`:** utilize `Iterator.remove()` ou operações apropriadas, como `removeIf`.

9. **Crie cópias quando precisar preservar o conjunto original:** operações como `retainAll()` e `removeAll()` modificam a coleção receptora.

---



## 12. Resumo

- `Set` é uma interface que representa uma coleção de elementos únicos.
- `HashSet` prioriza operações eficientes sem garantir uma ordem específica.
- `LinkedHashSet` mantém a ordem de inserção.
- `TreeSet` mantém os elementos ordenados.
- `add()` retorna `false` quando um elemento equivalente já existe.
- `contains()` verifica se um elemento está presente.
- `addAll()`, `retainAll()` e `removeAll()` permitem operações entre conjuntos.
- `equals()` e `hashCode()` são fundamentais para o comportamento correto de conjuntos baseados em hash.
- `Set` não permite acesso posicional por índice como `List`.

### Conceito fundamental

Utilize `Set` quando a regra de negócio exigir que os elementos sejam únicos. Escolha entre `HashSet`, `LinkedHashSet` e `TreeSet` conforme a necessidade de desempenho, preservação da ordem de inserção ou ordenação dos elementos.

