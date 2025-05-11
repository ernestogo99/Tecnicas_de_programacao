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
