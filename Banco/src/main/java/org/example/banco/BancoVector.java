package org.example.banco;
import java.util.Vector;
import org.example.contas.ContaAbstrata;
import org.example.contas.ContaEspecial;
import org.example.contas.Poupanca;
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
		}catch (RuntimeException e ){
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
		ContaAbstrata conta=this.procurar(numero);
		if(conta!=null) {
			conta.creditar(valor);
		}else {
			System.out.println("Conta não encontrada");
		}
	}
	
	public void debitar(String numero,double valor) {
		ContaAbstrata conta=this.procurar(numero);
		if(conta!=null) {
			conta.debitar(valor);
		}else {
			System.out.println("Conta não encontrada");
		}
	}
	
	public double saldo(String numero) {
		ContaAbstrata conta=this.procurar(numero);
		if(conta !=null) {
			return conta.saldo();
		}else {
			return 0.0;
		}	
	}
	
	public void transferir(String origem,String destino,double valor) {
		ContaAbstrata origim=this.procurar(origem);
		ContaAbstrata destiny=this.procurar(destino);
		if(origim!=null && destiny!=null) {
			origim.debitar(valor);
			destiny.creditar(valor);
			System.out.println("Foi transferido o valor de: "+valor+ " Da conta: "+origim.numero() + " para a conta: "+ destiny.numero());
		}else {
			System.out.println("Alguma conta não existe");
		}
		
		
	}
	
	
	
	public void renderJuros(String numero) {
		ContaAbstrata conta=this.procurar(numero);
		
		if(conta instanceof Poupanca) {
				((Poupanca) conta).renderJuros(this.taxajuros);
		}else {
			System.err.println("A conta não é do tipo poupanca");
		}
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
