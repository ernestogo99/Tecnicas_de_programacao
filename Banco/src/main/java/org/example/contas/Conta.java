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
