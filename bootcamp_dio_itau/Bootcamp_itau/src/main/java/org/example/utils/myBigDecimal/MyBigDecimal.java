package org.example.utils.myBigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyBigDecimal {
    public static void main(String[] args) {
        var value1= new BigDecimal("0.1");
        var value2= new BigDecimal("0.2");
        var sum= value1.add(value2);
        System.out.println("Sum: " + sum);
        System.out.println("Multiplication: " + value1.multiply(value2));
        System.out.println("Subtraction: " + value1.subtract(value2));
        System.out.println("Division: " + value1.divide(value2, 2, RoundingMode.HALF_UP));
    }
}
