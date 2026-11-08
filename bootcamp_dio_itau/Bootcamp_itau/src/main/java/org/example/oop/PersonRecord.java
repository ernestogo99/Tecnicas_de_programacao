package org.example.oop;

public record PersonRecord(
        String name,
        int age
)

{

    public PersonRecord(String name){
        this(name,24);
    }

    public PersonRecord {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}
