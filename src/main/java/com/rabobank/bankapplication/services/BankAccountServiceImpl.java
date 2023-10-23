package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.repositories.BankAccountRepository;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
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
        List<Transaction> outgoingTransactions = transactionRepository.getTransactionsByFromIban(iban);
        List<Transaction> incomingTransactions = transactionRepository.getTransactionsByToIban(iban);
        double balance = bankAccount.getBalance();

        for (Transaction outgoingTransaction : outgoingTransactions) {
            balance -= outgoingTransaction.getAmount();
        }
        for (Transaction incomingTransaction : incomingTransactions) {
            balance += incomingTransaction.getAmount();
        }
        return balance;
    }
}


