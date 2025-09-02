package com.example.nubank.service;


import com.example.nubank.dto.request.ContatoDTO;
import com.example.nubank.dto.response.ContatoResponseDTO;
import com.example.nubank.mapper.ContatoMapper;
import com.example.nubank.model.Clientes;
import com.example.nubank.model.Contatos;
import com.example.nubank.repository.ClientesRepository;
import com.example.nubank.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoMapper contatoMapper;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private ContatoRepository contatoRepository;


    public ContatoResponseDTO criarContato(ContatoDTO contatoDTO){
        Clientes clientesOptional=this.clientesRepository.findById(contatoDTO.clienteId().get())
                .orElseThrow(()->new RuntimeException("Cliente não encontrado"));

        Contatos contato=this.contatoMapper.toEntity(contatoDTO);
        contato.setClientes(clientesOptional);
        Contatos save=this.contatoRepository.save(contato);
        return this.contatoMapper.toResponseDTO(save);
    }

}
