package services;
import models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private List<Usuario> usuarios=new ArrayList<Usuario>();


    public Usuario criarUsuario(Usuario data){
        if(data==null){
            throw  new IllegalArgumentException("Dados do usuário não podem ser nulos");
        }

        Usuario novoUsuario= new Usuario(
                data.getNome(),
                data.getNumero()
        );
        usuarios.add(novoUsuario);
        return novoUsuario;
    }

    public Usuario buscarUsuarioPorNumero(int numero){
        for(Usuario usuario:usuarios){
            if(usuario.getNumero()==numero){
                return usuario;
            }
        }
        throw  new RuntimeException("Usuario com número: "+numero + " não encontrado");
    }

    public boolean apagarUsuario(int numero){
        Usuario usuario=this.buscarUsuarioPorNumero(numero);
        usuarios.remove(usuario);
        return true;
    }

    public Usuario editarUsuario(int numero,Usuario data){
        Usuario usuario=this.buscarUsuarioPorNumero(numero);
        usuario.setNome(data.getNome());
        usuario.setNumero(data.getNumero());

        return usuario;
    }

    public List<Usuario> getUsuarios(){
        if(this.usuarios.isEmpty()){
            throw  new RuntimeException("Nenhum usuário cadastrado ");
        }
        return this.usuarios;
    }

}
