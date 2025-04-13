package Models;

public class Pessoa {
   private String nome;
   private int id;
   private int idade;
   private String email;
   private int telefone;

    public Pessoa(String nome, int idade, String email, int telefone)
    {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.telefone = telefone;


    }




    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", idade=" + idade +
                ", email='" + email + '\'' +
                ", telefone=" + telefone +
                '}';
    }
}
