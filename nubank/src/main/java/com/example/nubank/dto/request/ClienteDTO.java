package com.example.nubank.dto.request;

import com.example.nubank.model.Contatos;

import java.util.List;

public record   ClienteDTO(String nome, List<ContatoDTO> contatos) {
}
