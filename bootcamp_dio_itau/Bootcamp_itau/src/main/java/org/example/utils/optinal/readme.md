# Java: Optional

## 📚 Sumário

1. [O que é Optional?](#-o-que-é-optional)
2. [Criando objetos Optional](#-criando-objetos-optional)
3. [Principais métodos](#-principais-métodos)
4. [Verificando a presença de valores](#-verificando-a-presença-de-valores)
5. [Obtendo valores](#-obtendo-valores)
6. [Métodos orElse, orElseGet e orElseThrow](#-métodos-orelse-orelseget-e-orelsethrow)
7. [Transformando valores com map()](#-transformando-valores-com-map)
8. [Diferença entre map() e flatMap()](#-diferença-entre-map-e-flatmap)
9. [Optional com expressões lambda](#-optional-com-expressões-lambda)
10. [Optional com Streams](#-optional-com-streams)
11. [Optional com Spring Boot e JPA](#-optional-com-spring-boot-e-jpa)
12. [Boas práticas](#-boas-práticas)
13. [Exercícios](#-exercícios)

---

# 🔎 O que é Optional?

`Optional` é uma classe do pacote `java.util` introduzida no Java 8, utilizada para representar um valor que pode ou não estar presente.

Seu principal objetivo é tornar explícito o tratamento de valores ausentes, reduzindo a ocorrência de `NullPointerException`.

Em vez de retornar diretamente um objeto que pode ser `null`, podemos retornar um `Optional` indicando que o valor pode estar presente ou ausente.

Exemplo:

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> nome = Optional.of("Ernesto");

        System.out.println(nome);
    }
}
```

## Por que utilizar Optional?

Considere um método que busca um usuário pelo ID:

```java
public Usuario buscarUsuario(Long id) {
    // Pode retornar null caso não encontre o usuário
    return null;
}
```

Quem utiliza esse método precisa lembrar de verificar se o retorno é `null`.

```java
Usuario usuario = buscarUsuario(1L);

if (usuario != null) {
    System.out.println(usuario.getNome());
}
```

Com `Optional`, podemos deixar explícito que o resultado pode não existir:

```java
public Optional<Usuario> buscarUsuario(Long id) {
    // Retorna um Optional contendo um usuário ou vazio
    return Optional.empty();
}
```

Utilização:

```java
Optional<Usuario> usuario = buscarUsuario(1L);

if (usuario.isPresent()) {
    System.out.println(usuario.get().getNome());
}
```

**Importante:** `Optional` não elimina completamente a possibilidade de `NullPointerException`. Seu objetivo é incentivar um tratamento explícito dos valores ausentes.

---

# 🏗️ Criando objetos Optional

Existem três principais maneiras de criar um objeto `Optional`.

## 1. Optional.of()

Utilizado quando temos certeza de que o valor não é `null`.

```java
Optional<String> nome = Optional.of("Ernesto");
```

Se passarmos `null`, será lançada uma `NullPointerException`.

```java
Optional<String> nome = Optional.of(null); // NullPointerException
```

## 2. Optional.ofNullable()

Utilizado quando o valor pode ser `null`.

```java
String nome = null;

Optional<String> resultado = Optional.ofNullable(nome);

System.out.println(resultado); // Optional.empty
```

Se o valor não for `null`, o Optional será criado contendo esse valor.

```java
String nome = "Ernesto";

Optional<String> resultado = Optional.ofNullable(nome);

System.out.println(resultado); // Optional[Ernesto]
```

## 3. Optional.empty()

Cria um Optional vazio, sem nenhum valor.

```java
Optional<String> nome = Optional.empty();

System.out.println(nome); // Optional.empty
```

### Comparação

| Método | Descrição | Aceita null? |
|---|---|---|
| `Optional.of()` | Cria um Optional com valor obrigatório | Não |
| `Optional.ofNullable()` | Cria um Optional a partir de um valor que pode ser nulo | Sim |
| `Optional.empty()` | Cria um Optional vazio | Não recebe argumento |

---

# 🔧 Principais métodos

| Método | Descrição |
|---|---|
| `of()` | Cria um Optional com um valor não nulo |
| `ofNullable()` | Cria um Optional que pode estar vazio |
| `empty()` | Cria um Optional vazio |
| `isPresent()` | Verifica se existe um valor |
| `isEmpty()` | Verifica se não existe um valor |
| `get()` | Retorna o valor presente |
| `orElse()` | Retorna o valor ou uma alternativa |
| `orElseGet()` | Retorna o valor ou obtém uma alternativa por Supplier |
| `orElseThrow()` | Retorna o valor ou lança uma exceção |
| `ifPresent()` | Executa uma ação se houver valor |
| `ifPresentOrElse()` | Executa uma ação para valor presente ou ausente |
| `filter()` | Mantém o valor caso satisfaça uma condição |
| `map()` | Transforma o valor presente |
| `flatMap()` | Transforma o valor evitando Optional aninhado |
| `or()` | Fornece outro Optional caso o atual esteja vazio |
| `stream()` | Converte o Optional em um Stream com zero ou um elemento |

---

# 🔍 Verificando a presença de valores

## Método isPresent()

Verifica se o Optional possui um valor.

Retorna:

- `true`: existe um valor.
- `false`: está vazio.

```java
Optional<String> nome = Optional.of("Ernesto");

System.out.println(nome.isPresent()); // true
```

Exemplo com Optional vazio:

```java
Optional<String> nome = Optional.empty();

System.out.println(nome.isPresent()); // false
```

## Método isEmpty()

Verifica se o Optional não possui um valor.

```java
Optional<String> nome = Optional.empty();

System.out.println(nome.isEmpty()); // true
```

Esse método foi introduzido no Java 11.

## Método ifPresent()

Executa uma ação somente quando existe um valor.

Recebe uma expressão lambda por meio da interface funcional `Consumer`.

```java
Optional<String> nome = Optional.of("Ernesto");

nome.ifPresent(valor -> System.out.println(valor));
```

Resultado:

```text
Ernesto
```

Se o Optional estiver vazio, nenhuma ação será executada.

## Método ifPresentOrElse()

Permite definir uma ação para quando existe um valor e outra para quando está vazio.

Foi introduzido no Java 9.

```java
Optional<String> nome = Optional.empty();

nome.ifPresentOrElse(
    valor -> System.out.println("Nome: " + valor),
    () -> System.out.println("Nome não encontrado")
);
```

Resultado:

```text
Nome não encontrado
```

---

# 📦 Obtendo valores

## Método get()

Retorna o valor armazenado no Optional.

```java
Optional<String> nome = Optional.of("Ernesto");

String resultado = nome.get();

System.out.println(resultado);
```

**Atenção:** se o Optional estiver vazio, `get()` lança `NoSuchElementException`.

```java
Optional<String> nome = Optional.empty();

String resultado = nome.get(); // NoSuchElementException
```

Por isso, evite utilizar `get()` sem verificar previamente a presença do valor.

Na maioria das situações, métodos como `orElse()`, `orElseGet()` e `orElseThrow()` tornam o código mais seguro e expressivo.

---

# 🔄 Métodos orElse(), orElseGet() e orElseThrow()

Esses métodos permitem definir o comportamento quando o Optional está vazio.

## 1. orElse()

Retorna o valor armazenado ou um valor alternativo.

```java
Optional<String> nome = Optional.empty();

String resultado = nome.orElse("Usuário desconhecido");

System.out.println(resultado);
```

Resultado:

```text
Usuário desconhecido
```

Se existir um valor, ele será retornado.

```java
Optional<String> nome = Optional.of("Ernesto");

String resultado = nome.orElse("Usuário desconhecido");

System.out.println(resultado); // Ernesto
```

**Importante:** o argumento de `orElse()` é avaliado mesmo quando o Optional possui um valor.

## 2. orElseGet()

Retorna o valor armazenado ou executa um `Supplier` para produzir uma alternativa.

```java
Optional<String> nome = Optional.empty();

String resultado = nome.orElseGet(() -> "Usuário desconhecido");

System.out.println(resultado);
```

A principal diferença é que o Supplier só é executado quando o Optional está vazio.

## 3. orElseThrow()

Retorna o valor ou lança uma exceção caso esteja vazio.

```java
Optional<String> nome = Optional.empty();

String resultado = nome.orElseThrow(
    () -> new IllegalArgumentException("Usuário não encontrado")
);
```

Também existe a versão sem argumentos, que lança `NoSuchElementException`:

```java
String resultado = nome.orElseThrow();
```

### Comparação entre os métodos

| Método | Com valor | Sem valor |
|---|---|---|
| `orElse()` | Retorna o valor | Retorna uma alternativa |
| `orElseGet()` | Retorna o valor | Executa um Supplier |
| `orElseThrow()` | Retorna o valor | Lança uma exceção |

### Diferença entre orElse() e orElseGet()

Essa é uma diferença bastante cobrada em provas e entrevistas.

```java
public static String buscarNomePadrao() {
    System.out.println("Executando método...");
    return "Desconhecido";
}
```

Utilizando `orElse()`:

```java
Optional<String> nome = Optional.of("Ernesto");

String resultado = nome.orElse(buscarNomePadrao());
```

Mesmo existindo um valor, o método `buscarNomePadrao()` será executado.

Utilizando `orElseGet()`:

```java
Optional<String> nome = Optional.of("Ernesto");

String resultado = nome.orElseGet(() -> buscarNomePadrao());
```

Nesse caso, o método não será executado, pois já existe um valor.

**Regra para memorizar:**

- `orElse()` → avalia a alternativa antecipadamente.
- `orElseGet()` → avalia a alternativa somente quando necessário.

---

# 🔀 Transformando valores com map()

O método `map()` permite transformar o valor armazenado no Optional.

Ele recebe uma função por meio da interface funcional `Function`.

Exemplo:

```java
Optional<String> nome = Optional.of("ernesto");

Optional<String> resultado = nome.map(String::toUpperCase);

System.out.println(resultado); // Optional[ERNESTO]
```

Nesse exemplo, o conteúdo foi transformado para letras maiúsculas.

Podemos utilizar expressões lambda:

```java
Optional<Integer> numero = Optional.of(10);

Optional<Integer> resultado = numero.map(valor -> valor * 2);

System.out.println(resultado); // Optional[20]
```

Se o Optional estiver vazio, a função não será executada e o resultado também será vazio.

```java
Optional<String> nome = Optional.empty();

Optional<String> resultado = nome.map(String::toUpperCase);

System.out.println(resultado); // Optional.empty
```

## Encadeamento de map()

Podemos encadear várias transformações.

```java
Optional<String> nome = Optional.of("  ernesto  ");

Optional<String> resultado = nome
    .map(String::trim)
    .map(String::toUpperCase)
    .map(valor -> valor + " MEDEIROS");

System.out.println(resultado); // Optional[ERNESTO MEDEIROS]
```

Cada operação é executada somente se existir um valor.

---

# 🔗 Diferença entre map() e flatMap()

Os métodos `map()` e `flatMap()` são utilizados para transformar valores, mas possuem comportamentos diferentes.

## map()

Recebe uma função que transforma o valor e envolve o resultado em outro Optional.

```java
Optional<String> nome = Optional.of("Ernesto");

Optional<Integer> tamanho = nome.map(String::length);

System.out.println(tamanho); // Optional[7]
```

## flatMap()

Recebe uma função que já retorna um Optional e evita criar um Optional dentro de outro Optional.

Exemplo:

```java
public static Optional<String> buscarEmail() {
    return Optional.of("ernesto@email.com");
}
```

Utilizando `map()`:

```java
Optional<String> nome = Optional.of("Ernesto");

Optional<Optional<String>> resultado =
    nome.map(valor -> buscarEmail());
```

Observe que o resultado é um `Optional` aninhado:

```text
Optional<Optional<String>>
```

Utilizando `flatMap()`:

```java
Optional<String> nome = Optional.of("Ernesto");

Optional<String> resultado =
    nome.flatMap(valor -> buscarEmail());
```

Agora temos somente:

```text
Optional<String>
```

### Comparação

| Método | Comportamento |
|---|---|
| `map()` | Transforma o valor e envolve o resultado em Optional |
| `flatMap()` | Transforma o valor quando a função já retorna Optional, evitando aninhamento |

**Dica para provas:** utilize `map()` quando a transformação retorna um valor comum e `flatMap()` quando a transformação já retorna um Optional.

---

# 🧩 Optional com expressões lambda

Optional trabalha muito bem com interfaces funcionais e expressões lambda.

Alguns métodos importantes utilizam essas interfaces:

| Método | Interface funcional | Finalidade |
|---|---|---|
| `ifPresent()` | Consumer | Executa uma ação |
| `ifPresentOrElse()` | Consumer e Runnable | Executa uma ação para cada situação |
| `orElseGet()` | Supplier | Fornece um valor alternativo |
| `orElseThrow()` | Supplier | Fornece uma exceção |
| `map()` | Function | Transforma um valor |
| `flatMap()` | Function | Transforma um valor retornando Optional |
| `filter()` | Predicate | Filtra um valor |

Exemplo:

```java
Optional<String> nome = Optional.of("Ernesto");

nome
    .filter(valor -> valor.length() > 5)
    .map(String::toUpperCase)
    .ifPresent(System.out::println);
```

Resultado:

```text
ERNESTO
```

---

# 🔍 Optional com filter()

O método `filter()` verifica se o valor presente satisfaz uma condição.

Ele recebe um `Predicate`, que retorna `true` ou `false`.

```java
Optional<Integer> idade = Optional.of(25);

Optional<Integer> resultado = idade.filter(valor -> valor >= 18);

System.out.println(resultado); // Optional[25]
```

Se a condição não for satisfeita, retorna um Optional vazio.

```java
Optional<Integer> idade = Optional.of(15);

Optional<Integer> resultado = idade.filter(valor -> valor >= 18);

System.out.println(resultado); // Optional.empty
```

Também podemos encadear com outros métodos:

```java
Optional<String> nome = Optional.of("Ernesto");

nome
    .filter(valor -> valor.startsWith("E"))
    .map(String::toUpperCase)
    .ifPresent(System.out::println);
```

---

# 🌊 Optional com Streams

A partir do Java 9, podemos converter um Optional em Stream utilizando `stream()`.

Um Optional pode produzir:

- Um Stream com um elemento, caso exista um valor.
- Um Stream vazio, caso não exista um valor.

Exemplo:

```java
Optional<String> nome = Optional.of("Ernesto");

nome.stream()
    .forEach(System.out::println);
```

Também é útil quando trabalhamos com uma coleção de Optional.

```java
List<Optional<String>> nomes = List.of(
    Optional.of("Ernesto"),
    Optional.empty(),
    Optional.of("Maria")
);

List<String> resultado = nomes.stream()
    .flatMap(Optional::stream)
    .toList();

System.out.println(resultado);
```

Resultado:

```text
[Ernesto, Maria]
```

O `flatMap()` elimina os Optional vazios e mantém somente os valores presentes.

---

# 🌱 Optional com Spring Boot e JPA

Em aplicações Spring Boot, Optional é bastante utilizado em métodos de busca de repositórios.

Exemplo de repository:

```java
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
```

O método `findByEmail()` retorna um Optional porque pode não existir um usuário com o e-mail informado.

## Utilizando Optional no Service

```java
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Usuário não encontrado")
            );
    }
}
```

Nesse exemplo:

1. O repository realiza a busca.
2. O resultado é um `Optional<Usuario>`.
3. `orElseThrow()` retorna o usuário caso exista.
4. Caso contrário, lança uma exceção.

Uma implementação mais adequada para uma API REST pode utilizar uma exceção específica:

```java
public Usuario buscarPorId(Long id) {
    return repository.findById(id)
        .orElseThrow(() ->
            new UsuarioNaoEncontradoException(
                "Usuário não encontrado: " + id
            )
        );
}
```

**Importante:** é comum utilizar Optional como retorno de métodos de busca, mas não é recomendado utilizá-lo indiscriminadamente em atributos de entidades JPA, parâmetros de métodos ou DTOs.

---

# 💡 Boas práticas

1. **Utilize Optional como retorno:** principalmente em métodos que podem não encontrar um resultado.
2. **Evite utilizar get() diretamente:** prefira `orElse()`, `orElseGet()` ou `orElseThrow()`.
3. **Utilize of() quando o valor não pode ser null:** caso contrário, utilize `ofNullable()`.
4. **Prefira orElseGet() para operações custosas:** evita executar a alternativa quando já existe um valor.
5. **Utilize map() para transformações:** permite encadear operações sem verificar `null` manualmente.
6. **Utilize flatMap() para evitar Optional aninhado:** principalmente quando a função já retorna Optional.
7. **Evite utilizar Optional em atributos de entidades:** ele foi projetado principalmente para representar retornos opcionais.
8. **Não utilize Optional para substituir qualquer null:** aplique quando a ausência de valor faz parte do contrato do método.

---

# 📝 Exercícios

## Exercício 1 — Criação de Optional

Crie três objetos Optional:

- Um utilizando `of()`.
- Um utilizando `ofNullable()` com `null`.
- Um utilizando `empty()`.

Exiba os resultados.

## Exercício 2 — Verificação de valores

Crie um Optional contendo seu nome.

Utilize:

- `isPresent()`;
- `isEmpty()`;
- `ifPresent()`;
- `ifPresentOrElse()`.

Observe o comportamento de cada método.

## Exercício 3 — Valores alternativos

Crie um Optional vazio e utilize:

- `orElse()`;
- `orElseGet()`;
- `orElseThrow()`.

Explique a diferença entre eles.

## Exercício 4 — Transformação

Crie um Optional contendo uma String.

Utilize `map()` para:

1. Remover espaços desnecessários.
2. Converter para letras maiúsculas.
3. Obter a quantidade de caracteres.

## Exercício 5 — Filter

Crie um Optional contendo uma idade.

Utilize `filter()` para verificar se a idade é maior ou igual a 18.

Caso a condição seja satisfeita, exiba uma mensagem.

## Exercício 6 — map() e flatMap()

Crie dois métodos:

- `buscarUsuario()`, retornando `Optional<Usuario>`.
- `buscarEmail()`, recebendo um usuário e retornando `Optional<String>`.

Utilize `map()` e `flatMap()` para observar a diferença entre os resultados.

## Exercício 7 — Spring Boot

Crie um endpoint que receba o ID de um usuário e utilize o repository para buscar o registro.

Caso o usuário não exista, lance uma exceção personalizada e retorne HTTP 404.

---

# 📌 Resumo

| Conceito | Definição |
|---|---|
| `Optional` | Representa um valor que pode ou não estar presente |
| `of()` | Cria Optional com valor não nulo |
| `ofNullable()` | Cria Optional a partir de valor que pode ser null |
| `empty()` | Cria Optional vazio |
| `isPresent()` | Verifica se existe valor |
| `isEmpty()` | Verifica se está vazio |
| `get()` | Obtém o valor, lançando exceção se vazio |
| `orElse()` | Retorna valor ou alternativa |
| `orElseGet()` | Retorna valor ou executa Supplier |
| `orElseThrow()` | Retorna valor ou lança exceção |
| `ifPresent()` | Executa uma ação se houver valor |
| `map()` | Transforma o valor |
| `flatMap()` | Transforma evitando Optional aninhado |
| `filter()` | Filtra o valor por uma condição |
| `stream()` | Converte Optional em Stream |

## 🎯 Regra para memorizar

- `Optional.of()` → valor obrigatório.
- `Optional.ofNullable()` → valor pode ser null.
- `Optional.empty()` → Optional vazio.
- `orElse()` → alternativa avaliada antecipadamente.
- `orElseGet()` → alternativa avaliada somente quando necessária.
- `orElseThrow()` → lança exceção se estiver vazio.
- `map()` → transforma um valor.
- `flatMap()` → transforma retornando Optional sem aninhamento.
- `filter()` → mantém o valor se satisfizer uma condição.

**Dica para provas:** lembre-se de que `Optional` foi introduzido no Java 8 para representar explicitamente a possibilidade de ausência de valor. Sua utilização é especialmente recomendada como tipo de retorno de métodos.