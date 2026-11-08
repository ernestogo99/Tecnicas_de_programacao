package org.example.oop;

import java.time.LocalDateTime;

public class Person {
    private String name;
    private int age;
    private int lastYearAgeInc= LocalDateTime.now().getYear()-1;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void incAge(){
        if(this.lastYearAgeInc>= LocalDateTime.now().getYear()){
            System.out.println("Você só pode aumentar a idade uma vez por ano.");
            return;
        }
        this.age++;
        this.lastYearAgeInc= LocalDateTime.now().getYear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
