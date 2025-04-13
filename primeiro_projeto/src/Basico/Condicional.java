package Basico;

public class Condicional {
    public static  void main(String[] args){
        boolean bool=true;
        String nome="Ernestogo";

        if(bool){
            System.out.println("Verdadeiro");
        }else{
            System.out.println("Falso");
        }

        if(nome.equals("Ernestogo") || bool){
            System.out.println("Igual");
        }else{
            System.out.println("Diferente");
        }

    }
}
