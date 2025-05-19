package org.example.models;

public class Xarope extends Medicamento {
    private final String tipo= "Xarope";


    public Xarope(String nome,int quantidade,Number frequencia,String unidadeDemedida,int grauDeseguranca){
        super(nome,quantidade,frequencia,unidadeDemedida,grauDeseguranca);
    }

    @Override
    public String gerarLinhaReceita() {
        return this.getNome() + " " + this.getFrequencia() + " " +  this.tipo + " de " + this.getQuantidade()+this.getUnidadeDemedida();
    }
}
