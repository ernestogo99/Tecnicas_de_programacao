# Interfaces em Java

## Sumário

1. [O que é uma interface?](#1-o-que-é-uma-interface)
2. [Como declarar uma interface](#2-como-declarar-uma-interface)
3. [Como implementar uma interface](#3-como-implementar-uma-interface)
4. [Múltiplas interfaces](#4-múltiplas-interfaces)
5. [Métodos e atributos de uma interface](#5-métodos-e-atributos-de-uma-interface)
6. [Interfaces versus classes abstratas](#6-interfaces-versus-classes-abstratas)
7. [O que é uma interface funcional?](#7-o-que-é-uma-interface-funcional)
8. [Expressões lambda](#8-expressões-lambda)
9. [Interfaces funcionais da API Java](#9-interfaces-funcionais-da-api-java)
10. [Quando utilizar interfaces](#10-quando-utilizar-interfaces)
11. [Resumo para revisão](#11-resumo-para-revisão)

---

## 1. O que é uma interface?

Uma **interface em Java** é um contrato que define comportamentos que uma classe deve oferecer ao implementá-la.

Ela permite estabelecer quais operações uma classe disponibiliza sem precisar definir completamente como essas operações serão executadas.

Para implementar uma interface, utilizamos a palavra-chave `implements`.

Por exemplo, imagine um sistema bancário que possui diferentes formas de pagamento. Todas elas precisam oferecer uma operação para realizar o pagamento, mas cada forma pode executar essa operação de maneira diferente.

Podemos definir uma interface chamada `Pagamento`.

```java
public interface Pagamento {
    void realizarPagamento(double valor);
}
```

Essa interface estabelece que qualquer classe concreta que a implemente deverá fornecer uma implementação do método `realizarPagamento()`.

### Por que utilizar interfaces?

As interfaces ajudam a:

- Definir contratos entre componentes.
- Reduzir o acoplamento entre classes.
- Facilitar testes e manutenção.
- Permitir diferentes implementações de um mesmo comportamento.
- Aplicar o polimorfismo.
- Facilitar a substituição de implementações sem alterar o código que as utiliza.

---

## 2. Como declarar uma interface

Utilizamos a palavra-chave `interface` para declarar uma interface.

```java
public interface Animal {
    void emitirSom();
}
```

Nesse exemplo:

- `public` define a visibilidade da interface.
- `interface` declara o contrato.
- `emitirSom()` define um comportamento que as implementações concretas devem fornecer.

Por padrão, um método declarado em uma interface sem corpo é `public abstract`.

Portanto, a declaração anterior equivale a:

```java
public interface Animal {
    public abstract void emitirSom();
}
```

Não é necessário escrever `public abstract`, pois esses modificadores são implícitos nesse caso.

---

## 3. Como implementar uma interface

Uma classe implementa uma interface usando `implements`.

Considere a interface `Animal`:

```java
public interface Animal {
    void emitirSom();
}
```

Agora, podemos criar diferentes implementações.

### Classe Cachorro

```java
public class Cachorro implements Animal {

    @Override
    public void emitirSom() {
        System.out.println("Au au!");
    }
}
```

### Classe Gato

```java
public class Gato implements Animal {

    @Override
    public void emitirSom() {
        System.out.println("Miau!");
    }
}
```

As duas classes implementam o mesmo contrato, mas apresentam comportamentos diferentes.

### Testando o polimorfismo

```java
public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Cachorro();
        Animal animal2 = new Gato();

        animal1.emitirSom();
        animal2.emitirSom();
    }
}
```

Saída:

```text
Au au!
Miau!
```

Observe que as variáveis possuem o tipo `Animal`, mas os objetos concretos são `Cachorro` e `Gato`.

Isso demonstra **polimorfismo**: o mesmo contrato permite executar comportamentos diferentes, de acordo com a implementação do objeto.

Uma classe concreta precisa implementar todos os métodos abstratos herdados das interfaces, diretamente ou por meio de sua classe pai. Caso contrário, ela também deverá ser abstrata.

---

## 4. Múltiplas interfaces

Diferentemente das classes, uma classe Java pode implementar várias interfaces.

Isso permite combinar diferentes capacidades sem precisar herdar de várias classes.

Considere duas interfaces:

```java
public interface Autenticavel {
    boolean autenticar(String senha);
}
```

```java
public interface Notificavel {
    void enviarNotificacao(String mensagem);
}
```

Uma classe pode implementar ambas:

```java
public class Usuario implements Autenticavel, Notificavel {

    private String senha;

    public Usuario(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    @Override
    public void enviarNotificacao(String mensagem) {
        System.out.println("Notificação: " + mensagem);
    }
}
```

Nesse exemplo, `Usuario` possui duas capacidades:

- Autenticar um usuário.
- Enviar notificações.

A declaração `implements Autenticavel, Notificavel` indica que a classe assume os contratos das duas interfaces.

**Importante:** Java não permite herança múltipla de classes, mas permite implementar múltiplas interfaces.

---

## 5. Métodos e atributos de uma interface

As interfaces podem conter diferentes tipos de membros.

### 5.1. Métodos abstratos

São métodos sem implementação que definem comportamentos obrigatórios para as classes concretas.

```java
public interface Repositorio {
    void salvar(String dado);
}
```

### 5.2. Métodos `default`

A partir do Java 8, uma interface pode fornecer métodos de instância com implementação usando `default`.

```java
public interface Repositorio {

    void salvar(String dado);

    default void exibirMensagem() {
        System.out.println("Operação executada.");
    }
}
```

A classe que implementa a interface pode utilizar o método padrão sem precisar sobrescrevê-lo.

```java
public class RepositorioMemoria implements Repositorio {

    @Override
    public void salvar(String dado) {
        System.out.println("Salvando: " + dado);
    }
}
```

Uso:

```java
Repositorio repositorio = new RepositorioMemoria();

repositorio.salvar("Registro 1");
repositorio.exibirMensagem();
```

Saída:

```text
Salvando: Registro 1
Operação executada.
```

### 5.3. Métodos `static`

Interfaces também podem declarar métodos estáticos.

```java
public interface Calculadora {

    static int somar(int a, int b) {
        return a + b;
    }
}
```

Para chamar esse método, utilizamos o nome da interface:

```java
int resultado = Calculadora.somar(10, 20);

System.out.println(resultado);
```

Saída:

```text
30
```

O método estático pertence à interface e não é chamado por meio de uma instância.

### 5.4. Atributos de interfaces

Os atributos declarados em interfaces são implicitamente:

- `public`
- `static`
- `final`

Exemplo:

```java
public interface Configuracao {
    int LIMITE_MAXIMO = 100;
}
```

Essa declaração equivale a:

```java
public interface Configuracao {
    public static final int LIMITE_MAXIMO = 100;
}
```

O atributo representa uma constante compartilhada. Não é possível atribuir um novo valor a ela.

### 5.5. Métodos `private`

Desde o Java 9, interfaces podem declarar métodos privados para reutilizar código internamente em métodos `default` ou `static`.

```java
public interface Mensagem {

    default void exibir() {
        imprimir("Olá!");
    }

    private void imprimir(String texto) {
        System.out.println(texto);
    }
}
```

O método privado não é acessível diretamente pelas classes que implementam a interface.

---

## 6. Interfaces versus classes abstratas

Interfaces e classes abstratas permitem abstrair comportamentos, mas possuem diferenças importantes.

| Característica | Interface | Classe abstrata |
|---|---|---|
| Declaração | `interface` | `abstract class` |
| Implementação ou herança | `implements` | `extends` |
| Múltiplas declarações | Uma classe pode implementar várias interfaces | Uma classe pode estender apenas uma classe |
| Construtores | Não possui construtores de instância | Pode possuir construtores |
| Estado de instância | Não possui atributos de instância comuns | Pode possuir atributos de instância |
| Métodos abstratos | Sim | Sim |
| Métodos com implementação | `default`, `static` e `private` | Métodos concretos comuns, entre outros |
| Constantes | Campos implicitamente `public static final` | Pode ter atributos mutáveis ou constantes |

### Quando utilizar uma interface?

Utilize uma interface quando quiser definir um contrato que diferentes classes podem implementar, mesmo que elas não pertençam à mesma hierarquia de herança.

Exemplo: `Pagamento`, `Autenticavel` e `Notificavel`.

### Quando utilizar uma classe abstrata?

Utilize uma classe abstrata quando diferentes classes compartilharem estado, código e comportamentos que façam sentido em uma mesma hierarquia.

Exemplo: uma classe abstrata `Funcionario` contendo nome, salário e métodos comuns a diferentes funcionários.

**Regra prática:** interfaces representam contratos e capacidades; classes abstratas permitem compartilhar estado e implementação dentro de uma hierarquia.

---

## 7. O que é uma interface funcional?

Uma **interface funcional** é uma interface que possui exatamente um método abstrato de instância.

Ela pode possuir métodos `default`, métodos `static` e métodos privados sem deixar de ser funcional.

Métodos abstratos que correspondem a métodos públicos de `Object`, como `equals()`, não contam para esse limite.

Interfaces funcionais são especialmente úteis para expressões lambda e referências a métodos.

### Exemplo de interface funcional

```java
@FunctionalInterface
public interface Operacao {
    int executar(int a, int b);
}
```

A interface define apenas um método abstrato: `executar()`.

A anotação `@FunctionalInterface` não é obrigatória, mas ajuda o compilador a verificar se a interface respeita as regras de uma interface funcional.

Se adicionarmos outro método abstrato independente, o compilador apresentará um erro.

### Implementação tradicional

Uma forma de implementar a interface é criar uma classe:

```java
public class Soma implements Operacao {

    @Override
    public int executar(int a, int b) {
        return a + b;
    }
}
```

Uso:

```java
public class Main {
    public static void main(String[] args) {
        Operacao operacao = new Soma();

        System.out.println(operacao.executar(10, 5));
    }
}
```

Saída:

```text
15
```

Essa implementação é válida, mas podemos simplificá-la usando uma expressão lambda.

---

## 8. Expressões lambda

Uma expressão lambda permite implementar de maneira concisa o método abstrato de uma interface funcional.

Em vez de criar uma classe separada, podemos escrever:

```java
public class Main {
    public static void main(String[] args) {
        Operacao soma = (a, b) -> a + b;

        Operacao multiplicacao = (a, b) -> a * b;

        System.out.println(soma.executar(10, 5));
        System.out.println(multiplicacao.executar(10, 5));
    }
}
```

Saída:

```text
15
50
```

Nesse caso:

- `(a, b)` representa os parâmetros.
- `->` separa os parâmetros do corpo da lambda.
- `a + b` é o corpo da primeira expressão.
- `a * b` é o corpo da segunda expressão.

O compilador identifica o método que precisa ser implementado com base no tipo funcional esperado.

### Sintaxe básica

```java
(parametros) -> expressao
```

Exemplo sem parâmetros:

```java
Runnable tarefa = () -> System.out.println("Executando tarefa");
```

Exemplo com um parâmetro:

```java
java.util.function.Consumer<String> imprimir =
        texto -> System.out.println(texto);
```

Exemplo com bloco de código:

```java
Operacao operacao = (a, b) -> {
    int resultado = a + b;
    return resultado;
};
```

Quando o corpo possui várias instruções, utilizamos chaves. Se o método retorna um valor, o bloco precisa retornar esse valor quando o fluxo de execução exigir.

### O que a lambda substitui?

Uma lambda não substitui a interface. Ela fornece uma implementação do método abstrato de uma interface funcional.

No exemplo:

```java
Operacao soma = (a, b) -> a + b;
```

- `Operacao` é a interface funcional.
- `executar()` é o método abstrato.
- `(a, b) -> a + b` é a implementação fornecida pela lambda.

---

## 9. Interfaces funcionais da API Java

O Java disponibiliza interfaces funcionais prontas no pacote `java.util.function`.

As mais importantes são:

### 9.1. `Predicate<T>`

Recebe um valor e retorna `boolean`. É útil para verificações e filtros.

```java
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> maiorDeIdade = idade -> idade >= 18;

        System.out.println(maiorDeIdade.test(20));
        System.out.println(maiorDeIdade.test(15));
    }
}
```

Saída:

```text
true
false
```

Seu método abstrato é `test(T valor)`.

### 9.2. `Function<T, R>`

Recebe um valor do tipo `T` e retorna um valor do tipo `R`.

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, Integer> tamanho = texto -> texto.length();

        System.out.println(tamanho.apply("Java"));
    }
}
```

Saída:

```text
4
```

Seu método abstrato é `apply(T valor)`.

### 9.3. `Consumer<T>`

Recebe um valor e executa uma operação sem retornar um resultado.

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> imprimir =
                texto -> System.out.println(texto);

        imprimir.accept("Olá, Java!");
    }
}
```

Seu método abstrato é `accept(T valor)`.

### 9.4. `Supplier<T>`

Não recebe argumentos e fornece um resultado.

```java
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<String> mensagem = () -> "Olá!";

        System.out.println(mensagem.get());
    }
}
```

Seu método abstrato é `get()`.

### 9.5. `Runnable`

É uma interface funcional que representa uma tarefa sem parâmetros e sem retorno.

```java
Runnable tarefa = () -> System.out.println("Tarefa executada");

tarefa.run();
```

É frequentemente utilizada para representar tarefas executáveis e trabalhar com threads.

### Resumo das interfaces funcionais

| Interface | Entrada | Retorno | Método principal |
|---|---|---|---|
| `Predicate<T>` | Um `T` | `boolean` | `test()` |
| `Function<T, R>` | Um `T` | Um `R` | `apply()` |
| `Consumer<T>` | Um `T` | `void` | `accept()` |
| `Supplier<T>` | Nenhuma | Um `T` | `get()` |
| `Runnable` | Nenhuma | `void` | `run()` |

---

## 10. Quando utilizar interfaces

Imagine um sistema que precisa enviar notificações por e-mail ou SMS.

Podemos definir um contrato comum:

```java
public interface Notificador {
    void enviar(String mensagem);
}
```

Implementação para e-mail:

```java
public class NotificadorEmail implements Notificador {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando e-mail: " + mensagem);
    }
}
```

Implementação para SMS:

```java
public class NotificadorSMS implements Notificador {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando SMS: " + mensagem);
    }
}
```

Uma classe de serviço pode depender da interface, em vez de depender diretamente de uma implementação concreta:

```java
public class ServicoNotificacao {
    private final Notificador notificador;

    public ServicoNotificacao(Notificador notificador) {
        this.notificador = notificador;
    }

    public void notificar(String mensagem) {
        notificador.enviar(mensagem);
    }
}
```

Uso:

```java
public class Main {
    public static void main(String[] args) {
        Notificador notificador = new NotificadorEmail();

        ServicoNotificacao servico =
                new ServicoNotificacao(notificador);

        servico.notificar("Seu pagamento foi aprovado.");
    }
}
```

Se quisermos utilizar SMS, podemos trocar a implementação:

```java
Notificador notificador = new NotificadorSMS();
```

A classe `ServicoNotificacao` não precisa ser modificada.

Esse é um exemplo de programação orientada a abstrações: o serviço depende do contrato `Notificador`, e não diretamente de uma classe concreta.

Essa abordagem facilita testes, manutenção e substituição de implementações.

---

## 11. Resumo para revisão

- **Interface:** define um contrato de comportamentos que uma classe pode implementar.
- **`implements`:** indica que uma classe implementa uma interface.
- **`extends`:** indica herança de uma classe ou extensão de uma interface por outra interface.
- **Polimorfismo:** permite tratar diferentes implementações por meio de um mesmo tipo.
- **Múltiplas interfaces:** uma classe pode implementar várias interfaces.
- **Interface funcional:** possui exatamente um método abstrato de instância.
- **`@FunctionalInterface`:** permite ao compilador verificar se a interface atende aos requisitos de uma interface funcional.
- **Lambda:** fornece uma implementação concisa para o método abstrato de uma interface funcional.
- **`default`:** permite declarar um método de instância com implementação na interface.
- **`static`:** permite declarar métodos associados à própria interface.
- **Campos de interface:** são implicitamente `public static final`.
- **Boas práticas:** programe para interfaces quando isso reduzir o acoplamento e facilitar a substituição de implementações.

### Para memorizar

Uma interface define **o que um componente deve oferecer**. A classe que a implementa define **como esse comportamento será executado**.

Uma interface funcional segue esse mesmo princípio, mas possui apenas um método abstrato de instância, permitindo utilizar lambdas e referências a métodos.

---

**Referência para estudo:** documentação oficial do Java sobre [interfaces](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html), [expressões lambda](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) e [interfaces funcionais](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html).
