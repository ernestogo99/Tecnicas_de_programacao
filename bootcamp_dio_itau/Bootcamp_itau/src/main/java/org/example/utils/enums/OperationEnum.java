package org.example.utils.enums;

import java.util.function.BiFunction;

public enum OperationEnum {
    ADDITION(1,"+", Integer::sum),
    SUBTRACTION(2,"-", (a, b) -> a - b),
    MULTIPLICATION(3,"*", (a, b) -> a * b),
    DIVISION(4,"/", (a, b) -> a / b);

    private final int code;
    private final String symbol;
    private final BiFunction<Integer, Integer, Integer> calculationFunction;


    OperationEnum(int code, String symbol, BiFunction<Integer, Integer, Integer> calculationFunction) {
        this.code = code;
        this.symbol = symbol;
        this.calculationFunction = calculationFunction;
    }

    public int getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }



    public BiFunction<Integer, Integer, Integer> getCalculationFunction() {
        return calculationFunction;
    }
}
