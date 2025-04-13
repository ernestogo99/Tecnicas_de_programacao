package oop;

public class Main {
    public static void main(String[] args) {
        Endereco end1=new Endereco("Dr correia lima",
                "sapiranga","fortaleza","5332",10);
        Pessoa pessoa1=new Pessoa("Ernestogo","0834209");
        System.out.println(pessoa1.getCpf());
        pessoa1.setEnd(end1);
        System.out.println(pessoa1.getEnd());


    }
}
