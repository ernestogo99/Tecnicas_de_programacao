package oop;

public class Humano extends  Servivo{

    public Humano(){
        super(42);
    }

    @Override
    public void respirar() {
        System.out.println("ooooooperrrrr");
    }
}
