package Views;

import Controllers.PessoaController;
import Models.Pessoa;

import java.util.Scanner;

public class PessoaView {
    private final PessoaController controller = new PessoaController();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Menu Pessoa ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");
            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> atualizar();
                case 4 -> deletar();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrar() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        int telefone = scanner.nextInt();

        Pessoa nova = new Pessoa( nome, idade, email, telefone);
        controller.create(nova);
        System.out.println("Pessoa cadastrada com sucesso!");
    }

    private void listar() {
        for (Pessoa p : controller.getAll()) {
            System.out.println(p.getId() + ": " + p.getNome() + ", " + p.getEmail());
        }
    }

    private void atualizar() {
        System.out.print("ID da pessoa a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Pessoa p = controller.getById(id);
        if (p == null) {
            System.out.println("Pessoa não encontrada!");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo email: ");
        String email = scanner.nextLine();
        System.out.print("Novo telefone: ");
        int telefone = scanner.nextInt();

        Pessoa dados = new Pessoa(nome, idade, email, telefone);
        controller.update(id, dados);
        System.out.println("Pessoa atualizada!");
    }

    private void deletar() {
        System.out.print("ID da pessoa a deletar: ");
        int id = scanner.nextInt();
        if (controller.delete(id)) {
            System.out.println("Pessoa deletada com sucesso!");
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }
}
