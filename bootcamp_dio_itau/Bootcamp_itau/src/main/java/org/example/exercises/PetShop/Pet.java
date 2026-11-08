package org.example.exercises.PetShop;

public class Pet {
    private final String name;
    private boolean clean;

    public Pet(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do pet é obrigatório.");
        }
        this.name = name.trim();
        this.clean = false;
    }

    public String getName() {
        return name;
    }

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }
}
