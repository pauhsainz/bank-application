package com.rabobank.bankapplication.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BankAccount {
    private String firstName;
    private String lastName;
    @Id
    private String username;
    private BigDecimal balance;

    public BankAccount() {

    }

    public String getSelfIBAN() {
        return selfIBAN;
    }

    public void setSelfIBAN(String selfIBAN) {
        this.selfIBAN = selfIBAN;
    }

    public String selfIBAN;
    //IBAN CHECK
    //TEST CASES!!!!

    public BankAccount(String firstName, String lastName, String username, BigDecimal balance, String selfIBAN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.balance = balance;
        this.selfIBAN=selfIBAN;
    }

    public void deposit(BigDecimal amount, String category, String selfIBAN) {
        addToBalance(amount);
        addTransaction(amount, category, selfIBAN);
    }

    public void withdraw(BigDecimal amount, String category) {
        if (isBalanceSufficient(amount)) {
            subtractFromBalance(amount);
            addTransaction(amount, "Withdrawal", category);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void transfer(BankAccount otherAccount, BigDecimal amount, String category) {
        if (isBalanceSufficient(amount)) {
            subtractFromBalance(amount);
            otherAccount.addToBalance(amount);
            addTransaction(amount, "Transfer to " + otherAccount.username, category);
            otherAccount.addTransaction(amount, "Received transfer from " + this.username, category);
        } else {
            System.out.println("Insufficient balance");
        }
    }
    private void addToBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    private void subtractFromBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    private boolean isBalanceSufficient(BigDecimal amount) {
        return this.balance.compareTo(amount) >= 0;
    }

    private void addTransaction(BigDecimal amount, String category, String selfIBAN) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCategory(category);
        transaction.setFromIBAN(selfIBAN);

    }

//    public static BankAccount generateSampleBankAccount() {
//    }

//    public static BankAccount createBankAccountFromUserInput() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter first name: ");
//        String firstName = scanner.nextLine();
//
//        System.out.print("Enter last name: ");
//        String lastName = scanner.nextLine();
//
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//
//        BigDecimal balance = readBigDecimalInput("Enter initial balance: ");
//
//        return new BankAccount(firstName, lastName, username, balance);
//    }

    private static BigDecimal readBigDecimalInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal value = null;

        while (value == null) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                value = new BigDecimal(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }

        return value;
    }
//    public static void main(String[] args) {
//        BankAccount sampleAccount = generateSampleBankAccount();
//        System.out.println("Sample Bank Account:");
//        System.out.println("Username: " + sampleAccount.username);
//        System.out.println("Balance: " + sampleAccount.balance);
//
//        BankAccount newAccount = createBankAccountFromUserInput();
//        System.out.println("New Bank Account:");
//        System.out.println("Username: " + newAccount.username);
//        System.out.println("Balance: " + newAccount.balance);
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}

