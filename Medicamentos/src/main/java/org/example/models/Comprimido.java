package org.example.models;

public class Comprimido extends Medicamento {
    private final  String tipo= "Comprimido";

    public Comprimido(String nome,int quantidade,Number frequencia,String unidadeDemedida){
        super(nome,quantidade,frequencia,unidadeDemedida);
    }

    @Override
    public String gerarLinhaReceita() {
        return this.getNome() + " " + this.getFrequencia() + " " +  this.tipo + " de " + this.getQuantidade()+this.getUnidadeDemedida();
    }
}
