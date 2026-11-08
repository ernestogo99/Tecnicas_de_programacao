package org.example.basics;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MyBasicScanner {

    private final static String WELCOME_MESSAGE = "Please enter your name: ";
    private final static Scanner SCANNER = new Scanner(System.in);

    public MyBasicScanner() {

    }

    public void basicPrint() {
        System.out.println(WELCOME_MESSAGE);
        String name = SCANNER.nextLine();
        System.out.println("Please enter your age: ");
        int age = SCANNER.nextInt();
        System.out.printf("Hello %s, you are %d years old.%n", name, age);
    }

    public void basicOperators() {
        System.out.println("How old are you? ");
        int age = SCANNER.nextInt();
        System.out.println("You are " + age + " years old.");
        System.out.println("Você é emancipado? (true/false) ");
        boolean emancipated = SCANNER.nextBoolean();
        boolean canDrive = age >= 18 || emancipated;
        System.out.println("You can drive: " + canDrive);
    }

    public void mathOperators(){
        var value=(5+9) * (10-5)/2;
        System.out.println("value: " + value);
        System.out.println("Put a number: ");
        var number = SCANNER.nextInt();
        System.out.println("A raiz quadrada do número é: " + Math.sqrt(number));
        System.out.printf("o NÚMERO %d elevado ao quadrado é : %.2f%n", number, Math.pow(number, 2));
    }

    public void calculateAge(String name, int birthYear) {
        int currentYear = LocalDateTime.now().getYear();
        int age = currentYear - birthYear;
        System.out.printf("Olá %s, você tem %d anos.%n", name, age);
    }

    public void squareArea(float side){
        float area = side * side;
        System.out.printf("A área do quadrado é: %.2f%n", area);
    }

    public void rectangleArea(float length, float width) {
        float area = length * width;
        System.out.printf("A área do retângulo é: %.2f%n", area);
    }

    public void diferenceBetweenAges(String name1, int age1, String name2, int age2) {
        if(age1 < 0 || age2 < 0) {
            System.out.println("A idade não pode ser menor que 0");
            return;
        }
        int diference = Math.abs(age1 - age2);
        System.out.printf("A diferença de idade entre %s e %s é: %d anos.%n", name1, name2, diference);
    }
}
