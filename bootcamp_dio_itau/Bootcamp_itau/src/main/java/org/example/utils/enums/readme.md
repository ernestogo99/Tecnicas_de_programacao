# Java: Enum

## 📚 Sumário

1. [O que é Enum?](#-o-que-é-enum)
2. [Criando um Enum](#-criando-um-enum)
3. [Principais métodos](#-principais-métodos)
4. [Percorrendo valores de um Enum](#-percorrendo-valores-de-um-enum)
5. [Enum com Switch](#-enum-com-switch)
6. [Enum com atributos e construtores](#-enum-com-atributos-e-construtores)
7. [Enum com métodos](#-enum-com-métodos)
8. [Enum implementando interfaces](#-enum-implementando-interfaces)
9. [Enum com Spring Boot e JPA](#-enum-com-spring-boot-e-jpa)
10. [Boas práticas](#-boas-práticas)


---

# 🔎 O que é Enum?

`enum` é um recurso da linguagem Java utilizado para representar um conjunto fixo de constantes.

É especialmente útil quando precisamos trabalhar com valores previamente definidos, evitando a utilização de Strings ou números soltos no código.

Exemplos de situações em que podemos utilizar enums:

- Dias da semana;
- Estados de um pedido;
- Níveis de acesso;
- Tipos de pagamento;
- Status de usuários;
- Categorias de produtos;
- Prioridades de tarefas.

Exemplo:

```java
public enum StatusPedido {
    PENDENTE,
    PROCESSANDO,
    ENVIADO,
    ENTREGUE,
    CANCELADO
}
```

Nesse exemplo, `StatusPedido` representa um conjunto limitado de estados possíveis para um pedido.

## Características

- Representa um conjunto fixo de constantes.
- É um tipo especial de classe em Java.
- Cada constante é uma instância do próprio enum.
- Pode possuir atributos, construtores e métodos.
- Pode implementar interfaces.
- Não pode estender outras classes, pois todos os enums herdam implicitamente de `java.lang.Enum`.
- Não pode ser instanciado utilizando `new`.

**Importante:** as constantes de um enum são objetos, não apenas valores textuais.

---

# 🏗️ Criando um Enum

Para criar um enum, utilizamos a palavra-chave `enum`.

```java
public enum DiaSemana {
    DOMINGO,
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO
}
```

Podemos utilizar esse enum em uma classe normalmente.

```java
public class Main {
    public static void main(String[] args) {
        DiaSemana dia = DiaSemana.SEGUNDA;

        System.out.println(dia); // SEGUNDA
    }
}
```

Observe que não precisamos utilizar `new` para acessar uma constante.

Basta utilizar o nome do enum seguido do nome da constante.

## Utilizando Enum em uma classe

```java
public enum StatusUsuario {
    ATIVO,
    INATIVO,
    BLOQUEADO
}
```

```java
public class Usuario {

    private String nome;
    private StatusUsuario status;

    public Usuario(String nome, StatusUsuario status) {
        this.nome = nome;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public StatusUsuario getStatus() {
        return status;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario(
            "Ernesto",
            StatusUsuario.ATIVO
        );

        System.out.println(usuario.getNome());
        System.out.println(usuario.getStatus());
    }
}
```

Utilizar enums evita valores inválidos, como `"ATIVADO"`, `"ativo123"` ou `"BLOQUEADO "`.

---

# 🔧 Principais métodos

Todo enum possui métodos herdados de `java.lang.Enum` e alguns métodos estáticos disponibilizados pelo próprio tipo de enum.

| Método | Descrição |
|---|---|
| `values()` | Retorna um array com todas as constantes do enum |
| `valueOf()` | Retorna uma constante a partir do seu nome |
| `name()` | Retorna o nome exato da constante |
| `ordinal()` | Retorna a posição da constante, começando em zero |
| `toString()` | Retorna a representação textual da constante |
| `compareTo()` | Compara a posição das constantes dentro do enum |
| `getDeclaringClass()` | Retorna a classe do enum ao qual a constante pertence |

## Método values()

Retorna todas as constantes declaradas no enum.

```java
public enum Nivel {
    BAIXO,
    MEDIO,
    ALTO
}
```

```java
public class Main {
    public static void main(String[] args) {
        Nivel[] niveis = Nivel.values();

        for (Nivel nivel : niveis) {
            System.out.println(nivel);
        }
    }
}
```

Resultado:

```text
BAIXO
MEDIO
ALTO
```

## Método valueOf()

Converte uma String para a constante correspondente do enum.

```java
public class Main {
    public static void main(String[] args) {
        String nome = "ALTO";

        Nivel nivel = Nivel.valueOf(nome);

        System.out.println(nivel); // ALTO
    }
}
```

**Atenção:** o método `valueOf()` diferencia letras maiúsculas e minúsculas.

```java
Nivel.valueOf("ALTO"); // Funciona
Nivel.valueOf("alto"); // IllegalArgumentException
```

Se o valor não corresponder a uma constante, será lançada uma `IllegalArgumentException`.

## Método name()

Retorna o nome exato da constante.

```java
Nivel nivel = Nivel.MEDIO;

System.out.println(nivel.name()); // MEDIO
```

## Método ordinal()

Retorna a posição da constante no enum, começando em zero.

```java
public enum Nivel {
    BAIXO,
    MEDIO,
    ALTO
}
```

```java
System.out.println(Nivel.BAIXO.ordinal()); // 0
System.out.println(Nivel.MEDIO.ordinal()); // 1
System.out.println(Nivel.ALTO.ordinal());  // 2
```

**Importante:** evite utilizar `ordinal()` para armazenar ou identificar valores de negócio no banco de dados. Se a ordem das constantes mudar, os números também mudarão.

## Método compareTo()

Compara duas constantes considerando sua posição de declaração.

```java
Nivel nivel1 = Nivel.BAIXO;
Nivel nivel2 = Nivel.ALTO;

System.out.println(nivel1.compareTo(nivel2)); // Valor negativo
System.out.println(nivel2.compareTo(nivel1)); // Valor positivo
System.out.println(nivel1.compareTo(nivel1)); // 0
```

---

# 🔁 Percorrendo valores de um Enum

Podemos utilizar o método `values()` junto com o `for` para percorrer todas as constantes.

```java
public enum TipoPagamento {
    PIX,
    CARTAO,
    BOLETO,
    DINHEIRO
}
```

```java
public class Main {
    public static void main(String[] args) {
        for (TipoPagamento tipo : TipoPagamento.values()) {
            System.out.println(tipo.name());
        }
    }
}
```

Resultado:

```text
PIX
CARTAO
BOLETO
DINHEIRO
```

Esse recurso é útil quando precisamos exibir opções disponíveis ou executar alguma operação para cada constante.

---

# 🔀 Enum com Switch

Podemos utilizar enums em estruturas condicionais `switch`.

```java
public enum StatusPedido {
    PENDENTE,
    PROCESSANDO,
    ENVIADO,
    ENTREGUE,
    CANCELADO
}
```

```java
public class Main {
    public static void main(String[] args) {
        StatusPedido status = StatusPedido.ENVIADO;

        switch (status) {
            case PENDENTE:
                System.out.println("Aguardando processamento");
                break;

            case PROCESSANDO:
                System.out.println("Pedido em processamento");
                break;

            case ENVIADO:
                System.out.println("Pedido enviado");
                break;

            case ENTREGUE:
                System.out.println("Pedido entregue");
                break;

            case CANCELADO:
                System.out.println("Pedido cancelado");
                break;
        }
    }
}
```

Também podemos utilizar a sintaxe moderna do `switch` com setas:

```java
switch (status) {
    case PENDENTE -> System.out.println("Aguardando processamento");
    case PROCESSANDO -> System.out.println("Em processamento");
    case ENVIADO -> System.out.println("Enviado");
    case ENTREGUE -> System.out.println("Entregue");
    case CANCELADO -> System.out.println("Cancelado");
}
```

---

# 🧱 Enum com atributos e construtores

Uma característica interessante dos enums é que eles podem possuir atributos e construtores, assim como classes convencionais.

Isso permite associar informações adicionais a cada constante.

Exemplo:

```java
public enum Prioridade {

    BAIXA("Baixa", 1),
    MEDIA("Média", 2),
    ALTA("Alta", 3),
    CRITICA("Crítica", 4);

    private final String descricao;
    private final int nivel;

    Prioridade(String descricao, int nivel) {
        this.descricao = descricao;
        this.nivel = nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNivel() {
        return nivel;
    }
}
```

Observe alguns detalhes:

- Cada constante recebe os valores definidos no construtor.
- O construtor não possui modificador de acesso `public` ou `protected`.
- Os atributos podem ser declarados como `final`.
- Não precisamos criar objetos manualmente.

Utilizando o enum:

```java
public class Main {
    public static void main(String[] args) {
        Prioridade prioridade = Prioridade.ALTA;

        System.out.println(prioridade.getDescricao());
        System.out.println(prioridade.getNivel());
    }
}
```

Resultado:

```text
Alta
3
```

## Entendendo o construtor

Quando declaramos:

```java
BAIXA("Baixa", 1),
MEDIA("Média", 2),
ALTA("Alta", 3);
```

Cada constante representa uma instância do enum, recebendo os argumentos correspondentes no construtor.

Podemos imaginar que cada constante possui seus próprios valores de atributos.

---

# ⚙️ Enum com métodos

Assim como classes comuns, enums podem possuir métodos para implementar comportamentos.

Exemplo:

```java
public enum Operacao {

    SOMA {
        @Override
        public double executar(double a, double b) {
            return a + b;
        }
    },

    SUBTRACAO {
        @Override
        public double executar(double a, double b) {
            return a - b;
        }
    },

    MULTIPLICACAO {
        @Override
        public double executar(double a, double b) {
            return a * b;
        }
    },

    DIVISAO {
        @Override
        public double executar(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Divisão por zero");
            }

            return a / b;
        }
    };

    public abstract double executar(double a, double b);
}
```

Utilizando:

```java
public class Main {
    public static void main(String[] args) {
        double resultado = Operacao.SOMA.executar(10, 5);

        System.out.println(resultado); // 15.0
    }
}
```

Nesse exemplo, cada constante implementa seu próprio comportamento.

Essa abordagem é conhecida como implementação específica por constante e pode ser útil quando cada opção possui uma regra diferente.

---

# 🔌 Enum implementando interfaces

Um enum pode implementar uma ou mais interfaces.

Isso permite utilizar polimorfismo e definir contratos de comportamento.

Exemplo:

```java
public interface Notificacao {
    void enviar(String mensagem);
}
```

```java
public enum CanalNotificacao implements Notificacao {

    EMAIL {
        @Override
        public void enviar(String mensagem) {
            System.out.println("Enviando e-mail: " + mensagem);
        }
    },

    SMS {
        @Override
        public void enviar(String mensagem) {
            System.out.println("Enviando SMS: " + mensagem);
        }
    },

    WHATSAPP {
        @Override
        public void enviar(String mensagem) {
            System.out.println("Enviando WhatsApp: " + mensagem);
        }
    }
}
```

Utilizando:

```java
public class Main {
    public static void main(String[] args) {
        Notificacao notificacao = CanalNotificacao.EMAIL;

        notificacao.enviar("Olá, tudo bem?");
    }
}
```

Resultado:

```text
Enviando e-mail: Olá, tudo bem?
```

Nesse caso, podemos tratar as constantes do enum como implementações da interface `Notificacao`.

---

# 🌱 Enum com Spring Boot e JPA

Em aplicações Spring Boot, enums são bastante utilizados para representar estados e categorias de entidades.

Exemplo de enum:

```java
public enum StatusPedido {
    PENDENTE,
    PROCESSANDO,
    ENVIADO,
    ENTREGUE,
    CANCELADO
}
```

Exemplo de entidade:

```java
import jakarta.persistence.*;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    // Getters e Setters
}
```

## EnumType.STRING

Utilizando:

```java
@Enumerated(EnumType.STRING)
private StatusPedido status;
```

O JPA armazena o nome da constante no banco de dados.

Exemplo:

| id | descricao | status |
|---|---|---|
| 1 | Pedido 1 | PENDENTE |
| 2 | Pedido 2 | ENVIADO |
| 3 | Pedido 3 | ENTREGUE |

## EnumType.ORDINAL

Também existe a possibilidade de utilizar:

```java
@Enumerated(EnumType.ORDINAL)
private StatusPedido status;
```

Nesse caso, o JPA armazena a posição da constante, começando em zero.

Exemplo:

| id | descricao | status |
|---|---|---|
| 1 | Pedido 1 | 0 |
| 2 | Pedido 2 | 2 |
| 3 | Pedido 3 | 3 |

**Recomendação:** prefira `EnumType.STRING` na maioria das aplicações.

Se utilizarmos `ORDINAL` e alterarmos a ordem das constantes, os registros existentes poderão passar a representar estados incorretos.

---

# 💡 Boas práticas

1. **Utilize enums para valores fixos:** estados, categorias, prioridades e tipos.
2. **Evite Strings soltas:** prefira enums para representar opções previamente definidas.
3. **Utilize nomes claros:** constantes normalmente são declaradas em letras maiúsculas, separadas por `_`.
4. **Evite utilizar ordinal() para regras de negócio:** a posição pode mudar.
5. **Prefira EnumType.STRING no JPA:** evita problemas quando a ordem das constantes muda.
6. **Utilize atributos quando necessário:** associe descrições, códigos ou outras informações às constantes.
7. **Não utilize enums para dados dinâmicos:** se os valores precisam ser cadastrados e alterados pelos usuários, considere utilizar uma entidade no banco de dados.
8. **Aproveite o polimorfismo:** enums podem implementar interfaces e definir comportamentos específicos.


# 📌 Resumo

| Conceito | Definição |
|---|---|
| `enum` | Tipo especial utilizado para representar constantes |
| `values()` | Retorna todas as constantes |
| `valueOf()` | Converte uma String para uma constante |
| `name()` | Retorna o nome da constante |
| `ordinal()` | Retorna a posição da constante |
| `compareTo()` | Compara a posição das constantes |
| Construtor | Permite inicializar atributos das constantes |
| Atributos | Permitem armazenar informações adicionais |
| Métodos | Permitem definir comportamentos |
| Interfaces | Permitem implementar contratos de comportamento |
| `EnumType.STRING` | Persiste o nome da constante no banco |
| `EnumType.ORDINAL` | Persiste a posição da constante no banco |

## 🎯 Regra para memorizar

- `enum` → representa um conjunto fixo de constantes.
- `values()` → retorna todas as constantes.
- `valueOf()` → converte String para enum.
- `ordinal()` → retorna a posição da constante.
- `name()` → retorna o nome da constante.
- Enum pode possuir atributos, construtores e métodos.
- Enum pode implementar interfaces, mas não pode estender outras classes.

**Dica para provas:** lembre-se de que enums são tipos especiais de classe, suas constantes são instâncias do próprio enum e não podem ser instanciadas com `new`.