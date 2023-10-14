package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import com.rabobank.bankapplication.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Transaction> getTransactionsByIban(String email, String iban) {
        Optional<User> loggedinCustomer = userRepository.getUserByEmail(email);
        if (loggedinCustomer.isEmpty()) {
            throw new IllegalArgumentException("No customer with such email: " + email);
        }
        boolean bankAccountOwner = loggedinCustomer.get().getBankAccounts().stream().anyMatch(bankAccount -> iban.equals(bankAccount.getIban()));
        if (!bankAccountOwner) {
            throw new IllegalStateException("Current user does not have bank account with such IBAN:" + iban);
        }
        return transactionRepository
                .findAll()
                .stream()
                .filter(transaction -> transaction.getToIban().equals(iban))
                .collect(Collectors.toList());
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction updateTransactionCategory(Long id, String category) {
        return null;
    }
}
