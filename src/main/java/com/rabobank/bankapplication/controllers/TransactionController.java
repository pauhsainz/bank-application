package com.rabobank.bankapplication.controllers;
import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        Date now = new Date();
        transaction.setDate(now);
        transaction.setId(null);
        return transactionRepository.save(transaction);
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
        //PUT (PATCH??) FOR CATEGORY
        //LOOK INTO PATCH
        //create functionality for user to select new category for a mislabeled transaction
        //Try running ASAP and using postman to check whether 'CRUD' calls work
        //Finish database
        //Make sure balance, username, category re-labelling works
        //Connect front end
        //CO2 DON'T FORGET

    }
}
