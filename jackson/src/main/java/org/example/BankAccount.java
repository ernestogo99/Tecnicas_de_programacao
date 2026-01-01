package org.example;

public class BankAccount {

    private int id;
    private String holderName;

    public BankAccount(){

    }

    public BankAccount(int id, String holderName) {
        this.id = id;
        this.holderName = holderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", holderName='" + holderName + '\'' +
                '}';
    }
}
