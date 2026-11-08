package org.example.exceptions;

public class InsufficientSharesException extends CapitalGainsException {
    public InsufficientSharesException(String message) {
        super(message);
    }

    public InsufficientSharesException(){
        super("Não há ações suficientes para realizar a operação");
    }
}
