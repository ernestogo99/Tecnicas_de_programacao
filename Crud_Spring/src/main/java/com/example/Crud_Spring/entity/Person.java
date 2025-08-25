package com.example.Crud_Spring.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_people")
@Getter
@Setter

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "name",nullable = false)
    private String nome;

    @Column(name = "cpf",nullable = false,unique = true)
    private String cpf;

    @Column(name = "age",nullable = false)
    private Integer idade;


    @Builder
    public Person(String nome, String cpf, Integer idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }
}
