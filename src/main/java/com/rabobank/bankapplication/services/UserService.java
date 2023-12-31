//package com.rabobank.bankapplication.services;
//
//import com.rabobank.bankapplication.models.Transaction;
//import com.rabobank.bankapplication.models.User;
//import com.rabobank.bankapplication.repositories.UserRepository;
//import com.rabobank.bankapplication.repositories.TransactionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final TransactionRepository transactionRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository, TransactionRepository transactionRepository) {
//        this.userRepository = userRepository;
//        this.transactionRepository = transactionRepository;
//    }
//
//    public List<Transaction> getTransactionsByIban(String email, String iban) {
//        Optional<User> loggedinUser = userRepository.getUserByEmail(email);
//        if (loggedinUser.isEmpty()) {
//            throw new IllegalArgumentException("No customer with such email: " + email);
//        }
//
//        User user = loggedinUser.get();
//        boolean bankAccountOwner = user.getBankAccounts().stream().anyMatch(bankAccount -> iban.equals(bankAccount.getIban()));
//
//        if (!bankAccountOwner) {
//            throw new IllegalStateException("Current user does not have a bank account with such IBAN: " + iban);
//        }
//
//        return transactionRepository
//                .findAll()
//                .stream()
//                .filter(transaction -> transaction.getFromIban().equals(iban))
//                .collect(Collectors.toList());
//    }
//
//}
