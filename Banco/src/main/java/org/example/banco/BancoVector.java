package org.example.banco;
import java.util.Vector;
import org.example.contas.ContaAbstrata;
import org.example.contas.ContaEspecial;
import org.example.contas.Poupanca;
import org.example.exceptions.CIException;
import org.example.exceptions.SIException;
import org.example.repositorio.RepositorioContaVector;

public class BancoVector implements IBanco {
	private RepositorioContaVector contas= new RepositorioContaVector();
	private Double taxajuros;
	
	
	public BancoVector(Double taxajuros) {
		this.taxajuros=taxajuros;
	}
	
	public void cadastrar(ContaAbstrata conta) {
		try{
			contas.inserir(conta);
		}catch (IllegalArgumentException e){
			System.err.println(e.getMessage());
		}
	
	}
	
	
	
	
	public ContaAbstrata procurar(String numero) {
		try{
			return contas.procurar(numero);
		}catch (CIException e ){
			System.err.println(e.getMessage());
			return null;
		}
		

	}
	
	public Vector<ContaAbstrata> listar(){
		try{
			return  contas.listar();
		}catch (RuntimeException e){
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public void creditar(String numero,double valor) {
		try{
			contas.creditar(numero,valor);
		} catch (CIException e) {
			System.err.println(e.getMessage());
		}
	}

	public void debitar(String numero,double valor) {
		try{
			contas.debitar(numero,valor);
		} catch (CIException e) {
			System.err.println(e.getMessage());
		} catch (SIException e) {
			System.err.println(e.getMessage());
		}
	}

	public double saldo(String numero) {
		try{
			return contas.saldo(numero);
		} catch (CIException e) {
			System.err.println(e.getMessage());
			return 0.0;
		}
	}

	public void transferir(String origem,String destino,double valor) {
		try{
			contas.transferir(origem, destino, valor);
		} catch (CIException e) {
			System.err.println(e.getMessage());
		} catch (SIException e) {
			System.err.println(e.getMessage());
		}

		
	}

	public void remover(String numero) {
		try{
			contas.remover(numero);
		}catch (CIException e){
			System.err.println(e.getMessage());
		}
	}


	public void renderJuros(String numero) {
		ContaAbstrata conta=this.procurar(numero);
		conta.renderJuros(this.taxajuros);

	}
	
	public void renderBonus(String numero) {
		ContaAbstrata conta=this.procurar(numero);
		
		if(conta instanceof ContaEspecial) {
				((ContaEspecial) conta).renderBonus();;
		}else {
			System.err.println("A conta não é do tipo Contaespecial");
		}
	}

	
	public double saldototal() {
		try{
			return  contas.saldototal();
		}catch (RuntimeException e ){
			System.err.println(e.getMessage());
			return  0.0;
		}
	}

	
	public int numeroContas() {
		try {
			return this.contas.tamanho();
		}catch (RuntimeException e ){
            System.err.println(e.getMessage());
            return 0;
		} 
	}
	
	
	
	
	
}
