package com.example.springBoot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(@NotNull @Min(0) double valor,@NotNull OffsetDateTime datahora) {
}
