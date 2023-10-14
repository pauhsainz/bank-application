package com.rabobank.bankapplication.controllers;

import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + id));
    }

    @PostMapping("/{id}")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        // Set the current date and time when creating the transaction
        LocalDateTime now = LocalDateTime.now();
        transaction.setDate(now);
        transaction.setId(null);
        return transactionRepository.save(transaction);
    }

    @PatchMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction updatedTransaction) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with ID: " + id));
        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setDescription(updatedTransaction.getDescription());
        return transactionRepository.save(transaction);
    }
}

