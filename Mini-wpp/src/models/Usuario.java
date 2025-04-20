package models;

public class Usuario {
   private String nome;
   private int numero;

    public Usuario(String nome,int numero){
        this.nome=nome;
        this.numero=numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", numero=" + numero +
                '}';
    }
}
