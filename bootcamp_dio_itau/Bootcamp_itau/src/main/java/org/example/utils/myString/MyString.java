package org.example.utils.myString;

public class MyString {
    public static void main(String[] args) {
        String str = "Hello, World!";
        System.out.println("Original String: " + str);
        System.out.println("Length: " + str.length());
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Lowercase: " + str.toLowerCase());
        System.out.println("Substring (0, 5): " + str.substring(0, 5));
        System.out.println("Contains 'World': " + str.contains("World"));
        System.out.println("Starts with 'Hello': " + str.startsWith("Hello"));
        System.out.println("Ends with '!': " + str.endsWith("!"));
        System.out.println("Index of 'o': " + str.indexOf('o'));
        System.out.println("Replace 'World' with 'Java': " + str.replace("World", "Java"));
        System.out.println("Trimmed String: " + str.trim());

        var value="java;java;java;java";
        var parts = value.split(";",2);
        for (var part : parts) {
            System.out.println(part);
        }


    }
}
