package Basico;

import java.util.ArrayList;

public class List { public static void main(String[] args) {
    ArrayList<String> nomes=new ArrayList<>();
    nomes.add("ernestogo");
    nomes.add("João");
    nomes.add("Ronaldo");
    System.out.println(nomes);
    System.out.println(nomes.get(0));
    nomes.remove(0);
    System.out.println(nomes);

    for(int i=0;i<nomes.size();i++){
        System.out.println(nomes.get(i));
    }

    for(String nome:nomes){
        System.out.println(nome);
    }
    int contador=0;
    while(contador<5){
        System.out.println("oii");
        contador++;
    }

}
}
