package org.example.repositorio;

import java.util.Vector;

import org.example.contas.ContaAbstrata;
import org.example.exceptions.CIException;
import org.example.exceptions.SIException;

public class RepositorioContaVector implements IRepositorioContaVector {
	private Vector<ContaAbstrata> contas=new Vector<ContaAbstrata>();

	
	public void inserir(ContaAbstrata conta) {
		if(conta==null) {
		    throw new IllegalArgumentException("Dados da conta não podem ser nulos.");
		}
		contas.add(conta);
	
	}

	
	public void remover(String numero) throws CIException {
		ContaAbstrata conta=this.procurar(numero);
		if(conta!=null) {
			contas.remove(conta);
		}
		
		
	}


	public ContaAbstrata procurar(String numero) throws CIException {
		for(ContaAbstrata conta:contas) {
			if(conta.numero().equals(numero)) {
				return conta;
			}
		}
		throw new CIException(numero);
		
	
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

	public void creditar(String numero,double valor) throws CIException {
		ContaAbstrata conta=this.procurar(numero);
		if(conta!=null) {
			conta.creditar(valor);
		}
	}

	public void debitar(String numero,double valor) throws CIException,SIException {
		ContaAbstrata conta=this.procurar(numero);
		double saldoAtual=conta.saldo();
		if(conta!=null && conta.saldo()>=valor) {
			conta.debitar(valor);
		}else{
			throw  new SIException(saldoAtual,numero);
		}

	}

	public double saldo(String numero) throws CIException {
		ContaAbstrata conta=this.procurar(numero);
		if(conta !=null) {
			return conta.saldo();
		}else {
			return 0.0;
		}
	}

	public void transferir(String origem,String destino,double valor) throws CIException,SIException {
		ContaAbstrata origim=this.procurar(origem);
		ContaAbstrata destiny=this.procurar(destino);

		if(origim!=null && destiny!=null) {
			origim.debitar(valor);
			destiny.creditar(valor);
			System.out.println("Foi transferido o valor de: "+valor+ " Da conta: "+origim.numero() + " para a conta: "+ destiny.numero());
		}


	}

	

}
