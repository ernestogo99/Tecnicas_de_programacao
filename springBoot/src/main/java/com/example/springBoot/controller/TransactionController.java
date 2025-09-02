package com.example.springBoot.controller;


import com.example.springBoot.dto.TransactionRequestDTO;
import com.example.springBoot.model.Transaction;
import com.example.springBoot.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequestDTO request){
        if(request.datahora().isAfter(OffsetDateTime.now())){
            return ResponseEntity.unprocessableEntity().build();
        }
        this.transactionService.addTransaction(new Transaction(request.valor(), request.datahora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransacTions(){
        this.transactionService.clearTransaction();
        return ResponseEntity.ok().build();
    }

}
