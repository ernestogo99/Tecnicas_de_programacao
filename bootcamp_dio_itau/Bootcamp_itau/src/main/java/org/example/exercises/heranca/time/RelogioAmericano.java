package org.example.exercises.heranca.time;


public class RelogioAmericano extends Relogio {

    public RelogioAmericano(int hora, int minuto, int segundo) {
        super(hora, minuto, segundo);
    }

    @Override
    protected int getHoraMinima() {
        return 1;
    }

    @Override
    protected int getHoraMaxima() {
        return 12;
    }

    @Override
    public void ajustarHorario(Relogio outro) {
        int horaConvertida = outro.getHora() % 12;

        if (horaConvertida == 0) {
            horaConvertida = 12;
        }

        setHora(horaConvertida);
        setMinuto(outro.getMinuto());
        setSegundo(outro.getSegundo());
    }
}

