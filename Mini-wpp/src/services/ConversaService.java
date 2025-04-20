package services;

import models.Conversa;
import models.Mensagem;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ConversaService {
    private List<Conversa> conversas = new ArrayList<>();

    public Conversa obterOuCriarConversa(Usuario u1, Usuario u2) {
        for (Conversa c : conversas) {
            if ((c.getUsuario1().equals(u1) && c.getUsuario2().equals(u2)) ||
                    (c.getUsuario1().equals(u2) && c.getUsuario2().equals(u1))) {
                return c;
            }
        }
        Conversa nova = new Conversa(u1, u2);
        conversas.add(nova);
        return nova;
    }

    public void enviarMensagem(Conversa conversa, Usuario remetente, String texto) {
        if (!conversa.contemUsuario(remetente)) {
            throw new IllegalArgumentException("Usuário não participa da conversa.");
        }

        Usuario destinatario = remetente.equals(conversa.getUsuario1())
                ? conversa.getUsuario2() : conversa.getUsuario1();

        Mensagem msg = new Mensagem(texto, remetente, destinatario);
        msg.setCheckToEnviada();
        conversa.adicionarMsg(msg);
    }

    public void encaminharMensagem(Mensagem msgOriginal, Usuario novoDestinatario) {
        Conversa conversa = obterOuCriarConversa(msgOriginal.getRemetente(), novoDestinatario);
        Mensagem msgEncaminhada = msgOriginal.encaminhar(novoDestinatario);
        msgEncaminhada.setCheckToEnviada();
        conversa.adicionarMsg(msgEncaminhada);
    }

    public void editarMensagem(Conversa conversa, String textoAntigo, String novoTexto) {
        Mensagem mensagem = buscarMensagemPorTexto(conversa, textoAntigo);
        mensagem.setTexto(novoTexto);
    }

    public void apagarMensagem(Conversa conversa, String texto) {
        Mensagem mensagem = buscarMensagemPorTexto(conversa, texto);
        mensagem.apagarTexto();
        mensagem.setCheckToApagada();
    }

    public Mensagem buscarMensagemPorTexto(Conversa conversa, String texto) {
        for (Mensagem msg : conversa.obterMensagens()) {
            if (msg.getTexto().equals(texto)) {
                return msg;
            }
        }
        throw new RuntimeException("Mensagem com o texto informado não foi encontrada.");
    }

    public List<Mensagem> listarMensagens(Conversa conversa) {
        List<Mensagem> mensagens = conversa.obterMensagens();
        if (mensagens.isEmpty()) {
            throw new RuntimeException("Nenhuma mensagem encontrada na conversa.");
        }
        return mensagens;
    }

    public List<Conversa> getTodasConversas() {
        if(conversas.isEmpty()){
            throw  new RuntimeException("Nenhuma conversa criada");
        }
        return this.conversas;
    }
}
