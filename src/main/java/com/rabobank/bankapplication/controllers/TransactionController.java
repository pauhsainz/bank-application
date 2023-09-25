package com.rabobank.bankapplication.controllers;

import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import com.rabobank.bankapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    public TransactionController(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/transactions")
    public List<Transaction> getTransactions(@RequestParam String toIban) {
        return transactionRepository.getTransactionsByFromIban(toIban);
    }

    public List<Transaction> getTransactionsByIban(String email, String iban) {
        Optional<User> loggedinUser = userRepository.getUserByEmail(email);
        if (loggedinUser.isEmpty()) {
            throw new IllegalArgumentException("No customer with such email: " + email);
        }
        boolean bankAccountOwner = loggedinUser.get().getBankAccount().stream().anyMatch(bankAccount -> iban.equals(bankAccount.getIban()));
        if (!bankAccountOwner) {
            throw new IllegalStateException("Current user does not have bank account with such IBAN:" + iban);
        }
        return transactionRepository
                .findAll()
                .stream()
                .filter(transaction -> transaction.getFromIban().equals(iban))
                .collect(Collectors.toList());
    }
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        Date now = new Date();
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
