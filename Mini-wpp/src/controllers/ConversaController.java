package controllers;

import models.Conversa;
import models.Mensagem;
import models.Usuario;
import services.ConversaService;

import java.util.List;

public class ConversaController {
    private final ConversaService conversaService = new ConversaService();

    public Conversa iniciarOuObterConversa(Usuario u1, Usuario u2) {
        try {
            return conversaService.obterOuCriarConversa(u1, u2);
        } catch (RuntimeException e) {
            System.err.println("Erro ao iniciar/obter conversa: " + e.getMessage());
            return null;
        }
    }

    public void enviarMensagem(Conversa conversa, Usuario remetente, String texto) {
        try {
            conversaService.enviarMensagem(conversa, remetente, texto);
            System.out.println("Mensagem enviada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao enviar mensagem: " + e.getMessage());
        }
    }

    public void encaminharMensagem(Mensagem mensagemOriginal, Usuario novoDestinatario) {
        try {
            conversaService.encaminharMensagem(mensagemOriginal, novoDestinatario);
            System.out.println("Mensagem encaminhada com sucesso!");
        } catch (RuntimeException e) {
            System.err.println("Erro ao encaminhar mensagem: " + e.getMessage());
        }
    }

    public Mensagem buscarMensagemPortexto(Conversa conversa,String texto){
        try{
           return conversaService.buscarMensagemPorTexto(conversa,texto);
        }catch (RuntimeException e) {
            System.err.println("Mensagem não encontrada: " + e.getMessage());
            return  null;
        }
    }

    public void editarMensagem(Conversa conversa, String textoAntigo, String novoTexto) {
        try {
            conversaService.editarMensagem(conversa, textoAntigo, novoTexto);
            System.out.println("Mensagem editada com sucesso!");
        } catch (RuntimeException e) {
            System.err.println("Erro ao editar mensagem: " + e.getMessage());
        }
    }

    public void apagarMensagem(Conversa conversa, String texto) {
        try {
            conversaService.apagarMensagem(conversa, texto);
            System.out.println("Mensagem apagada com sucesso!");
        } catch (RuntimeException e) {
            System.err.println("Erro ao apagar mensagem: " + e.getMessage());
        }
    }

    public List<Mensagem> listarMensagens(Conversa conversa) {
        try {
            return conversaService.listarMensagens(conversa);
        } catch (RuntimeException e) {
            System.err.println("Erro ao listar mensagens: " + e.getMessage());
            return null;
        }
    }

    public List<Conversa> listarTodasConversas() {
        try{
            return conversaService.getTodasConversas();
        }catch (RuntimeException e) {
            System.err.println("Erro ao listar conversas: " + e.getMessage());
            return null;
        }

    }
}
