package org.example.services;

import org.example.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> usuarios=new ArrayList<User>();


    public void addUser(User user){
        if(user==null){
            throw new IllegalArgumentException(" Os dados não podem ser nulos");
        }

        usuarios.add(user);
    }

    public User getUser(String nome){
        for(User user:usuarios){
            if(user.getNome().equals(nome)){
                return  user;
            }
        }
        throw  new RuntimeException("Usuário não encontrado");
    }

    public  List<User> getAllUsers(){
        if(usuarios.isEmpty()){
            throw  new RuntimeException("Nenhum usuário cadastrado");
        }
        return this.usuarios;
    }

    public void deleteUser(String nome){
        User user=getUser(nome);
        usuarios.remove(user);
    }

    public void updateUser(String nome,User user){
        User userEncontrado=getUser(nome);
        userEncontrado.setNome(user.getNome());
    }

    @Override
    public String toString() {
        return "UserService{" +
                "usuarios=" + usuarios +
                '}';
    }
}
