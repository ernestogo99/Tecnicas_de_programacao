package org.example.exceptions;

public class CIException extends  Exception {
    private final String numero;

    public CIException(String numero){
        super("A conta de número: "+ numero + "não existe");
        this.numero=numero;
    }

    public String numeroConta(){
        return numero;
    }

}
