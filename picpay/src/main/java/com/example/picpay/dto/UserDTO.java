package com.example.picpay.dto;

import com.example.picpay.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String cpf, BigDecimal balance, String email, String password,
                      UserType type) {
}
