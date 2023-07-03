package com.rabobank.bankapplication.controllers;
import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        Date now = new  Date();
        transaction.setDate(now);
        transaction.setId(null);
        return transactionRepository.save(transaction);
    }
}