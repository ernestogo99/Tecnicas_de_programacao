package com.example.nubank.controller;

import com.example.nubank.dto.request.ContatoDTO;
import com.example.nubank.dto.response.ContatoResponseDTO;
import com.example.nubank.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping
    ResponseEntity<ContatoResponseDTO> criarContato(@RequestBody ContatoDTO contatoDTO){
        ContatoResponseDTO contatoResponseDTO=this.contatoService.criarContato(contatoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoResponseDTO);
    }
}
