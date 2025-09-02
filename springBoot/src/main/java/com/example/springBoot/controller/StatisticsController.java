package com.example.springBoot.controller;

import com.example.springBoot.dto.StatisticsResponse;
import com.example.springBoot.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<StatisticsResponse> getStatistics(){
        DoubleSummaryStatistics statistics =this.transactionService.getStatistics();
        return ResponseEntity.ok(new StatisticsResponse(statistics));
    }
}
