package com.rabobank.bankapplication.controllers;


import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.User;
import com.rabobank.bankapplication.repositories.BankAccountRepository;
import com.rabobank.bankapplication.utils.IBANUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankAccountController {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @PostMapping("/bank-accounts")
    public BankAccount createBankAccount(User user, String iban) {
        BankAccount bankAccount = new BankAccount(user, iban);
        return bankAccountRepository.save(bankAccount);
    }

    @GetMapping("/bank-accounts")
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }
    //TODO get all Bank Accounts
//    @GetMapping("/accounts/{userId}")
//    public List<BankAccount> getUserAccounts(@PathVariable Long userId) {
//        User user = userRepository.findById(userId).orElse(null);
//        String email = user.getEmail();
//        if (user != null) {
//            return bankAccountRepository.getBankAccountByEmail(email);
//        }
//        throw new IllegalArgumentException("No customer with such email: " + email);
//    }
    public BankAccount updateBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
    //TODO Not update bankAccount, instead the list of associated transactions --> calculate balance on the fly from retrieved transactions
    //TODO update their last-name
    //TODO Create user object, makes use of BA and needs to be retrieved based on username (which is associated with a set of IBAN, which is assc. with transactions)

}
