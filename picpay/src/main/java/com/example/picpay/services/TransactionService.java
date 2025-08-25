package com.example.picpay.services;


import com.example.picpay.domain.transaction.Transaction;
import com.example.picpay.domain.user.User;
import com.example.picpay.dto.TransactionDTO;
import com.example.picpay.repositories.TrasanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrasanctionRepository repository;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception{
        User sender=this.userService.findUserById(transaction.senderId());
        User receiver=this.userService.findUserById(transaction.receiverId());

        this.userService.validateTransaction(sender,transaction.value());

        Transaction newTransaction=new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setReceiver(receiver);
        newTransaction.setSender(sender);
        newTransaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        return newTransaction;

    }

    public boolean autorizeTransaction(User sender, BigDecimal value){
       ResponseEntity<Map> autorizationResponse = restTemplate.getForEntity("url", Map.class);
       if(autorizationResponse.getStatusCode()== HttpStatus.OK && autorizationResponse.getBody().get("message")=="autorizado"){
           return true;
       }else {
           return false;
       }
    }
}
