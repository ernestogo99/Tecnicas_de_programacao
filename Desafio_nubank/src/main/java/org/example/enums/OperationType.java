package org.example.enums;

public enum OperationType {

    BUY(1, "Compra"),
    SELL(2, "Venda");

    private final int code;
    private final String description;

    OperationType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}