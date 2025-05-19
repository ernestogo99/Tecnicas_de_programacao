package org.example.models;

import org.example.interfaces.IsSafety;

public abstract class Medicamento implements IsSafety {
    private String nome;
    private int quantidade;
    private Number frequencia;
    private String unidadeDemedida;
    private int grauDeSeguranca;

    public Medicamento(String nome,int quantidade,Number frequencia,String unidadeDemedida,int grauDeSeguranca){
        this.grauDeSeguranca=grauDeSeguranca;
        this.nome=nome;
        this.quantidade=quantidade;
        this.frequencia=frequencia;
        this.unidadeDemedida=unidadeDemedida;
    }

    public abstract String gerarLinhaReceita();



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int grauDeseguranca() {
        return this.grauDeSeguranca;
    }

    public Number getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Number frequencia) {
        this.frequencia = frequencia;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeDemedida() {
        return unidadeDemedida;
    }

    public void setUnidadeDemedida(String unidadeDemedida) {
        this.unidadeDemedida = unidadeDemedida;
    }
}
