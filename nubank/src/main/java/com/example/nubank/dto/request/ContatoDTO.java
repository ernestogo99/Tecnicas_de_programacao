package com.example.nubank.dto.request;

import java.util.Optional;

public record ContatoDTO(String telefone, String email , Optional<Long>clienteId) {
}
