//package com.rabobank.bankapplication.services;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.rabobank.bankapplication.models.BankAccount;
//import com.rabobank.bankapplication.models.Transaction;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//
//public class BankAccountServiceTest {
//
//    private BankAccount bankAccount;
//
//    @BeforeEach
//    public void setUp() {
//        bankAccount = new BankAccount("Paul", "McCartney", "paulisdead", new BigDecimal("1000.00"), "123456789");
//    }
//
//    @Test
//    public void testDeposit() {
//        bankAccount.deposit(new BigDecimal("500.00"));
//        assertEquals(new BigDecimal("1500.00"), bankAccount.getBalance(), "Deposit amount added correctly");
//    }
//
//    @Test
//    public void testWithdraw() {
//        bankAccount.withdraw(new BigDecimal("500.00"));
//        assertEquals(new BigDecimal("500.00"), bankAccount.getBalance(), "Withdraw amount deducted correctly");
//    }
//
//    @Test
//    public void testTransfer() {
//        BankAccount recipientAccount = new BankAccount("John", "Lennon", "thewalrus", new BigDecimal("500.00"), "987654321");
//        bankAccount.transfer(new Transaction(new BigDecimal("300.00"), "Rent Payment", "123456789", "987654321"), recipientAccount);
//
//        assertEquals(new BigDecimal("700.00"), bankAccount.getBalance(), "Transfer amount deducted correctly from sender");
//        assertEquals(new BigDecimal("800.00"), recipientAccount.getBalance(), "Transfer amount added correctly to recipient");
//    }
//
//    @Test
//    public void testTransactionToString() {
//        Transaction transaction = new Transaction(new BigDecimal("9999"), "music", "123456789", "987654321");
//        String expected = "null: $9999.00 (music) [123456789 -> 987654321]";
//        assertEquals(expected, transaction.toString(), "Transaction toString() method is incorrect");
//    }
//}
//
//
