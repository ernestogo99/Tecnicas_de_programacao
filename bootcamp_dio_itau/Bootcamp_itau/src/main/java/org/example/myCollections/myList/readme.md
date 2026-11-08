# Arrays e Listas em Java

## Sumário

- [1. Introdução](#1-introdução)
- [2. Arrays em Java](#2-arrays-em-java)
    - [2.1. O que são arrays?](#21-o-que-são-arrays)
    - [2.2. Declarando e inicializando arrays](#22-declarando-e-inicializando-arrays)
    - [2.3. Acessando e modificando elementos](#23-acessando-e-modificando-elementos)
    - [2.4. Percorrendo arrays](#24-percorrendo-arrays)
    - [2.5. Classe Arrays](#25-classe-arrays)
    - [2.6. Arrays multidimensionais](#26-arrays-multidimensionais)
- [3. Listas em Java](#3-listas-em-java)
    - [3.1. O que são listas?](#31-o-que-são-listas)
    - [3.2. Interface List](#32-interface-list)
    - [3.3. ArrayList](#33-arraylist)
    - [3.4. LinkedList](#34-linkedlist)
    - [3.5. Principais métodos](#35-principais-métodos)
    - [3.6. Percorrendo listas](#36-percorrendo-listas)
    - [3.7. Imutabilidade e listas de tamanho fixo](#37-imutabilidade-e-listas-de-tamanho-fixo)
- [4. Diferenças entre arrays e listas](#4-diferenças-entre-arrays-e-listas)
- [5. Quando utilizar cada estrutura?](#5-quando-utilizar-cada-estrutura)
- [6. Boas práticas](#6-boas-práticas)
- [7. Exercícios para praticar](#7-exercícios-para-praticar)
- [8. Resumo](#8-resumo)

---

## 1. Introdução

Em Java, frequentemente precisamos armazenar e manipular conjuntos de dados, como nomes de usuários, notas de alunos, produtos ou números.

Existem diferentes estruturas para organizar esses dados. Duas das mais importantes são:

- **Arrays:** estruturas que armazenam uma quantidade definida de elementos.
- **Listas:** estruturas que permitem trabalhar com coleções de elementos, oferecendo operações para adicionar, remover, consultar e modificar valores.

Embora tenham finalidades semelhantes, arrays e listas possuem características diferentes.

## 2. Arrays em Java

### 2.1. O que são arrays?

Um array é uma estrutura que armazena vários elementos do mesmo tipo em uma única variável.

Suas principais características são:

- Possui tamanho fixo após sua criação.
- Os elementos são acessados por índices.
- O primeiro índice é `0`.
- Pode armazenar tipos primitivos, como `int` e `double`, ou referências a objetos.
- Não permite adicionar ou remover posições depois de criado.

Exemplo:

```java
public class Main {
    public static void main(String[] args) {
        int[] numeros = {10, 20, 30, 40, 50};

        System.out.println(numeros[0]); // 10
        System.out.println(numeros[2]); // 30
        System.out.println(numeros[4]); // 50
    }
}
```

O array possui cinco elementos, com índices de `0` a `4`.

### 2.2. Declarando e inicializando arrays

Existem diferentes formas de declarar e inicializar arrays.

**1. Declarando e definindo o tamanho**

```java
int[] numeros = new int[5];
```

O array possui cinco posições. Como o tipo é `int`, todas começam com o valor padrão `0`.

**2. Declarando e informando os valores**

```java
String[] nomes = {"Ana", "Carlos", "Maria"};
```

O array possui três posições, preenchidas com os nomes informados.

**3. Declarando separadamente**

```java
double[] notas;

notas = new double[3];

notas[0] = 8.5;
notas[1] = 7.0;
notas[2] = 9.5;
```

**Valores padrão dos arrays**

| Tipo | Valor padrão |
|---|---|
| `int` | `0` |
| `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` |
| Tipos de referência, como `String` | `null` |

Esses valores são atribuídos automaticamente quando um array é criado. Variáveis locais, por outro lado, precisam ser inicializadas antes de serem utilizadas.

### 2.3. Acessando e modificando elementos

Utilizamos o índice para acessar ou modificar uma posição.

```java
int[] numeros = {10, 20, 30};

numeros[1] = 99;

System.out.println(numeros[1]); // 99
```

Para descobrir o tamanho de um array, utilizamos a propriedade `length`:

```java
System.out.println(numeros.length); // 3
```

Atenção: tentar acessar um índice inexistente provoca uma exceção `ArrayIndexOutOfBoundsException`.

```java
int[] numeros = {10, 20, 30};

System.out.println(numeros[3]); // Erro em tempo de execução
```

O último índice válido é sempre `length - 1`.

### 2.4. Percorrendo arrays

Podemos utilizar diferentes estruturas de repetição.

**Com `for` tradicional**

```java
int[] numeros = {10, 20, 30};

for (int i = 0; i < numeros.length; i++) {
    System.out.println(numeros[i]);
}
```

É útil quando precisamos do índice ou queremos controlar a posição acessada.

**Com `for-each`**

```java
int[] numeros = {10, 20, 30};

for (int numero : numeros) {
    System.out.println(numero);
}
```

É uma alternativa simples quando precisamos apenas ler os elementos.

### 2.5. Classe Arrays

A classe `java.util.Arrays` fornece métodos utilitários para manipular arrays.

```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] numeros = {40, 10, 30, 20};

        Arrays.sort(numeros);

        System.out.println(Arrays.toString(numeros));
        // [10, 20, 30, 40]

        System.out.println(Arrays.binarySearch(numeros, 30));
        // 2
    }
}
```

Principais métodos:

| Método | Finalidade |
|---|---|
| `Arrays.toString(array)` | Representa os elementos como texto. |
| `Arrays.sort(array)` | Ordena os elementos. |
| `Arrays.equals(a, b)` | Compara os elementos de dois arrays. |
| `Arrays.fill(array, valor)` | Preenche todas as posições com um valor. |
| `Arrays.copyOf(array, tamanho)` | Cria uma cópia com o tamanho especificado. |
| `Arrays.binarySearch(array, valor)` | Pesquisa um elemento em um array ordenado. |

**Importante:** para usar `binarySearch` corretamente, o array deve estar ordenado de acordo com a ordem utilizada na pesquisa.

### 2.6. Arrays multidimensionais

Arrays também podem representar tabelas e matrizes.

```java
int[][] matriz = {
    {1, 2, 3},
    {4, 5, 6}
};

System.out.println(matriz[0][1]); // 2
System.out.println(matriz[1][2]); // 6
```

Nesse exemplo, `matriz[linha][coluna]` acessa um elemento específico.

O Java também permite arrays multidimensionais irregulares, nos quais cada linha pode ter um tamanho diferente.

---

## 3. Listas em Java

### 3.1. O que são listas?

Uma lista é uma coleção ordenada de elementos. Em Java, as listas fazem parte do **Java Collections Framework**, que disponibiliza interfaces e implementações para trabalhar com conjuntos de dados.

Diferentemente de um array comum, uma lista como `ArrayList` pode aumentar ou diminuir de tamanho conforme os elementos são adicionados ou removidos.

As listas oferecem:

- Adição de elementos.
- Remoção de elementos.
- Consulta por índice.
- Alteração de elementos.
- Pesquisa de valores.
- Iteração sobre os elementos.

Para trabalhar com listas, normalmente utilizamos a interface `List` e uma implementação concreta, como `ArrayList`.

### 3.2. Interface List

A interface `List` define operações comuns às listas. Ela é encontrada no pacote `java.util`.

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();

        nomes.add("Ana");
        nomes.add("Carlos");
        nomes.add("Maria");

        System.out.println(nomes);
        // [Ana, Carlos, Maria]
    }
}
```

Observe esta declaração:

```java
List<String> nomes = new ArrayList<>();
```

- `List`: interface que define o comportamento esperado.
- `String`: tipo de elemento que a lista pode armazenar.
- `ArrayList`: implementação concreta da interface.
- `new ArrayList<>()`: cria a lista.

Essa abordagem favorece o polimorfismo: podemos trocar a implementação por outra compatível sem alterar necessariamente o código que utiliza a interface.

### 3.3. ArrayList

`ArrayList` é uma implementação da interface `List` baseada em um array interno que pode crescer conforme necessário.

É uma das opções mais comuns para armazenar listas em Java.

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> linguagens = new ArrayList<>();

        linguagens.add("Java");
        linguagens.add("Python");
        linguagens.add("JavaScript");

        linguagens.remove("Python");

        System.out.println(linguagens);
        // [Java, JavaScript]

        System.out.println(linguagens.get(0));
        // Java
    }
}
```

Características:

- Tamanho dinâmico.
- Acesso por índice geralmente muito eficiente.
- Permite elementos duplicados.
- Permite valores `null`.
- Inserções ou remoções no meio podem exigir o deslocamento de outros elementos.

Em geral, `ArrayList` é uma boa escolha quando precisamos consultar elementos frequentemente por índice e realizar adições ao final da lista.

### 3.4. LinkedList

`LinkedList` é outra implementação da interface `List`. Ela utiliza uma estrutura de nós ligados entre si, na qual cada nó mantém referências para os elementos vizinhos.

```java
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> tarefas = new LinkedList<>();

        tarefas.add("Estudar Java");
        tarefas.add("Praticar Collections");
        tarefas.add("Revisar exercícios");

        System.out.println(tarefas);
    }
}
```

Características:

- Tamanho dinâmico.
- Acesso por índice geralmente mais lento do que em `ArrayList`.
- Inserções e remoções nas extremidades são eficientes.
- Permite elementos duplicados e `null`.

Embora `LinkedList` possa ser útil em alguns cenários, ela não é automaticamente mais rápida para inserções e remoções. Para modificar uma posição intermediária, encontrar essa posição pode exigir percorrer a lista.

**Regra prática:** prefira `ArrayList` como escolha inicial para listas de uso geral. Considere `LinkedList` quando as operações específicas da estrutura realmente beneficiarem sua aplicação.

### 3.5. Principais métodos

A interface `List` disponibiliza vários métodos para manipular elementos.

Considere:

```java
List<String> nomes = new ArrayList<>();
```

| Método | Finalidade | Exemplo |
|---|---|---|
| `add(elemento)` | Adiciona ao final | `nomes.add("Ana")` |
| `add(indice, elemento)` | Insere em uma posição | `nomes.add(0, "Carlos")` |
| `get(indice)` | Retorna um elemento | `nomes.get(0)` |
| `set(indice, elemento)` | Substitui um elemento | `nomes.set(0, "Maria")` |
| `remove(indice)` | Remove pela posição | `nomes.remove(0)` |
| `remove(elemento)` | Remove um elemento correspondente | `nomes.remove("Ana")` |
| `size()` | Retorna a quantidade de elementos | `nomes.size()` |
| `contains(elemento)` | Verifica se existe um elemento | `nomes.contains("Ana")` |
| `indexOf(elemento)` | Retorna o primeiro índice encontrado | `nomes.indexOf("Ana")` |
| `isEmpty()` | Verifica se está vazia | `nomes.isEmpty()` |
| `clear()` | Remove todos os elementos | `nomes.clear()` |

Exemplo completo:

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();

        nomes.add("Ana");
        nomes.add("Carlos");
        nomes.add("Maria");

        System.out.println(nomes.size());        // 3
        System.out.println(nomes.contains("Ana")); // true

        nomes.set(1, "João");

        System.out.println(nomes.get(1)); // João

        nomes.remove("Maria");

        System.out.println(nomes);
        // [Ana, João]
    }
}
```

**Atenção à sobrecarga de `remove`:**

Em uma `List<Integer>`, a chamada `numeros.remove(1)` remove o elemento que está no índice `1`, e não necessariamente o valor inteiro `1`.

Para remover o valor, podemos utilizar:

```java
numeros.remove(Integer.valueOf(1));
```

### 3.6. Percorrendo listas

**Com `for` tradicional**

```java
List<String> nomes = new ArrayList<>();

nomes.add("Ana");
nomes.add("Carlos");
nomes.add("Maria");

for (int i = 0; i < nomes.size(); i++) {
    System.out.println(nomes.get(i));
}
```

**Com `for-each`**

```java
for (String nome : nomes) {
    System.out.println(nome);
}
```

**Com `forEach` e expressão lambda**

```java
nomes.forEach(nome -> System.out.println(nome));
```

A última abordagem utiliza uma expressão lambda para definir a ação executada para cada elemento.

Se o objetivo for apenas imprimir os nomes, também podemos utilizar:

```java
nomes.forEach(System.out::println);
```

Essa sintaxe é uma referência a método.

**Cuidado ao remover elementos durante a iteração:** modificar diretamente uma `ArrayList` dentro de um `for-each` pode provocar `ConcurrentModificationException`. Para remoções durante a iteração, considere o método `removeIf` ou um `Iterator`.

Exemplo com `removeIf`:

```java
nomes.removeIf(nome -> nome.startsWith("A"));
```

Esse código remove os nomes que começam com a letra `A`.

### 3.7. Imutabilidade e listas de tamanho fixo

Existem maneiras diferentes de criar listas a partir de valores existentes.

**Lista modificável**

```java
List<String> nomes = new ArrayList<>();

nomes.add("Ana");
nomes.add("Carlos");
nomes.remove("Ana");
```

**Lista de tamanho fixo com `Arrays.asList`**

```java
import java.util.Arrays;
import java.util.List;

List<String> nomes = Arrays.asList("Ana", "Carlos");

nomes.set(0