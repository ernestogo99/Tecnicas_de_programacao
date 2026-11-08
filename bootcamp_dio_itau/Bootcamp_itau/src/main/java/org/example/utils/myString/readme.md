# Java: String, StringBuilder e StringBuffer

## 📚 Sumário

1. [Classe String](#-classe-string)
    - [Características](#características)
    - [Imutabilidade](#imutabilidade)
    - [Principais métodos](#principais-métodos-da-string)
    - [Comparação de Strings](#comparação-de-strings)
    - [String Pool](#string-pool)
2. [Classe StringBuilder](#-classe-stringbuilder)
    - [Características](#características-1)
    - [Principais métodos](#principais-métodos-1)
    - [Exemplo prático](#exemplo-prático)
3. [Classe StringBuffer](#-classe-stringbuffer)
    - [Características](#características-2)
    - [Principais métodos](#principais-métodos-2)
4. [Comparação entre String, StringBuilder e StringBuffer](#-comparação-entre-string-stringbuilder-e-stringbuffer)
5. [Boas práticas](#-boas-práticas)


---

# 🔤 Classe String

A classe `String` representa uma sequência de caracteres em Java.

Ela é utilizada para armazenar e manipular textos, como nomes, endereços, mensagens e outras informações.

```java
public class Main {
    public static void main(String[] args) {
        String nome = "Ernesto";
        String mensagem = new String("Olá, mundo!");

        System.out.println(nome);
        System.out.println(mensagem);
    }
}
```

## Características

- É uma classe do pacote `java.lang`, portanto não precisa de importação.
- É utilizada para representar sequências de caracteres.
- É **imutável**: depois de criada, seu conteúdo não pode ser alterado.
- Possui diversos métodos para manipulação de textos.
- Pode ser criada utilizando literais ou o operador `new`.

## Imutabilidade

Uma das principais características da classe `String` é sua imutabilidade.

Isso significa que, após a criação de um objeto String, seu conteúdo não pode ser modificado.

Quando realizamos uma operação que aparentemente altera uma String, na verdade, um novo objeto é criado.

```java
public class Main {
    public static void main(String[] args) {
        String texto = "Olá";

        texto.concat(" mundo");

        System.out.println(texto); // Olá
    }
}
```

O método `concat()` não modifica a String original. Ele retorna uma nova String.

Para armazenar o resultado:

```java
String texto = "Olá";

texto = texto.concat(" mundo");

System.out.println(texto); // Olá mundo
```

Nesse exemplo, a variável `texto` passa a referenciar a nova String.

**Importante:** a imutabilidade proporciona maior segurança e previsibilidade, mas operações repetidas de concatenação podem gerar muitos objetos temporários.

## Principais métodos da String

| Método | Descrição | Exemplo |
|---|---|---|
| `length()` | Retorna a quantidade de caracteres | `"Java".length()` → `4` |
| `charAt(index)` | Retorna o caractere de uma posição | `"Java".charAt(0)` → `'J'` |
| `substring(inicio)` | Retorna parte da String a partir de uma posição | `"Java".substring(1)` → `"ava"` |
| `substring(inicio, fim)` | Retorna parte da String entre duas posições | `"Java".substring(0, 2)` → `"Ja"` |
| `toUpperCase()` | Converte para maiúsculas | `"java".toUpperCase()` → `"JAVA"` |
| `toLowerCase()` | Converte para minúsculas | `"JAVA".toLowerCase()` → `"java"` |
| `trim()` | Remove espaços nas extremidades | `" Java ".trim()` → `"Java"` |
| `strip()` | Remove espaços em branco Unicode nas extremidades | `" Java ".strip()` → `"Java"` |
| `contains()` | Verifica se contém uma sequência | `"Java".contains("av")` → `true` |
| `startsWith()` | Verifica se começa com determinado texto | `"Java".startsWith("Ja")` → `true` |
| `endsWith()` | Verifica se termina com determinado texto | `"Java".endsWith("va")` → `true` |
| `equals()` | Compara o conteúdo | `"Java".equals("Java")` → `true` |
| `equalsIgnoreCase()` | Compara ignorando maiúsculas e minúsculas | `"JAVA".equalsIgnoreCase("java")` → `true` |
| `replace()` | Substitui caracteres ou sequências | `"Java".replace("a", "o")` → `"Jovo"` |
| `replaceAll()` | Substitui usando expressão regular | `"abc123".replaceAll("\\d", "")` → `"abc"` |
| `split()` | Divide uma String em um array | `"a,b,c".split(",")` |
| `isEmpty()` | Verifica se está vazia | `"".isEmpty()` → `true` |
| `isBlank()` | Verifica se está vazia ou contém somente espaços | `"   ".isBlank()` → `true` |
| `concat()` | Concatena duas Strings | `"Java".concat(" 21")` → `"Java 21"` |

### Exemplos de utilização

```java
public class Main {
    public static void main(String[] args) {
        String linguagem = "Java";

        System.out.println(linguagem.length());       // 4
        System.out.println(linguagem.charAt(0));      // J
        System.out.println(linguagem.toUpperCase());  // JAVA
        System.out.println(linguagem.toLowerCase());  // java
        System.out.println(linguagem.substring(1));   // ava
        System.out.println(linguagem.contains("av")); // true
        System.out.println(linguagem.replace("J", "K")); // Kava
    }
}
```

### Atenção: `isEmpty()` e `isBlank()`

Esses métodos possuem comportamentos diferentes:

```java
String texto1 = "";
String texto2 = "   ";

System.out.println(texto1.isEmpty()); // true
System.out.println(texto2.isEmpty()); // false

System.out.println(texto1.isBlank()); // true
System.out.println(texto2.isBlank()); // true
```

- `isEmpty()`: verifica se a quantidade de caracteres é zero.
- `isBlank()`: verifica se a String está vazia ou contém somente caracteres considerados espaços em branco.

## Comparação de Strings

Em Java, existem duas maneiras comuns de comparar Strings: `==` e `equals()`.

### Operador `==`

Compara se duas variáveis apontam para o mesmo objeto na memória.

```java
String texto1 = new String("Java");
String texto2 = new String("Java");

System.out.println(texto1 == texto2); // false
```

Mesmo que o conteúdo seja igual, os objetos são diferentes.

### Método `equals()`

Compara o conteúdo das Strings.

```java
String texto1 = new String("Java");
String texto2 = new String("Java");

System.out.println(texto1.equals(texto2)); // true
```

**Regra:** para comparar o conteúdo de Strings, utilize `equals()`.

## String Pool

O String Pool é uma área especial gerenciada pela JVM para reutilização de Strings literais.

Quando criamos Strings utilizando literais, a JVM pode reutilizar objetos existentes no pool.

```java
String texto1 = "Java";
String texto2 = "Java";

System.out.println(texto1 == texto2); // true
```

Nesse caso, as duas variáveis apontam para a mesma referência.

Já utilizando `new`:

```java
String texto1 = new String("Java");
String texto2 = new String("Java");

System.out.println(texto1 == texto2); // false
```

O operador `new` cria novos objetos, mesmo que o conteúdo seja igual.

### Método `intern()`

O método `intern()` retorna a representação canônica da String no pool.

```java
String texto1 = new String("Java");
String texto2 = "Java";

System.out.println(texto1 == texto2); // false

String texto3 = texto1.intern();

System.out.println(texto3 == texto2); // true
```

---

# 🧱 Classe StringBuilder

A classe `StringBuilder` permite criar e manipular sequências de caracteres de maneira **mutável**.

Diferentemente da classe String, ela permite modificar o conteúdo do próprio objeto sem precisar criar uma nova String a cada operação.

É especialmente útil quando precisamos realizar diversas concatenações ou modificações de texto.

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder texto = new StringBuilder("Olá");

        texto.append(" mundo");

        System.out.println(texto); // Olá mundo
    }
}
```

## Características

- É mutável.
- Permite modificar o conteúdo do próprio objeto.
- Possui métodos para adicionar, remover, substituir e inserir caracteres.
- Geralmente apresenta melhor desempenho que concatenações repetidas de String.
- Não é sincronizada, portanto não oferece segurança automática para acesso concorrente entre threads.

## Principais métodos

| Método | Descrição | Exemplo |
|---|---|---|
| `append()` | Adiciona conteúdo ao final | `texto.append(" Java")` |
| `insert()` | Insere conteúdo em uma posição | `texto.insert(0, "Olá ")` |
| `delete()` | Remove caracteres de um intervalo | `texto.delete(0, 2)` |
| `deleteCharAt()` | Remove um caractere específico | `texto.deleteCharAt(0)` |
| `replace()` | Substitui caracteres de um intervalo | `texto.replace(0, 2, "Oi")` |
| `reverse()` | Inverte a sequência | `texto.reverse()` |
| `length()` | Retorna o tamanho atual | `texto.length()` |
| `capacity()` | Retorna a capacidade atual do buffer | `texto.capacity()` |
| `charAt()` | Retorna o caractere de uma posição | `texto.charAt(0)` |
| `setCharAt()` | Altera um caractere específico | `texto.setCharAt(0, 'J')` |
| `substring()` | Retorna uma String com parte do conteúdo | `texto.substring(0, 3)` |
| `toString()` | Converte para String | `texto.toString()` |

## Exemplo prático

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder texto = new StringBuilder("Java");

        // Adiciona conteúdo
        texto.append(" é uma linguagem");

        // Insere conteúdo na posição 0
        texto.insert(0, "Aprendendo ");

        // Substitui os caracteres entre as posições 0 e 10
        texto.replace(0, 10, "Estudando");

        // Remove caracteres entre as posições 0 e 3
        texto.delete(0, 3);

        // Inverte o conteúdo
        texto.reverse();

        // Converte para String
        String resultado = texto.toString();

        System.out.println(resultado);
    }
}
```

### Entendendo `capacity()` e `length()`

Esses métodos possuem finalidades diferentes:

- `length()`: retorna a quantidade de caracteres armazenados.
- `capacity()`: retorna a capacidade atual do buffer, ou seja, quanto espaço ele possui antes de precisar aumentar sua capacidade.

```java
StringBuilder texto = new StringBuilder("Java");

System.out.println(texto.length());   // 4
System.out.println(texto.capacity()); // 20
```

Por padrão, um `StringBuilder` criado com o construtor vazio possui capacidade inicial de 16 caracteres. Quando criado com uma String, sua capacidade inicial é o tamanho da String mais 16.

A capacidade pode aumentar automaticamente conforme o conteúdo cresce.

---

# 🔒 Classe StringBuffer

A classe `StringBuffer` possui características semelhantes à classe `StringBuilder`.

Ela também permite manipular sequências de caracteres de maneira mutável.

Sua principal diferença é que seus métodos de modificação são sincronizados, oferecendo segurança para operações concorrentes entre threads.

```java
public class Main {
    public static void main(String[] args) {
        StringBuffer texto = new StringBuffer("Olá");

        texto.append(" mundo");

        System.out.println(texto); // Olá mundo
    }
}
```

## Características

- É mutável.
- Permite modificar o conteúdo do próprio objeto.
- Possui métodos semelhantes aos de `StringBuilder`.
- Seus métodos são sincronizados.
- É adequada quando várias threads precisam compartilhar e modificar o mesmo objeto.
- Pode apresentar desempenho inferior ao `StringBuilder` devido à sincronização.

## Principais métodos

A classe `StringBuffer` possui métodos muito semelhantes aos de `StringBuilder`.

| Método | Descrição |
|---|---|
| `append()` | Adiciona conteúdo ao final |
| `insert()` | Insere conteúdo em uma posição |
| `delete()` | Remove caracteres de um intervalo |
| `deleteCharAt()` | Remove um caractere específico |
| `replace()` | Substitui caracteres de um intervalo |
| `reverse()` | Inverte a sequência |
| `length()` | Retorna o tamanho atual |
| `capacity()` | Retorna a capacidade atual |
| `charAt()` | Retorna o caractere de uma posição |
| `setCharAt()` | Altera um caractere específico |
| `substring()` | Retorna parte do conteúdo como String |
| `toString()` | Converte para String |

## Exemplo prático

```java
public class Main {
    public static void main(String[] args) {
        StringBuffer texto = new StringBuffer("Java");

        texto.append(" e Spring Boot");

        System.out.println(texto);

        texto.insert(0, "Estudando ");

        System.out.println(texto);

        texto.reverse();

        System.out.println(texto);

        String resultado = texto.toString();

        System.out.println(resultado);
    }
}
```

### O que significa sincronização?

A sincronização permite controlar o acesso de múltiplas threads a um recurso compartilhado.

No caso de `StringBuffer`, seus métodos sincronizados ajudam a evitar que duas threads executem simultaneamente operações conflitantes no mesmo objeto.

**Importante:** isso não significa que qualquer sequência de operações envolvendo `StringBuffer` seja automaticamente atômica. Se várias chamadas precisarem funcionar como uma única operação indivisível, será necessário sincronizar esse conjunto de operações.

---

# ⚖️ Comparação entre String, StringBuilder e StringBuffer

As três classes trabalham com sequências de caracteres, mas possuem diferenças importantes.

| Característica | String | StringBuilder | StringBuffer |
|---|---|---|---|
| Mutabilidade | Imutável | Mutável | Mutável |
| Sincronização dos métodos | Não aplicável | Não | Sim |
| Segurança para acesso concorrente | Imutável após construção | Não oferece sincronização | Métodos sincronizados |
| Desempenho em modificações repetidas | Geralmente inferior | Geralmente superior | Pode ser inferior ao StringBuilder |
| Uso principal | Armazenamento de textos | Manipulação frequente de textos | Manipulação compartilhada entre threads |

## Quando utilizar cada classe?

### String

Utilize quando precisar armazenar textos que não serão modificados constantemente.

```java
String nome = "Ernesto";
String email = "ernesto@email.com";
```

### StringBuilder

Utilize quando precisar realizar diversas modificações ou concatenações, principalmente dentro de loops.

```java
StringBuilder resultado = new StringBuilder();

for (int i = 0; i < 5; i++) {
    resultado.append(i).append(" ");
}

System.out.println(resultado);
```

### StringBuffer

Utilize quando precisar compartilhar uma sequência de caracteres mutável entre múltiplas threads e desejar sincronização nos métodos individuais.

```java
StringBuffer resultado = new StringBuffer();

resultado.append("Processamento ");
resultado.append("concorrente");

System.out.println(resultado);
```

---

# 💡 Boas práticas

1. **Prefira String para textos comuns:** não existe necessidade de utilizar StringBuilder para toda operação com texto.
2. **Utilize StringBuilder em concatenações repetitivas:** principalmente dentro de loops.
3. **Evite StringBuffer sem necessidade:** a sincronização pode gerar custo adicional.
4. **Utilize equals() para comparar conteúdo:** evite utilizar `==` para comparar Strings.
5. **Utilize toString() quando necessário:** converta StringBuilder ou StringBuffer para String quando precisar entregar o resultado a outro método ou camada.
6. **Aproveite os métodos da String:** métodos como `split()`, `replace()`, `substring()` e `contains()` facilitam a manipulação de textos.

# 📌 Resumo

| Conceito | Definição |
|---|---|
| String | Classe imutável para representar textos |
| StringBuilder | Classe mutável, sem sincronização dos métodos |
| StringBuffer | Classe mutável, com métodos sincronizados |
| Imutabilidade | O conteúdo do objeto não pode ser alterado após sua criação |
| Mutabilidade | O conteúdo do objeto pode ser modificado |
| String Pool | Área gerenciada pela JVM para reutilização de Strings |
| `equals()` | Compara o conteúdo dos objetos |
| `==` | Compara referências de objetos |
| `append()` | Adiciona conteúdo ao final |
| `reverse()` | Inverte a sequência de caracteres |
| `toString()` | Retorna uma representação em String |

**Regra para memorizar:**

- `String` → texto imutável.
- `StringBuilder` → texto mutável e sem sincronização.
- `StringBuffer` → texto mutável com métodos sincronizados.

**Dica para provas:** a principal diferença entre `StringBuilder` e `StringBuffer` é a sincronização. Ambos são mutáveis e possuem métodos muito semelhantes.