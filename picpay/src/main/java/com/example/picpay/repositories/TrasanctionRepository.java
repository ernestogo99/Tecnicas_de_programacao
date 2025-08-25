package com.example.picpay.repositories;

import com.example.picpay.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrasanctionRepository extends JpaRepository<Transaction,Long> {
}
