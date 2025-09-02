package com.example.nubank.repository;

import com.example.nubank.model.Contatos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contatos,Long> {

}
