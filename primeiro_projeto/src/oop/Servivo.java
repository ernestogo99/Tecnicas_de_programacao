package oop;

public abstract class Servivo {
    protected int idade;

    public Servivo(int idade){
        this.idade=idade;
    }

    public  abstract void respirar();

    public void dormir(){
        System.out.println("ZZZZZ");
    }
}
