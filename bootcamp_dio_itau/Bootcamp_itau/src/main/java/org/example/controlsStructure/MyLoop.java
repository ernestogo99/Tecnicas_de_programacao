package org.example.controlsStructure;

import java.util.List;
import java.util.Scanner;

public class MyLoop {
    private final Scanner scanner=new Scanner(System.in);
    private final List<Integer> numbers=List.of(1,2,3,4,5,6,7,8,9,10);

    public MyLoop(){

    }


    public void forLoopExample(){
        System.out.println("Informe um número: ");
        var number = this.scanner.nextInt();
        for(int i=1; i<=number; i++){
            System.out.printf("%d x %d = %d%n", number, i, number*i);
        }

        for(var num : this.numbers){
            System.out.println("For each na minha lista");
            System.out.println(num);
        }

    }


    public void imc(Float weight, Float height){
        var imc = weight / (height * height);
        System.out.printf("Seu IMC é: %.2f%n", imc);
        if (imc <= 18.5f) {
            System.out.println("Você está abaixo do peso.");
        } else if (imc <= 24.9f) {
            System.out.println("Você está com o peso normal.");
        } else if (imc <= 29.9f) {
            System.out.println("Você está com sobrepeso.");
        } else if (imc <= 34.9f) {
            System.out.println("Você está com obesidade grau 1.");
        } else if (imc <= 39.9f) {
            System.out.println("Você está com obesidade grau 2.");
        } else {
            System.out.println("Você está com obesidade grau 3.");
        }
    }


    public void   intervalDesc(Integer number1,Integer number2, boolean even){
        if(number1>number2){
            System.out.println("O primeiro número deve ser menor que o segundo.");
            return;
        }

        for(int i=number2; i>=number1; i--){
            if(even && this.isEven(i)){
                System.out.println(i);
            } else if (!even && !this.isEven(i)) {
                System.out.println(i);
            }
        }
    }

    private boolean isEven(Integer number){
        return number % 2 == 0;
    }

    public boolean whileLoopExample(){
        System.out.println("Informe o número inicial: ");
        var number = this.scanner.nextInt();

        while (true) {
            System.out.println("Informe um número: ");
            var myNumber = this.scanner.nextInt();

            if (myNumber < number) {
                System.out.println("Número menor que o inicial, será ignorado.");
                continue;
            }

            if (myNumber % number != 0) {
                System.out.println("O número informado não é divisível pelo número inicial. Execução encerrada.");
                return false;
            }

            System.out.printf("%d é divisível por %d.%n", myNumber, number);
        }
    }


}
