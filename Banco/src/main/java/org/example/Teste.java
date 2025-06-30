package org.example;

import org.example.contas.ContaAbstrata;
import org.example.contas.ContaEspecial;
import org.example.contas.Poupanca;
import org.example.exceptions.CIException;
import org.example.repositorio.ContaFile;

import java.util.Vector;

public class Teste {
    public static void main(String[] args) {

        String caminho = "src/main/java/org/example/data/db.json";



        ContaFile repositorio = new ContaFile(caminho);


        ContaAbstrata conta1 = new ContaEspecial("123");
        ContaAbstrata conta2 = new Poupanca("456");
        ContaAbstrata conta3 = new ContaEspecial("789");


        repositorio.inserir(conta1);
        repositorio.inserir(conta2);
        repositorio.inserir(conta3);

        conta1.creditar(1000);
        conta2.creditar(500);
        conta3.creditar(1500);

        System.out.println("Contas cadastradas com sucesso!");


        System.out.println("\nListando contas:");
        Vector<ContaAbstrata> contas = repositorio.listar();
        for (ContaAbstrata c : contas) {
            System.out.println(c);
        }


        try {
            ContaAbstrata encontrada = repositorio.procurar("456");
            System.out.println("\nConta encontrada:");
            System.out.println(encontrada);
        } catch (CIException e) {
            System.err.println(e.getMessage());
        }

        try {
            repositorio.remover("123");
            System.out.println("\nConta 123 removida com sucesso!");
        } catch (CIException e) {
            System.err.println(e.getMessage());
        }


        System.out.println("\nContas restantes:");
        for (ContaAbstrata c : repositorio.listar()) {
            System.out.println(c);
        }


        System.out.println("\nNúmero de contas: " + repositorio.tamanho());
    }
}

