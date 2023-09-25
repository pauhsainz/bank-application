//package com.rabobank.bankapplication;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.rabobank.bankapplication.models.BankAccount;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.math.BigDecimal;
//
//
//public class BankAccountTest {
//
//        private BankAccount bankAccount;
//
//        @BeforeEach
//        public void setUp() {
//            // Create a BankAccount for testing
//            bankAccount = new BankAccount("Paul", "McCartney", "paulisdead", new BigDecimal("1000.00"), "123456789");
//        }
//
//        @Test
//        public void testDeposit() {
//            bankAccount.deposit(new BigDecimal("500.00"));
//            assertEquals(new BigDecimal("1500.00"), bankAccount.getBalance(), "Deposit amount not added correctly");
//        }
//
//        @Test
//        public void testWithdraw() {
//            bankAccount.withdraw(new BigDecimal("500.00"));
//            assertEquals(new BigDecimal("500.00"), bankAccount.getBalance(), "Withdraw amount not deducted correctly");
//        }
//
//        @Test
//        public void testTransfer() {
//            BankAccount recipientAccount = new BankAccount("Jane", "Smith", "janesmith", new BigDecimal("500.00"));
//            bankAccount.transfer(recipientAccount, new BigDecimal("300.00"), "Transfer");
//
//            assertEquals(new BigDecimal("700.00"), bankAccount.getBalance(), "Transfer amount not deducted correctly from sender");
//            assertEquals(new BigDecimal("800.00"), recipientAccount.getBalance(), "Transfer amount not added correctly to recipient");
//        }
//
//        @Test
//        public void testIsValidUsername() {
//            assertTrue(bankAccount.isValidUsername("paulisdead"), "Valid username marked as invalid");
//            assertFalse(bankAccount.isValidUsername("paul.is.dead"), "Username with invalid characters marked as valid");
//            assertFalse(bankAccount.isValidUsername(" paul is dead "), "Username with spaces marked as valid");
//        }
//}
//
//
