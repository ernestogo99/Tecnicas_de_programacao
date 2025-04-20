package views;

import controllers.UsuarioController;
import models.Usuario;

import java.util.Scanner;

public class AppView {
    private final UsuarioController usuarioController = new UsuarioController();
    private final UsuarioView usuarioView = new UsuarioView(usuarioController);
    private final ConversaView conversaView = new ConversaView(usuarioController);
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar usuários");
            System.out.println("2. Iniciar conversa");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> usuarioView.exibirMenu();
                case 2 -> iniciarConversa();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void iniciarConversa() {
        System.out.print("Número do primeiro usuário: ");
        int numero1 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Número do segundo usuário: ");
        int numero2 = scanner.nextInt();
        scanner.nextLine();

        Usuario u1 = usuarioController.buscarUsuarioPorNumero(numero1);
        Usuario u2 = usuarioController.buscarUsuarioPorNumero(numero2);

        if (u1 == null || u2 == null) {
            System.out.println("Um ou ambos os usuários não foram encontrados.");
            return;
        }

        conversaView.exibirMenu(u1, u2);
    }
}
