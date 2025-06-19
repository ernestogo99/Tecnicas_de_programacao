package org.example.exceptions;

public class SIException extends Exception {
    private double  saldo;
    private String numero;

    public SIException(double saldo,String numero)
    {
        super("Saldo insuficiente " + " saldo atual:" + saldo + "conta: "+ numero);
        this.saldo=saldo;
        this.numero=numero;
    }

    public double saldoConta(){
        return this.saldo;
    }

    public String numeroConta(){
        return this.numero;
    }

}
