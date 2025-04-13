package oop;

public class Pessoa implements  People {
   private String nome;
   private String cpf;
   private Endereco end;

    public Pessoa(String nome,String cpf){
        this.nome=nome;
        this.cpf=cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEnd() {
        return end;
    }

    public void setEnd(Endereco end) {
        this.end = end;
    }

    @Override
    public void darOi() {
        System.out.println("oiiii");
    }

    @Override
    public void gritar() {
        System.out.println("AAAAAAAAAAAAAAAAAA");
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + (end != null ? end.toString() : "null") +
                '}';
    }
}
