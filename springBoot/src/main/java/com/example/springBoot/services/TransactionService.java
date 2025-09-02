package com.example.springBoot.services;


import com.example.springBoot.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {
    private final Queue<Transaction> transactions=new ConcurrentLinkedQueue<Transaction>();


    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public void clearTransaction(){
        this.transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics(){
        OffsetDateTime now=OffsetDateTime.now();
        return transactions.stream().filter(t->t.getDatahora().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor).summaryStatistics();
    }
}


