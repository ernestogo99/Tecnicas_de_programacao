package com.example.Crud_Spring.controller;


import com.example.Crud_Spring.dto.request.PersonRequestDTO;
import com.example.Crud_Spring.dto.response.PersonResponseDTO;
import com.example.Crud_Spring.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}")
    private ResponseEntity<PersonResponseDTO> findById(@PathVariable Long id){
        return  ResponseEntity.ok().body(personService.findById(id));
    }


    @GetMapping("/")
    private ResponseEntity<List<PersonResponseDTO>> findById(){
        return  ResponseEntity.ok(personService.findAll());
    }


    @PostMapping
    public ResponseEntity<PersonResponseDTO> create(@RequestBody PersonRequestDTO dto) {
        PersonResponseDTO created = personService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> update(@PathVariable Long id,
                                                    @RequestBody PersonRequestDTO dto) {
        PersonResponseDTO updated = personService.update(dto, id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String result = personService.delete(id);
        return ResponseEntity.ok(result);
    }





}
