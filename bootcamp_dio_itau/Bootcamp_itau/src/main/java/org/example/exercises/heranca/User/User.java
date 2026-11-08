package org.example.exercises.heranca.User;

public class User {
    private String nome;
    private String email;
    private String senha;
    private final boolean isAdministrador;
    private boolean logado;

    public User(String nome, String email, String senha,
                   boolean isAdministrador) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isAdministrador = isAdministrador;
        this.logado = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdministrador() {
        return isAdministrador;
    }

    public boolean isLogado() {
        return logado;
    }

    public boolean realizarLogin(String email, String senha) {
        if (this.email.equals(email) && this.senha.equals(senha)) {
            logado = true;
            System.out.println("Login realizado com sucesso.");
            return true;
        }

        System.out.println("Email ou senha incorretos.");
        return false;
    }

    public void realizarLogoff() {
        if (logado) {
            logado = false;
            System.out.println("Logoff realizado com sucesso.");
        } else {
            System.out.println("O usuário não está logado.");
        }
    }

    public void alterarDados(String novoNome, String novoEmail) {
        this.nome = novoNome;
        this.email = novoEmail;
        System.out.println("Dados alterados com sucesso.");
    }

    public boolean alterarSenha(String senhaAtual, String novaSenha) {
        if (this.senha.equals(senhaAtual)) {
            this.senha = novaSenha;
            System.out.println("Senha alterada com sucesso.");
            return true;
        }

        System.out.println("A senha atual está incorreta.");
        return false;
    }
}
