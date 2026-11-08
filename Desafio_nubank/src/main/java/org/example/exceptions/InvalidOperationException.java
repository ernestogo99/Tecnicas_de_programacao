package org.example.exceptions;

public class InvalidOperationException extends CapitalGainsException {

    public InvalidOperationException(String message) {
        super(message);
    }
    public InvalidOperationException(){
        super("Operação inválida");
    }
}
