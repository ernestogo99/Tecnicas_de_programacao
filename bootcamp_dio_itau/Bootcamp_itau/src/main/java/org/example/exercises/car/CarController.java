package org.example.exercises.car;

import javax.swing.SwingUtilities;

public class CarController {
    private final Car model;
    private final CarView view;

    public CarController() {
        this.model = new Car();
        this.view = new CarView(this);
    }

    public void run() {
        SwingUtilities.invokeLater(() -> view.setVisible(true));
    }

    public void ligarCarro() {
        try {
            model.turnOn();
            view.showMessage("Carro ligado com sucesso.");
            refreshView();
        } catch (IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    public void desligarCarro() {
        try {
            model.turnOff();
            view.showMessage("Carro desligado com sucesso.");
            refreshView();
        } catch (IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    public void acelerar() {
        try {
            model.accelerate();
            view.showMessage("Velocidade aumentada em 1 km/h.");
            refreshView();
        } catch (IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    public void diminuirVelocidade() {
        try {
            model.brake();
            view.showMessage("Velocidade reduzida em 1 km/h.");
            refreshView();
        } catch (IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    public void virarEsquerda() {
        try {
            model.turnLeft();
            view.showMessage("Carro virou para a esquerda.");
            refreshView();
        } catch (IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    public void virarDireita() {
        try {
            model.turnRight();
            view.showMessage("Carro virou para a direita.");
            refreshView();
        } catch (IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    public void verificarVelocidade() {
        view.showMessage("Velocidade atual: " + model.getSpeed() + " km/h");
        refreshView();
    }

    public void trocarMarcha() {
        try {
            int gear = view.readGear();
            model.shiftGear(gear);
            view.showMessage("Marcha alterada para " + model.getGear());
            refreshView();
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    private void refreshView() {
        view.updateStatus(model.isEngineOn(), model.getSpeed(), model.getGear(), model.getDirection());
    }

    public static void main(String[] args) {
        new CarController().run();
    }
}
