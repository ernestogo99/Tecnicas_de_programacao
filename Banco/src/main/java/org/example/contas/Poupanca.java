package org.example.contas;

public class Poupanca extends Conta {
	
	public Poupanca(String numero) {
		super(numero);
	}

	@Override
	public void renderJuros(Double taxa) {
		this.creditar(this.saldo() * taxa);
	}
}
