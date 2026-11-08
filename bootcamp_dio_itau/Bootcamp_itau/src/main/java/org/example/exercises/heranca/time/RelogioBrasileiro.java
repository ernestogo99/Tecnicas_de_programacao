package org.example.exercises.heranca.time;


public class RelogioBrasileiro extends Relogio {

    public RelogioBrasileiro(int hora, int minuto, int segundo) {
        super(hora, minuto, segundo);
    }

    @Override
    protected int getHoraMinima() {
        return 0;
    }

    @Override
    protected int getHoraMaxima() {
        return 23;
    }

    @Override
    public void ajustarHorario(Relogio outro) {
        setHora(outro.getHora());
        setMinuto(outro.getMinuto());
        setSegundo(outro.getSegundo());
    }
}
