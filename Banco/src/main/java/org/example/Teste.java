package org.example;

import org.example.contas.ContaAbstrata;
import org.example.contas.ContaEspecial;
import org.example.contas.Poupanca;
import org.example.exceptions.CIException;
import org.example.repositorio.ContaFile;

import java.util.Vector;

public class Teste {
    public static void main(String[] args) {
        // Caminho do arquivo JSON onde as contas serão salvas
        String caminho = "src/main/java/org/example/data/db.json";


        // Instância do DAO que salva e lê contas do arquivo
        ContaFile repositorio = new ContaFile(caminho);

        // Criando contas de teste
        ContaAbstrata conta1 = new ContaEspecial("123");
        ContaAbstrata conta2 = new Poupanca("456");
        ContaAbstrata conta3 = new ContaEspecial("789");

        // Inserindo contas
        repositorio.inserir(conta1);
        repositorio.inserir(conta2);
        repositorio.inserir(conta3);

        conta1.creditar(1000);
        conta2.creditar(500);
        conta3.creditar(1500);

        System.out.println("Contas cadastradas com sucesso!");

        // Listando contas
        System.out.println("\nListando contas:");
        Vector<ContaAbstrata> contas = repositorio.listar();
        for (ContaAbstrata c : contas) {
            System.out.println(c);
        }

        // Procurando uma conta
        try {
            ContaAbstrata encontrada = repositorio.procurar("456");
            System.out.println("\nConta encontrada:");
            System.out.println(encontrada);
        } catch (CIException e) {
            System.err.println(e.getMessage());
        }

        // Removendo uma conta
        try {
            repositorio.remover("123");
            System.out.println("\nConta 123 removida com sucesso!");
        } catch (CIException e) {
            System.err.println(e.getMessage());
        }

        // Listando novamente após remoção
        System.out.println("\nContas restantes:");
        for (ContaAbstrata c : repositorio.listar()) {
            System.out.println(c);
        }

        // Mostrando o número total de contas
        System.out.println("\nNúmero de contas: " + repositorio.tamanho());
    }
}

