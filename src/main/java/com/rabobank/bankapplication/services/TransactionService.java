package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByIban(String email, String iban);
    Transaction getTransactionById(Long id);
    Transaction createTransaction(Transaction transaction);
    Transaction updateTransactionCategory(Long id, String category);
}
