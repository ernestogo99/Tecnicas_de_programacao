package com.example.nubank.repository;

import com.example.nubank.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes,Long> {
}
