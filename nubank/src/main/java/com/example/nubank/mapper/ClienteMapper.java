package com.example.nubank.mapper;

import com.example.nubank.dto.request.ClienteDTO;
import com.example.nubank.dto.response.ClienteResponseDTO;
import com.example.nubank.model.Clientes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ContatoMapper.class})
public interface ClienteMapper {

    ClienteDTO toClienteDTO(Clientes clientes);

    ClienteResponseDTO toResponseDto(Clientes clientes);

    Clientes toCliente(ClienteDTO clienteDTO);

    List<ClienteResponseDTO> toResponseDtoList(List<Clientes> clientes);
}
