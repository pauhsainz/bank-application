package com.rabobank.bankapplication.controllers;

import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions") // Use "api" to distinguish RESTful endpoints
public class TransactionController {

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @GetMapping("/{id}") // Use "id" as a variable
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + id));
    }
    @GetMapping(value = "/all/{iban}")
    public List<Transaction> getTransactionsByIban(@PathVariable String iban) {
        return transactionRepository.getTransactionsByFromIban(iban);
    }


    @PostMapping("/create") // Use "create" to specify the action
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        // Set the current date and time when creating the transaction
        LocalDateTime now = LocalDateTime.now();
        transaction.setDate(now);
        transaction.setId(null);
        return transactionRepository.save(transaction);
    }

    @PatchMapping("/update/{id}") // Use "update" and "id" as variables
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + id));
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setDescription(updatedTransaction.getDescription());
        return transactionRepository.save(transaction);
    }
}
