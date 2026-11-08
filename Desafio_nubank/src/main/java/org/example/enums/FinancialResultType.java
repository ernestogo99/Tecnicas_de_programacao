package org.example.enums;

public enum FinancialResultType {

    PROFIT("Lucro"),
    LOSS("Prejuízo"),
    NONE("Sem resultado");

    private final String description;

    FinancialResultType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
