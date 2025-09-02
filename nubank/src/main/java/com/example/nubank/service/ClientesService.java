package com.example.nubank.service;

import com.example.nubank.dto.request.ClienteDTO;
import com.example.nubank.dto.response.ClienteResponseDTO;
import com.example.nubank.dto.response.ContatoResponseDTO;
import com.example.nubank.mapper.ClienteMapper;
import com.example.nubank.mapper.ContatoMapper;
import com.example.nubank.model.Clientes;
import com.example.nubank.model.Contatos;
import com.example.nubank.repository.ClientesRepository;
import com.example.nubank.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private ContatoMapper contatoMapper;

    public ClienteResponseDTO salvarCliente(ClienteDTO clienteDTO) {
        Clientes cliente=this.clienteMapper.toCliente(clienteDTO);
        if (cliente.getContatos() != null) {
            cliente.getContatos().forEach(c -> c.setClientes(cliente));
        }
        Clientes salvo=this.clientesRepository.save(cliente);
        return this.clienteMapper.toResponseDto(salvo);
    }

    public List<ClienteResponseDTO> listarClientes(){
        return  this.clienteMapper.toResponseDtoList(this.clientesRepository.findAll());
    }

    public List<ContatoResponseDTO> listarContatosPorId(Long id){
        Clientes clientesEncontrado=this.clientesRepository.findById(id).orElseThrow(()->new RuntimeException("Cliente não encontrado"));
        return this.contatoMapper.toResponseList(clientesEncontrado.getContatos());
    }
}
