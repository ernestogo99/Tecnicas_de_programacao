package controllers;
import models.Usuario;
import services.UsuarioService;

import java.util.List;

public class UsuarioController {
    private final UsuarioService service=new UsuarioService();

    public Usuario create(Usuario usuario){
        try{
            return this.service.criarUsuario(usuario);
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Usuario update(int numero,Usuario usuario){
        try{
            return service.editarUsuario(numero,usuario);
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
            return  null;
        }
    }

    public Usuario buscarUsuarioPorNumero(int numero){
        try{
            return service.buscarUsuarioPorNumero(numero);
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
            return  null;
        }
    }

    public boolean delete(int numero){
        try{
            return service.apagarUsuario(numero);
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<Usuario> getUsuarios(){
        try{
            return service.getUsuarios();
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
