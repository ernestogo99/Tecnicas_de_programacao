package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void adicionarUsuario(User user) {
        try {
            userService.addUser(user);
            System.out.println("Usuário adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    public User buscarUsuario(String nome) {
        try {
            User user = userService.getUser(nome);
            System.out.println("Usuário encontrado: " + user.getNome());
            return  user;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return  null;
        }
    }

    public List<User> listarUsuarios() {
        try {
            List<User> users = userService.getAllUsers();
            System.out.println("Lista de usuários:");
            for (User user : users) {
                System.out.println("- " + user.getNome());
            }
            return  users;
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
            return  null;
        }
    }

    public void removerUsuario(String nome) {
        try {
            userService.deleteUser(nome);
            System.out.println("Usuário removido com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizarUsuario(String nomeAtual, User novoUser) {
        try {
            userService.updateUser(nomeAtual, novoUser);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
