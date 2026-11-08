package org.example.interfaces;


import java.util.List;
import java.util.function.Function;


public class FunctionalInterfaces {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        names.forEach((String name) -> {;
            System.out.println("Hello, " + name + "!");
        });
        printStringValue((String name) -> "Hello, " + name + "!", "Alice");

    }


    private static void printStringValue(Function<String, String> function, String value) {
        System.out.println(function.apply(value));
    }
}
