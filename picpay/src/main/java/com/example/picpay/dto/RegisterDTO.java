package com.example.picpay.dto;

import com.example.picpay.domain.user.UserType;

public record RegisterDTO(String cpf, String password, UserType type) {
}
