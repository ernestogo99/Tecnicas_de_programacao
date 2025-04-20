package models;

import java.util.ArrayList;
import java.util.List;

public class Conversa {
    private Usuario usuario1;
    private Usuario usuario2;
    private List<Mensagem> mensagens=new ArrayList<>();

    public Conversa(Usuario usuario1,Usuario usuario2){
        this.usuario1=usuario1;
        this.usuario2=usuario2;
    }

   public List<Mensagem> obterMensagens(){
        return this.mensagens;
   }

   public void adicionarMsg(Mensagem msg){
        this.mensagens.add(msg);
   }

   public Usuario getUsuario1(){
        return this.usuario1;
   }

    public Usuario getUsuario2(){
        return this.usuario2;
    }

   public  boolean contemUsuario(Usuario usuario){
        return usuario.equals(usuario1) || usuario.equals(usuario2);
   }





 }
