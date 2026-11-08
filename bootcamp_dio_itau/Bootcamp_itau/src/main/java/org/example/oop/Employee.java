package org.example.oop;

public sealed abstract class Employee permits Manager, Salesman {
    private String code;
    private String name;
    private String adress;
    private int age;
    private double salary;


    public Employee(String code, String name, String adress, int age, double salary) {
        this.code = code;
        this.name = name;
        this.adress = adress;
        this.age = age;
        this.salary = salary;
    }

    public Employee(){

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
