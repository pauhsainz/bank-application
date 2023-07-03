package com.rabobank.bankapplication.controllers;


import java.math.BigDecimal;
import java.util.List;

import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.repositories.BankAccountRepository;

public class BankAccountController {
    private BankAccountRepository bankAccountRepository;

//    public BankAccount createBankAccount(String firstName, String lastName, String username, BigDecimal balance) {
//        BankAccount bankAccount = new BankAccount(firstName, lastName, username, balance);
//        return bankAccountRepository.save(bankAccount);
//    }
//
////    public BankAccount getBankAccountByUsername(String username) {
////        return bankAccountRepository.findById(username);
////    }
//
//    public List<BankAccount> getAllBankAccounts() {
//        return bankAccountRepository.findAll();
//    }
//
//    public BankAccount updateBankAccount(BankAccount bankAccount) {
//        return bankAccountRepository.update(bankAccount);
//    }
//
//    public void deleteBankAccount(BankAccount bankAccount) {
//        bankAccountRepository.delete(bankAccount);
//    }
//
//    public void deleteBankAccountByUsername(String username) {
//        bankAccountRepository.deleteById(username);
//    }
}
