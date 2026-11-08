package org.example.exercises.car;

public class Car {
    private static final int MAX_SPEED = 120;
    private static final int MIN_SPEED = 0;
    private static final int MAX_GEAR = 6;
    private static final int MIN_GEAR = 0;

    private boolean engineOn;
    private int speed;
    private int gear;
    private String direction;

    public Car() {
        this.engineOn = false;
        this.speed = MIN_SPEED;
        this.gear = MIN_GEAR;
        this.direction = "Reto";
    }

    public void turnOn() {
        if (engineOn) {
            throw new IllegalStateException("O carro já está ligado.");
        }
        engineOn = true;
    }

    public void turnOff() {
        if (!engineOn) {
            throw new IllegalStateException("O carro já está desligado.");
        }

        if (gear != MIN_GEAR || speed != MIN_SPEED) {
            throw new IllegalStateException("Para desligar, o carro deve estar em ponto morto e com velocidade zero.");
        }

        engineOn = false;
        direction = "Reto";
    }

    public void accelerate() {
        validateEngineOn();

        if (gear == MIN_GEAR) {
            throw new IllegalStateException("Não é possível acelerar com o carro em ponto morto.");
        }

        int nextSpeed = speed + 1;
        if (nextSpeed > MAX_SPEED) {
            throw new IllegalStateException("Velocidade máxima atingida: 120 km/h.");
        }

        if (nextSpeed > getMaxSpeedForGear(gear)) {
            throw new IllegalStateException("Velocidade acima do limite da marcha atual.");
        }

        speed = nextSpeed;
    }

    public void brake() {
        validateEngineOn();

        if (speed == MIN_SPEED) {
            throw new IllegalStateException("O carro já está parado.");
        }

        int nextSpeed = speed - 1;
        if (nextSpeed < getMinSpeedForGear(gear)) {
            throw new IllegalStateException("A redução de velocidade não pode deixar a velocidade fora da faixa da marcha atual.");
        }

        speed = nextSpeed;
    }

    public void shiftGear(int targetGear) {
        validateEngineOn();

        if (targetGear < MIN_GEAR || targetGear > MAX_GEAR) {
            throw new IllegalArgumentException("A marcha deve estar entre 0 e 6.");
        }

        if (Math.abs(targetGear - gear) > 1) {
            throw new IllegalStateException("Não é permitido pular marcha. Troque apenas para a marcha adjacente.");
        }

        if (targetGear == MIN_GEAR && speed != MIN_SPEED) {
            throw new IllegalStateException("Para entrar em ponto morto, a velocidade deve ser zero.");
        }

        if (targetGear != MIN_GEAR && (speed < getMinSpeedForGear(targetGear) || speed > getMaxSpeedForGear(targetGear))) {
            throw new IllegalStateException("A velocidade atual não é compatível com a marcha selecionada.");
        }

        gear = targetGear;
    }

    public void turnLeft() {
        validateEngineOn();

        if (speed < 1 || speed > 40) {
            throw new IllegalStateException("O carro só pode virar para a esquerda com velocidade entre 1 km/h e 40 km/h.");
        }

        direction = "Esquerda";
    }

    public void turnRight() {
        validateEngineOn();

        if (speed < 1 || speed > 40) {
            throw new IllegalStateException("O carro só pode virar para a direita com velocidade entre 1 km/h e 40 km/h.");
        }

        direction = "Direita";
    }

    public int getSpeed() {
        return speed;
    }

    public int getGear() {
        return gear;
    }

    public boolean isEngineOn() {
        return engineOn;
    }

    public String getDirection() {
        return direction;
    }

    public String getStatus() {
        return "Ligado: " + engineOn + " | Marcha: " + gear + " | Velocidade: " + speed + " km/h | Direção: " + direction;
    }

    private void validateEngineOn() {
        if (!engineOn) {
            throw new IllegalStateException("O carro está desligado. Ligue o veículo antes de operar.");
        }
    }

    private int getMinSpeedForGear(int gearNumber) {
        return switch (gearNumber) {
            case 0, 1 -> 0;
            case 2 -> 20;
            case 3 -> 40;
            case 4 -> 60;
            case 5 -> 80;
            case 6 -> 100;
            default -> throw new IllegalArgumentException("Marcha inválida: " + gearNumber);
        };
    }

    private int getMaxSpeedForGear(int gearNumber) {
        return switch (gearNumber) {
            case 0 -> 0;
            case 1 -> 20;
            case 2 -> 40;
            case 3 -> 60;
            case 4 -> 80;
            case 5 -> 100;
            case 6 -> 120;
            default -> throw new IllegalArgumentException("Marcha inválida: " + gearNumber);
        };
    }
}
