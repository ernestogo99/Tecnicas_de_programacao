package org.example.utils.enums;

public class EnumTest {
    public static void main(String[] args) {
        // Test MyEnum
        for (MyEnum myEnum : MyEnum.values()) {
            System.out.println("Code: " + myEnum.getCode() + ", Name: " + myEnum.getName());
        }

        // Test OperationEnum
        for (OperationEnum operationEnum : OperationEnum.values()) {
            System.out.println("Symbol: " + operationEnum.getSymbol());
        }

        var option=-1;
        while(option!=0){
            System.out.println("Escolha uma operação pelo número");
            System.out.println("1 - Adição");
            System.out.println("2 - Subtração");
            System.out.println("3 - Multiplicação");
            System.out.println("4 - Divisão");
            System.out.println("0 - Sair");
            var scanner = new java.util.Scanner(System.in);
            option = scanner.nextInt();
            if(option==0){
                System.out.println("Saindo...");
                break;
            }
            if(option<1 || option>4){
                System.out.println("Opção inválida");
                continue;
            }
            System.out.println("Digite o primeiro número:");
            var num1 = scanner.nextInt();
            System.out.println("Digite o segundo número:");
            var num2 = scanner.nextInt();
            var operation = OperationEnum.values()[option - 1];
            var result = operation.getCalculationFunction().apply(num1, num2);
            System.out.println("Resultado: " + num1 + " " + operation.getSymbol() + " " + num2 + " = " + result);
            System.out.println("================================");
        }

    }
}
