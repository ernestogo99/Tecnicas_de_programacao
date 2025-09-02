package com.example.springBoot.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private double valor;
    private OffsetDateTime datahora;

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDatahora() {
        return datahora;
    }
}
