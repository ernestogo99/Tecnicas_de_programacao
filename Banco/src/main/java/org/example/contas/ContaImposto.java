package org.example.contas;

public class ContaImposto extends ContaAbstrata {

	public ContaImposto(String numero) {
		super(numero);
	
	}

	@Override
	public void debitar(double valor) {
		this.saldo=saldo-(valor + (valor * 0.001));
		
	}

}
