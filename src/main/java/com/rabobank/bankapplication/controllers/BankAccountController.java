package com.rabobank.bankapplication.controllers;


import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class BankAccountController {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createBankAccount(String firstName, String lastName, String username, BigDecimal balance) {
        BankAccount bankAccount = new BankAccount(firstName, lastName, username, balance);
        return bankAccountRepository.save(bankAccount);
    }
    public BankAccount getBankAccountByselfIban(String selfIban) {
        return bankAccountRepository.findById(selfIban).orElseThrow(()->new RuntimeException("No IBAN found, try again"));
    }

    public BankAccount updateBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
    //Not update bankAccount, instead the list of associated transactions --> calculate balance on the fly from retrieved transactions
    //update their last-name
    //Create user object, makes use of BA and needs to be retrieved based on username (which is associated with a set of IBAN, which is assc. with transactions)

    // Get all Bank Accounts
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }
}
