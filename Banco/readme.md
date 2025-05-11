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
atributos que são propriedades de um objeto, e os métodos são
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

# Casting

É o processo de converter um tipo de dado em outro

exemplo:

```java
public void renderJuros(String numero) {
		ContaAbstrata conta=this.procurar(numero);

		if(conta instanceof Poupanca) {
				((Poupanca) conta).renderJuros(this.taxajuros);
		}else {
			System.err.println("A conta não é do tipo poupanca");
		}
	}
```

o comando instanceof indica se Poupanca é uma instância de conta, ou seja,
faz uma checagem de tipos.

aqui estamos convertendo o tipo ContaAbstrata para poupanca, desta forma:
((Poupanca) conta)

# Interface

Em Java, uma interface é um tipo especial que define um contrato: ela declara métodos que uma classe deve implementar, mas não contém a implementação desses métodos

- Interface não é reutilizavel como a classe abstrata
- Permite herança múltipla
- Uma classe pode ter múltiplos subtipos implementados por mais de uma interface
- ela padroniza comportamento em classes diferentes
- Uma classe implementa uma interface, assim para utiliza-la usamos o comando implements

exemplo:

```java
package org.example.repositorio;

import java.util.Vector;

import org.example.contas.ContaAbstrata;

public interface IRepositorioContaVector {
	public void inserir(ContaAbstrata conta);
	public void remover(String numero);
	public ContaAbstrata procurar(String numero);
	public Vector<ContaAbstrata> listar();
	public int tamanho();
}

```

implementando a interface:

```java
package org.example.repositorio;

import java.util.Vector;

import org.example.contas.ContaAbstrata;

public class RepositorioContaVector implements IRepositorioContaVector {
	private Vector<ContaAbstrata> contas=new Vector<ContaAbstrata>();


	public void inserir(ContaAbstrata conta) {
		if(conta==null) {
		    throw new IllegalArgumentException("Dados da conta não podem ser nulos.");
		}
		contas.add(conta);

	}


	public void remover(String numero) {
		ContaAbstrata conta=this.procurar(numero);
		if(conta!=null) {
			contas.remove(conta);
		}


	}


	public ContaAbstrata procurar(String numero) {
		for(ContaAbstrata conta:contas) {
			if(conta.numero().equals(numero)) {
				return conta;
			}
		}
		throw new RuntimeException("Conta com número: " + numero + " não encontrada.");


	}


	public Vector<ContaAbstrata> listar(){
		if(this.contas.isEmpty()) {
			throw new  RuntimeException("Nenhuma conta cadastrada.");
		}
		return this.contas;
	}

	public double saldototal() {
		double saldoTotal=0.0;
		if(this.contas.isEmpty()) {
			throw new  RuntimeException("Nenhuma conta cadastrada.");
		}
		for(ContaAbstrata conta:contas){
			saldoTotal+=conta.saldo();
		}
		return saldoTotal;
	}

	public int tamanho() {
		if(this.contas.isEmpty()) {
			throw new  RuntimeException("Nenhuma conta cadastrada.");
		}
		return this.contas.size();
	}


}

```

caso algum método do contrato não esteja implementado na classe, a IDE
irá acusar um erro.
