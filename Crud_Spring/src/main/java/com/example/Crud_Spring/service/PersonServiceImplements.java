package com.example.Crud_Spring.service;


import com.example.Crud_Spring.dto.request.PersonRequestDTO;
import com.example.Crud_Spring.dto.response.PersonResponseDTO;
import com.example.Crud_Spring.entity.Person;
import com.example.Crud_Spring.repository.PeopleRepository;
import com.example.Crud_Spring.util.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class PersonServiceImplements implements  PersonService {
    private final PeopleRepository peopleRepository;

    private final PersonMapper personMapper;


    @Override
    public PersonResponseDTO findById(Long id) {
        Person person=returnPerson(id);
        return  personMapper.toPersonDTO(person);
    }

    @Override
    public List<PersonResponseDTO> findAll() {
        return personMapper.toPleopleDTO(peopleRepository.findAll());

    }

    @Override
    public PersonResponseDTO register(PersonRequestDTO personDTO) {
        Person person=personMapper.toPerson(personDTO);
        Person savedPerson=peopleRepository.save(person);
        return  personMapper.toPersonDTO(savedPerson);
    }

    @Override
    public PersonResponseDTO update(PersonRequestDTO personRequestDTO,Long id) {
           Person person=returnPerson(id);
           personMapper.updatePersonData(person,personRequestDTO);
           person=peopleRepository.save(person);
           return personMapper.toPersonDTO(person);
    }

    @Override
    public String delete(Long id) {
        peopleRepository.deleteById(id);
        return  "Person deleted";
    }


    private Person returnPerson(Long id){
      return  peopleRepository.findById(id).orElseThrow(()->new RuntimeException("Person wasn't found on database"));
    }
}

