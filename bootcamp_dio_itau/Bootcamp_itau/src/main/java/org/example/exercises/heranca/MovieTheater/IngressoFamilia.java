package org.example.exercises.heranca.MovieTheater;

public class IngressoFamilia extends Ingresso {
    private int numeroPessoas;

    public IngressoFamilia(double valor, String nomeFilme, boolean dublado, int numeroPessoas) {
        super(valor, nomeFilme, dublado);
        this.numeroPessoas = numeroPessoas;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public int getPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    public void setPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    @Override
    public double getValorReal() {
        double valorTotal = valor * numeroPessoas;
        if (numeroPessoas > 3) {
            valorTotal *= 0.95;
        }
        return valorTotal;
    }
}
