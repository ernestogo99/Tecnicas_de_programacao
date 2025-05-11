package org.example;



import org.example.contas.ContaImposto;
import org.example.contas.Poupanca;
import org.example.banco.BancoVector;

public class Main {

    public static void main(String[] args) {
        Poupanca poupanca=new Poupanca("209");
        BancoVector banco=new BancoVector(0.5);
        ContaImposto ci=new ContaImposto("123");
        poupanca.creditar(200);
        banco.cadastrar(poupanca);
        ci.creditar(200);
        banco.cadastrar(ci);

        System.out.println(ci.saldo());
        banco.debitar("123", 100);
        System.out.println(ci.saldo());
        System.out.println(poupanca.saldo());
        banco.renderJuros("209");
        System.out.println(poupanca.saldo());
        poupanca.renderJuros(0.5);
        System.out.println(poupanca.saldo());
        System.out.println(banco.saldototal());

    }

}
