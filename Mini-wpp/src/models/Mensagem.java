package models;
import enums.CheckVisualizacao;
import java.time.LocalDateTime;

public class Mensagem {
    private LocalDateTime time;
    private String texto;
    private Usuario remetente;
    private Usuario destinatario;
    private CheckVisualizacao check;

    public Mensagem(String texto,Usuario remetente,Usuario destinatario){
        this.time=LocalDateTime.now();
        this.texto=texto;
        this.remetente=remetente;
        this.destinatario=destinatario;
    }





    public LocalDateTime getTime() {
        return time;
    }


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void apagarTexto(){
        this.texto="[mensagem apagada]";
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public CheckVisualizacao getCheck() {
        return check;
    }

    public void setCheckToEnviada(){
        this.check=CheckVisualizacao.ENVIADA;
    }

    public void setCheckToEntregue(){
        this.check=CheckVisualizacao.ENTREGUE;
    }

    public void setCheckToApagada(){
        this.check=CheckVisualizacao.APAGADA;
    }

    public void setCheckToLida(){
        this.check=CheckVisualizacao.LIDA;
    }

    public Mensagem encaminhar(Usuario novoDestinatario){
        return new Mensagem(this.texto,this.remetente,novoDestinatario);
    }
}
