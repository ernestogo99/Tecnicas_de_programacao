package com.example.Crud_Spring.service;


import com.example.Crud_Spring.dto.request.PersonRequestDTO;
import com.example.Crud_Spring.dto.response.PersonResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

    PersonResponseDTO findById(Long id);
    List<PersonResponseDTO> findAll();
    PersonResponseDTO register(PersonRequestDTO personDTO);
    PersonResponseDTO update(PersonRequestDTO personRequestDTO,Long id);
    String delete(Long id);


}
