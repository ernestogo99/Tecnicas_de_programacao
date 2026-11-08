package org.example.exercises.PetShop;

public class PetMachine {
    private static final int MAX_WATER = 30;
    private static final int MAX_SHAMPOO = 10;
    private static final int BATH_WATER_CONSUMPTION = 10;
    private static final int BATH_SHAMPOO_CONSUMPTION = 2;
    private static final int CLEANING_WATER_CONSUMPTION = 3;
    private static final int CLEANING_SHAMPOO_CONSUMPTION = 1;
    private static final int REFILL_AMOUNT = 2;

    private int waterLevel;
    private int shampooLevel;
    private Pet petInMachine;
    private boolean needsCleaning;

    public PetMachine() {
        this.waterLevel = 0;
        this.shampooLevel = 0;
        this.petInMachine = null;
        this.needsCleaning = false;
    }

    public void addWater() {
        int nextLevel = waterLevel + REFILL_AMOUNT;
        waterLevel = Math.min(nextLevel, MAX_WATER);
    }

    public void addShampoo() {
        int nextLevel = shampooLevel + REFILL_AMOUNT;
        shampooLevel = Math.min(nextLevel, MAX_SHAMPOO);
    }

    public void insertPet(Pet pet) {
        if (pet == null) {
            throw new IllegalArgumentException("É necessário informar um pet.");
        }

        if (petInMachine != null) {
            throw new IllegalStateException("A máquina já possui um pet.");
        }

        if (needsCleaning) {
            throw new IllegalStateException("A máquina precisa ser limpa antes de receber outro pet.");
        }

        petInMachine = pet;
    }

    public Pet removePet() {
        if (petInMachine == null) {
            throw new IllegalStateException("Não há pet na máquina.");
        }

        Pet pet = petInMachine;
        petInMachine = null;

        if (!pet.isClean()) {
            needsCleaning = true;
        }

        return pet;
    }

    public void giveBath() {
        if (petInMachine == null) {
            throw new IllegalStateException("Não há pet para dar banho.");
        }

        if (waterLevel < BATH_WATER_CONSUMPTION) {
            throw new IllegalStateException("Água insuficiente para dar banho.");
        }

        if (shampooLevel < BATH_SHAMPOO_CONSUMPTION) {
            throw new IllegalStateException("Shampoo insuficiente para dar banho.");
        }

        waterLevel -= BATH_WATER_CONSUMPTION;
        shampooLevel -= BATH_SHAMPOO_CONSUMPTION;
        petInMachine.setClean(true);
    }

    public void cleanMachine() {
        if (petInMachine != null) {
            throw new IllegalStateException("Retire o pet antes de limpar a máquina.");
        }

        if (waterLevel < CLEANING_WATER_CONSUMPTION) {
            throw new IllegalStateException("Água insuficiente para limpar a máquina.");
        }

        if (shampooLevel < CLEANING_SHAMPOO_CONSUMPTION) {
            throw new IllegalStateException("Shampoo insuficiente para limpar a máquina.");
        }

        waterLevel -= CLEANING_WATER_CONSUMPTION;
        shampooLevel -= CLEANING_SHAMPOO_CONSUMPTION;
        needsCleaning = false;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public int getShampooLevel() {
        return shampooLevel;
    }

    public boolean hasPetInMachine() {
        return petInMachine != null;
    }

    public boolean needsCleaning() {
        return needsCleaning;
    }

    public Pet getPetInMachine() {
        return petInMachine;
    }
}
