package com.rabobank.bankapplication.controllers;

import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.services.BankAccountService;
import com.rabobank.bankapplication.utils.IBANUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    @Autowired
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping("/create")
    public BankAccount createBankAccount() {
        String generatedIban = IBANUtil.generateIBAN();
        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setIban(generatedIban);
        return bankAccountService.createBankAccount(newBankAccount);
    }

    @GetMapping("/all")
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{iban}")
    public BankAccount getBankAccountByIban(@PathVariable String iban) {
        return bankAccountService.getBankAccountByIban(iban);
    }
    @GetMapping("/{user}")
    public BankAccount getBankAccountByUser(@PathVariable String user) {
        return bankAccountService.getBankAccountByIban(user);
    }
}
