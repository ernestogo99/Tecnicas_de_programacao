package oop;

public class Endereco {
    private String rua;
    private String bairro;
    private String cidade;
    private String cep;
    private int numero;

    public  Endereco(String rua,String bairro,String cidade,String cep,int numero){
        this.rua=rua;
        this.bairro=bairro;
        this.cidade=cidade;
        this.cep=cep;
        this.numero=numero;
    }

    public String getRua(){
        return  this.rua;
    }

    public void  setRua(String rua){
        this.rua=rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cep='" + cep + '\'' +
                ", numero=" + numero +
                '}';
    }
}
