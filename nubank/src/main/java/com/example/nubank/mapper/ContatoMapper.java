package com.example.nubank.mapper;


import com.example.nubank.dto.request.ContatoDTO;
import com.example.nubank.dto.response.ContatoResponseDTO;
import com.example.nubank.model.Contatos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ContatoMapper {

    ContatoDTO toDTO(Contatos contatos);

    @Mapping(source = "clientes.id", target = "clienteId")
    ContatoResponseDTO toResponseDTO(Contatos contatos);

    Contatos toEntity(ContatoDTO contatoDTO);

    List<ContatoDTO> toDtoList(List<Contatos> contatos);

    List<Contatos> toEntityList(List<ContatoDTO> dtos);

    List<ContatoResponseDTO> toResponseList(List<Contatos> contatos);

}
