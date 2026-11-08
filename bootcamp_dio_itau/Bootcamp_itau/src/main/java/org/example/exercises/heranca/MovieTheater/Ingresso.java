package org.example.exercises.heranca.MovieTheater;

public class Ingresso  {
    protected double valor;
    protected String nomeFilme;
    protected boolean dublado;

    public Ingresso(double valor, String nomeFilme, boolean dublado) {
        this.valor = valor;
        this.nomeFilme = nomeFilme;
        this.dublado = dublado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public boolean isDublado() {
        return dublado;
    }

    public void setDublado(boolean dublado) {
        this.dublado = dublado;
    }

    public void setLegendado(boolean legendado) {
        this.dublado = !legendado;
    }

    public boolean isLegendado() {
        return !dublado;
    }

    public double getValorReal() {
        return valor;
    }

    public double getPreco() {
        return getValorReal();
    }

    public void setPreco(double valor) {
        this.valor = valor;
    }

    public double getValorTotal() {
        return getValorReal();
    }
}
