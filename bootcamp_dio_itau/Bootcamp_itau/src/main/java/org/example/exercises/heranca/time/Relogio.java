package org.example.exercises.heranca.time;


public abstract class Relogio {
    private int hora;
    private int minuto;
    private int segundo;

    public Relogio(int hora, int minuto, int segundo) {
        setHora(hora);
        setMinuto(minuto);
        setSegundo(segundo);
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        if (hora < getHoraMinima() || hora > getHoraMaxima()) {
            throw new IllegalArgumentException("Hora inválida.");
        }

        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        if (minuto < 0 || minuto > 59) {
            throw new IllegalArgumentException("Minuto inválido.");
        }

        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        if (segundo < 0 || segundo > 59) {
            throw new IllegalArgumentException("Segundo inválido.");
        }

        this.segundo = segundo;
    }

    public String getHorario() {
        return String.format("%02d:%02d:%02d",
                hora, minuto, segundo);
    }

    protected abstract int getHoraMinima();

    protected abstract int getHoraMaxima();

    public abstract void ajustarHorario(Relogio outro);
}

