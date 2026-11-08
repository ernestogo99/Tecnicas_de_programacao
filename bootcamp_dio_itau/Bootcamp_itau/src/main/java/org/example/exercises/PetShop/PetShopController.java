package org.example.exercises.PetShop;

import javax.swing.SwingUtilities;

public class PetShopController {
    private final PetMachine model;
    private final PetShopView view;

    public PetShopController() {
        this.model = new PetMachine();
        this.view = new PetShopView(this);
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            refreshView();
            view.setVisible(true);
        });
    }

    public void abastecerAgua() {
        try {
            model.addWater();
            view.showMessage("Água abastecida em 2 litros.");
            refreshView();
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    public void abastecerShampoo() {
        try {
            model.addShampoo();
            view.showMessage("Shampoo abastecido em 2 litros.");
            refreshView();
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    public void darBanho() {
        try {
            model.giveBath();
            view.showMessage("Banho realizado com sucesso.");
            refreshView();
        } catch (IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    public void colocarPet() {
        try {
            String petName = view.readPetName();
            Pet pet = new Pet(petName);
            model.insertPet(pet);
            view.showMessage("Pet " + pet.getName() + " foi colocado na máquina.");
            refreshView();
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    public void retirarPet() {
        try {
            Pet pet = model.removePet();
            view.showMessage("Pet " + pet.getName() + " foi retirado da máquina.");
            refreshView();
        } catch (IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    public void verificarAgua() {
        view.showMessage("Nível de água: " + model.getWaterLevel() + " litros");
        refreshView();
    }

    public void verificarShampoo() {
        view.showMessage("Nível de shampoo: " + model.getShampooLevel() + " litros");
        refreshView();
    }

    public void verificarPetNaMaquina() {
        String status = model.hasPetInMachine() ? "Sim" : "Não";
        view.showMessage("Há pet na máquina? " + status);
        refreshView();
    }

    public void limparMaquina() {
        try {
            model.cleanMachine();
            view.showMessage("Máquina limpa com sucesso.");
            refreshView();
        } catch (IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    private void refreshView() {
        view.updateStatus(model.getWaterLevel(), model.getShampooLevel(), model.hasPetInMachine(), model.needsCleaning());
    }

    public static void main(String[] args) {
        new PetShopController().run();
    }
}
