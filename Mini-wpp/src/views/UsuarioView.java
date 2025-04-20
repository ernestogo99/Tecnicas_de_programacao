package views;

import controllers.UsuarioController;
import models.Usuario;

import java.util.List;
import java.util.Scanner;


public class UsuarioView {
    private final UsuarioController controller;
    private final Scanner scanner = new Scanner(System.in);

    public UsuarioView(UsuarioController controller) {
        this.controller = controller;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- MENU USUÁRIO ---");
            System.out.println("1. Criar usuário");
            System.out.println("2. Editar usuário");
            System.out.println("3. Buscar usuário por número");
            System.out.println("4. Apagar usuário");
            System.out.println("5. Listar usuários");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> criarUsuario();
                case 2 -> editarUsuario();
                case 3 -> buscarUsuario();
                case 4 -> apagarUsuario();
                case 5 -> listarUsuarios();
            }
        } while (opcao != 0);
    }

    private void criarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Número: ");
        int numero = scanner.nextInt();

        Usuario novo = new Usuario(nome, numero);
        controller.create(novo);
    }

    private void editarUsuario() {
        System.out.print("Número atual: ");
        int numero = scanner.nextInt();
        System.out.print("Novo nome: ");
        String nome = scanner.next();
        System.out.print("Novo número: ");
        int novoNumero = scanner.nextInt();

        Usuario novo = new Usuario(nome, novoNumero);
        controller.update(numero, novo);
    }

    private void buscarUsuario() {
        System.out.print("Número: ");
        int numero = scanner.nextInt();
        Usuario u = controller.buscarUsuarioPorNumero(numero);
        if (u != null) {
            System.out.println("Nome: " + u.getNome() + ", Número: " + u.getNumero());
        }
    }

    private void apagarUsuario() {
        System.out.print("Número: ");
        int numero = scanner.nextInt();
        controller.delete(numero);
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = controller.getUsuarios();
        if (usuarios != null) {
            for (Usuario u : usuarios) {
                System.out.println("Nome: " + u.getNome() + " | Número: " + u.getNumero());
            }
        }
    }
}
