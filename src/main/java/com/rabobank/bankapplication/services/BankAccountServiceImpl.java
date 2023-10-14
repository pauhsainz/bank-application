package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount createBankAccount(BankAccount newBankAccount) {
        return null;
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getBankAccountByIban(String iban) {
        return bankAccountRepository.findById(iban)
                .orElseThrow(() -> new IllegalArgumentException("Bank Account not found with IBAN: " + iban));
    }
    @Override
    public double calculateBalance(String iban) {
        BankAccount bankAccount = bankAccountRepository.findById(iban)
                .orElseThrow(() -> new IllegalArgumentException("Bank Account not found with IBAN: " + iban));

        // Calculate the balance based on transactions
        List<Transaction> transactions = bankAccount.getAllTransactions();
        double balance = bankAccount.getBalance();

        for (Transaction transaction : transactions) {
            if (transaction.getFromIban().equals(iban)) {
                // Subtract the amount from the balance if it's an outgoing transaction
                balance -= transaction.getAmount();
            } else if (transaction.getToIban().equals(iban)) {
                // Add the amount to the balance if it's an incoming transaction
                balance += transaction.getAmount();
            }
        }

        return balance;
    }
}


