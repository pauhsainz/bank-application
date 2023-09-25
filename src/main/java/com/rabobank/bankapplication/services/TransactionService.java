package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByIban(String userEmail, String iban);
}
