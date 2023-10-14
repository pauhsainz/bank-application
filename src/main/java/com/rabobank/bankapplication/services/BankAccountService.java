package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.BankAccount;
import java.util.List;

public interface BankAccountService {
    BankAccount createBankAccount(BankAccount newBankAccount);
    List<BankAccount> getAllBankAccounts();
    BankAccount getBankAccountByIban(String iban);
    double calculateBalance(String iban);
}


