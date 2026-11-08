package org.example.exercises.heranca.MovieTheater;

public class MeiaEntrada extends Ingresso {
    public MeiaEntrada(double valor, String nomeFilme, boolean dublado) {
        super(valor, nomeFilme, dublado);
    }

    @Override
    public double getValorReal() {
        return valor / 2;
    }
}
