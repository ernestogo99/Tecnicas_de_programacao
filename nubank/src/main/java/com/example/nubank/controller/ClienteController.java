package com.example.nubank.controller;


import com.example.nubank.dto.request.ClienteDTO;
import com.example.nubank.dto.response.ClienteResponseDTO;
import com.example.nubank.dto.response.ContatoResponseDTO;
import com.example.nubank.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")

public class ClienteController {

    @Autowired
    private  ClientesService clientesService;


    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@RequestBody ClienteDTO clienteDTO){
        ClienteResponseDTO clienteResponseDTO=this.clientesService.salvarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAll(){
        return ResponseEntity.ok(clientesService.listarClientes());
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatoResponseDTO>> listarContatosPorId(@PathVariable Long id){
        return ResponseEntity.ok(clientesService.listarContatosPorId(id));
    }

}
