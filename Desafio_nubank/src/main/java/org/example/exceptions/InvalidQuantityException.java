package org.example.exceptions;

public class InvalidQuantityException extends CapitalGainsException {
    public InvalidQuantityException(String message) {
        super(message);
    }
    public InvalidQuantityException(){
        super("A quantidade de ações não pode ser negativa ou zero");
    }
}
