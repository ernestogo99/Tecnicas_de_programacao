package com.example.Crud_Spring.dto.response;


import com.example.Crud_Spring.entity.Person;

public class PersonResponseDTO {
    private long id;


    private String nome;


    private String cpf;


    private Integer idade;

    public PersonResponseDTO(Person person){
        this.id=person.getId();
        this.nome=person.getNome();
        this.cpf=person.getCpf();
        this.idade=person.getIdade();
    }
}
