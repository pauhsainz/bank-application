package com.rabobank.bankapplication.controllers;

import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import com.rabobank.bankapplication.repositories.UserRepository;
import com.rabobank.bankapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;
    private UserService userService;
    public TransactionController(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    @GetMapping
    public List<Transaction> getTransactions(@RequestParam String iban) {
        return transactionRepository.getTransactionsByFromIban(iban);
    }


    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        LocalDateTime now = LocalDateTime.now();
        transaction.setDate(now);
        transaction.setId(null);
        return transactionRepository.save(transaction);
    }

    @PostMapping("/transactions")
    void addUser(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @PatchMapping("/{id}/category")
    public Transaction patchTransactionCategory(@PathVariable Long id, @RequestBody String category) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setCategory(category);
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction with ID " + id + " not found. Please try again.");
        }
    }
}
