package org.example.contas;

public class ContaEspecial extends Conta {
	private double bonus;
	
	public ContaEspecial(String numero) {
		super(numero);
		bonus=0;
	}
	
	public void renderBonus() {
		super.creditar(bonus);
		this.bonus=0;
	}
	
	public void renderBonus(double porcemtagem) {
		porcemtagem=porcemtagem * 0.01;
		super.creditar(porcemtagem * bonus);
		this.bonus=0;
	}
	
	public void creditar(double valor) {
		this.bonus=this.bonus +(valor * 0.01);
		super.creditar(valor);
	}
	
}
