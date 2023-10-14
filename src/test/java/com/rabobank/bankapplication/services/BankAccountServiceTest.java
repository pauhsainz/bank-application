package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.repositories.BankAccountRepository;
import com.rabobank.bankapplication.services.BankAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest{

   @InjectMocks
    private BankAccountServiceImpl bankAccountService;
   @Mock   //@Spy
    private BankAccountRepository bankAccountRepository;


    public Transaction createTransaction(String fromIban, String toIban, long amount) {
        Transaction transaction = new Transaction();
        transaction.setFromIban(fromIban);
        transaction.setToIban(toIban);
        transaction.setAmount(amount);
        // Set any other properties if necessary.
        return transaction;
    }

    @Test
    public void testCalculateBalance() {
        String iban = "TEST1234567890";
        BankAccount testAccount = new BankAccount();
// TODO       BankAccount testAccount = mock(BankAccount.class);
        testAccount.setIban(iban);
        testAccount.setBalance(1000);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(createTransaction(iban, "TO123", 500));
        transactions.add(createTransaction("FROM456", iban, 200));
        transactions.add(createTransaction(iban, "TO789", 300));

        // Mock the behavior of the repository to return the test account and transactions
        when(bankAccountRepository.findById(iban)).thenReturn(Optional.of(testAccount));

        // Calculate the balance
        double balance = bankAccountService.calculateBalance(iban);

        // Expected balance calculation: 1000 (initial balance) + 300 (incoming) - 500 (outgoing) + 200 (incoming) = 1000
        assertEquals(1000, balance, 0.01);
    }
}

