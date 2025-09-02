package com.example.nubank.dto.response;

import com.example.nubank.model.Contatos;

import java.util.List;

public record   ClienteResponseDTO(Long id, String nome, List<ContatoResponseDTO> contatos) {
}
