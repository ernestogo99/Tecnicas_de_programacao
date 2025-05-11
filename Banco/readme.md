# Banco digital

Este é um dos projetos feito em java da cadeira de técnicas de programação,que tem
como objetivo utilizar e entender conceitos de programação orientada a objetos em java.

## Destaques do projeto

- Gerenciamento de contas: cadastrar, buscar, listar, etc.
- Vários tipos de conta: ContaEspecial, ContaImposto, Poupança.
- A ContaEspecial pode render um bônus.
- A ContaImposto paga imposto ao debitar.
- A ContaPoupança pode render juros.
- Reutilização de código com herança.
- Uso de conceitos de orientação a objetos (POO).

## Como rodar o projeto

1 Compile o projeto:

```bash
javac Main.java
```

2. Execute:

```bash
java Main
```

---

## 🛠️ Tecnologias

- Java 17+
- Programação orientada a objetos (POO)

---

## Conceitos importantes

- Classes
- Pacotes
- Modificadores de acesso
- Herança
- Polimorfismo
- Classes Abstratas
- Atributos
- Métodos
- Casting
- Interface

# Classes

Uma classe é um agrupamento de coisas similares, ela possui
objetos que são algo com base na classe, e os métodos são
as ações que a classe pode sofrer ou realizar.

exemplo:

```java
public class Pessoa {
    String rua;
    String bairro;
    String cidade;
    String cep;
    int numero;

    public Pessoa(int numero){
        this.numero=numero;
    }

    public void dizerOi(){
        System.out.println("oi");
    }


}
```

na classe pessoa temos os atributos rua,bairro,cidade,cep,numero
e temos o método dizerOi.

O método public Pessoa(int numero) é o construtor, que é utilizado para inicializar a classe.

podemos instanciar a classe pessoa dessa forma:

Pessoa pessoa1= new Pessoa(2);

perceba que precisamos passar o parametro no construtor, caso haja um.

# Pacotes

- Agrupam definições de classes relacionadas
- Estrutura sistema de grande porte, facilita a localização de classes
- Oferece um maior nível de abstração: há mais classes do que pacotes
- nome do pacote deve ser minúsculo

```java
package org.example.contas;

public class Conta extends ContaAbstrata {


	public Conta(String numero) {
		super(numero);
	}

	@Override
	public void debitar(double valor) {
		this.saldo-=valor;

	}

}
```

neste exemplo temos o pacote package org.example.contas;
dentro dele podemos utilizar todas as contas, sem a necessidade de usar import.

# Modificadores de acesso

- private: acesso somente dentro da própria classe
- default: acesso somente dentro do mesmo pacote
- public: acesso liberado de qualquer lugar
- protected: acesso dentro do mesmo pacote e por subclasses

uso: private: para atributos -> protege dados.
protected: quando quiser permitir acesso por herança.

# Herança

É o mecanismo que permite que uma classe herde atributos e métodos de outra classe
usamos o extends para aplicar a herança.

```java
public class Conta extends ContaAbstrata {


	public Conta(String numero) {
		super(numero);
	}

	@Override
	public void debitar(double valor) {
		this.saldo-=valor;

	}

}
```

o super indica que eu estou chamando o construtor da classe herdada,
assim permitindo o acesso aos métodos e atribudos da classe.
assim , a classe conta poderá usar os métodos da classe ContaAbstrata

# Polimorfismo

É a capacidade de um mesmo método funcionar de maneiras diferentes dependendo
do objeto.

exemplo:

```java
public class ContaImposto extends ContaAbstrata {

	public ContaImposto(String numero) {
		super(numero);

	}

	@Override
	public void debitar(double valor) {
		this.saldo=saldo-(valor + (valor * 0.001));

	}

}
```

```java
public class Conta extends ContaAbstrata {


	public Conta(String numero) {
		super(numero);
	}

	@Override
	public void debitar(double valor) {
		this.saldo-=valor;

	}

}
```

Perceba que o método debitar da classe Conta e da contaImposto se comportam
de maneira diferente, apesar de ser o mesmo método, que foi herdado e sobrescrito
da classe Abstrata.

A anotação @Override em Java serve para indicar que um método está sobrescrevendo (override) um método da superclasse ou de uma interface.

# Classe abstrata

É uma classe que não pode ser instanciada diretamente.
Ela serve como molde para outras classes.
Demos usa-lá quando quisermos:

- Forçar subclasses a implementar certos métodos
- Reaproveitar código base em várias classes parecidas
- Quando a classe deve ser só herdada

```java
public abstract class ContaAbstrata {
	protected String numero;
	protected double saldo;

	public ContaAbstrata(String numero) {
		this.numero=numero;
		this.saldo=0;
	}


	public void creditar(double valor) {
		this.saldo+=valor;
	}

	public abstract void debitar(double valor);

	public String numero() {
		return this.numero;
	}

	public double saldo() {
		return this.saldo;
	}
}
```

perceba que nessa classe há um método abstrato, ou seja, ele deve ser
implementado nas classes que serão herdadas, mas não necessariamente
deve ser implementado na classe abstrata.

como essa é uma classe abstrata, se tentarmos usar o comando:

ContaAbstrata conta=new ContaAbstata();

irá ocorrer um erro, pois a classe abstrata não pode ser instanciada.
