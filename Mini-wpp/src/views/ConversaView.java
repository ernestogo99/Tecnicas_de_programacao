package views;

import controllers.ConversaController;
import controllers.UsuarioController;
import models.Conversa;
import models.Mensagem;
import models.Usuario;


import java.util.List;
import java.util.Scanner;

public class ConversaView {
    private final ConversaController controller = new ConversaController();
    private final UsuarioController usuarioController;
    private final Scanner scanner = new Scanner(System.in);

    public ConversaView(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void exibirMenu(Usuario usuario1, Usuario usuario2) {
        Conversa conversa = controller.iniciarOuObterConversa(usuario1, usuario2);
        if (conversa == null) return;

        int opcao;
        do {
            System.out.println("\n--- MENU CONVERSA ---");
            System.out.println("1. Enviar mensagem");
            System.out.println("2. Editar mensagem");
            System.out.println("3. Apagar mensagem");
            System.out.println("4. Listar mensagens");
            System.out.println("5. Encaminhar mensagem");
            System.out.println("6. Listar conversas");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> enviarMensagem(conversa, usuario1);
                case 2 -> editarMensagem(conversa);
                case 3 -> apagarMensagem(conversa);
                case 4 -> listarMensagens(conversa);
                case 5 -> encaminharMensagem(conversa);
                case 6 -> listarTodasConversas();
            }
        } while (opcao != 0);
    }

    private void enviarMensagem(Conversa conversa, Usuario remetente) {
        System.out.print("Texto da mensagem: ");
        String texto = scanner.nextLine();
        controller.enviarMensagem(conversa, remetente, texto);
    }

    private void editarMensagem(Conversa conversa) {
        System.out.print("Texto antigo: ");
        String antigo = scanner.nextLine();
        System.out.print("Novo texto: ");
        String novo = scanner.nextLine();
        controller.editarMensagem(conversa, antigo, novo);
    }

    private void apagarMensagem(Conversa conversa) {
        System.out.print("Texto da mensagem a apagar: ");
        String texto = scanner.nextLine();
        controller.apagarMensagem(conversa, texto);
    }

    private void listarMensagens(Conversa conversa) {
        List<Mensagem> mensagens = controller.listarMensagens(conversa);
        if (mensagens != null) {
            for (Mensagem m : mensagens) {
                System.out.println("[" + m.getTime() + "] " + m.getRemetente().getNome() + ": " + m.getTexto());
            }
        }
    }

    private void encaminharMensagem(Conversa conversa) {
        System.out.print("Digite o texto da mensagem a encaminhar: ");
        String textoOriginal = scanner.nextLine();

        Mensagem msgAencaminhar=controller.buscarMensagemPortexto(conversa,textoOriginal);
        if(msgAencaminhar==null){
            return;
        }


        System.out.print("Digite o número do novo destinatário: ");
        int numeroNovoDestinatario = scanner.nextInt();
        scanner.nextLine();

        Usuario novoDestinatario = usuarioController.buscarUsuarioPorNumero(numeroNovoDestinatario);
        if (novoDestinatario == null) {
            return;
        }

       controller.encaminharMensagem(msgAencaminhar,novoDestinatario);


    }

    private void listarTodasConversas() {
        List<Conversa> conversas = controller.listarTodasConversas();
        if (conversas == null || conversas.isEmpty()) {
            System.out.println("Nenhuma conversa registrada.");
            return;
        }

        System.out.println("--- TODAS AS CONVERSAS ---");
        for (Conversa c : conversas) {
            System.out.println(c.getUsuario1().getNome() + " ↔ " + c.getUsuario2().getNome() +
                    " - " + c.obterMensagens().size() + " mensagem(ns)");
        }
    }
}

