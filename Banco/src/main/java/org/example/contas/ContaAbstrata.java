package org.example.contas;



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

	public void renderJuros(Double taxa) {

	}
	
	public String numero() {
		return this.numero;
	}
	
	public double saldo() {
		return this.saldo;
	}
}
