package org.example.controlsStructure;

import java.util.Scanner;

public class ConditionalLoop {
    private final Scanner scanner = new Scanner(System.in);

    public ConditionalLoop(){

    }

    public void conditionalLoopExample() {
        System.out.println("Enter a number (0 to exit): ");
        int number = this.scanner.nextInt();

        while (number != 0) {
            if (number > 0) {
                System.out.println("You entered a positive number: " + number);
            } else {
                System.out.println("You entered a negative number: " + number);
            }
            System.out.println("Enter another number (0 to exit): ");
            number = this.scanner.nextInt();
        }

        System.out.println("Exiting the loop. Goodbye!");
    }

    public void driveExample(){
        System.out.println("Informe seu nome: ");
        var name = this.scanner.nextLine();
        System.out.println("Informe sua idade: ");
        var age = this.scanner.nextInt();
        System.out.println("Você é emancipado? (s/n)");
        var emancipated = this.scanner.next().equalsIgnoreCase("s");

        var  canDrive = age >= 18 || (emancipated && age >= 16);
        var message="";
        if(canDrive){
            message=String.format("Olá %s, você tem %d anos e pode dirigir.%n", name, age);
        }else {
            message=String.format("Olá %s, você tem %d anos e não pode dirigir.%n", name, age);
        }

        System.out.println(message);
        System.out.println("Fim da execução");
    }

    public void elvisOperator(){
        System.out.println("Informe seu nome: ");
        var name = this.scanner.nextLine();
        System.out.println("Informe sua idade: ");
        var age = this.scanner.nextInt();
        System.out.println("Você é emancipado? (s/n)");
        var emancipated = this.scanner.next().equalsIgnoreCase("s");

        var canDrive = age >= 18 || (emancipated && age >= 16);
        var message = canDrive ?
                 String.format("Olá %s, você tem %d anos e pode dirigir.%n", name, age)
                : String.format("Olá %s, você tem %d anos e não pode dirigir.%n", name, age);

        System.out.println(message);
        System.out.println("Fim da execução");
    }


    public void caseOperator(){
        System.out.println("Informe um número de 1 a 7: ");
        var option = this.scanner.nextInt();
        switch (option) {
            case 1 -> System.out.println("Domingo");
            case 2 -> System.out.println("Segunda-feira");
            case 3 -> System.out.println("Terça-feira");
            case 4 -> System.out.println("Quarta-feira");
            case 5 -> System.out.println("Quinta-feira");
            case 6 -> System.out.println("Sexta-feira");
            case 7 -> System.out.println("Sábado");
            default -> System.out.println("Opção inválida. Informe um número de 1 a 7.");
        }
    }
}
