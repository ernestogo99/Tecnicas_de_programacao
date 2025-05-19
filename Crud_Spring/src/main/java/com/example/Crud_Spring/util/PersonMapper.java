package com.example.Crud_Spring.util;

import com.example.Crud_Spring.dto.request.PersonRequestDTO;
import com.example.Crud_Spring.dto.response.PersonResponseDTO;
import com.example.Crud_Spring.entity.Person;

import java.util.List;
import java.util.stream.Collectors;


public class PersonMapper {

    public Person toPerson(PersonRequestDTO personDTO){
        return Person.builder().cpf(personDTO.getCpf()).idade(personDTO.getIdade()).nome(personDTO.getNome()).build();

    }

    public PersonResponseDTO toPersonDTO(Person person){
        return new PersonResponseDTO(person);
    }

    public List<PersonResponseDTO> toPleopleDTO(List<Person> peoplelist){
        return peoplelist.stream().map(PersonResponseDTO::new).collect(Collectors.toList());
    }

    public void updatePersonData(Person person,PersonRequestDTO personDTO){
        person.setNome(personDTO.getNome());
        person.setCpf(personDTO.getCpf());
        person.setIdade(personDTO.getIdade());
    }

}
