package com.rabobank.bankapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import com.rabobank.bankapplication.utils.IBANUtil;
import java.math.BigDecimal;
import java.util.Scanner;

@Entity
public class BankAccount {
    private String firstName;
    private String lastName;

    private String username;
    //Checker for valid -- not too long, no spaces, no weird characters (NO weird UTF-8) (ONLY a-z, caps, 0-9, _=? [chars on keyboard] etc)
    private BigDecimal balance;
    @Id
    public String selfIban;
    //IBAN CHECK
        //CREATE UTIL CLASS (IBAN GENERATOR / VALIDATOR)
        //quick & dirty instant call -- READMORE
            //singleton -- look into (not nesc. use it but, do learn about it)
        //implementation to experiment with
    //TEST CASES!!!!

    public BankAccount(String firstName, String lastName, String username, BigDecimal balance) {

    }

    public BankAccount(String firstName, String lastName, String username, BigDecimal balance, String selfIban) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.balance = balance;
        this.selfIban = IBANUtil.generateIBAN();
    }

    public void deposit(BigDecimal amount, String category, String selfIBAN) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount, String category, String selfIBAN) {
        if (isBalanceSufficient(amount)) {
            this.balance = this.balance.subtract(amount);
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
        transaction.setFromIban(selfIBAN);

    }


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

    public void transfer(Transaction transaction, BankAccount bankAccount) {
    }
    public String getSelfIban() {
        return selfIban;
    }

    // Make the setter for selfIban private to prevent modification after it's set during object creation.
    private void setSelfIban(String selfIban) {
        this.selfIban = selfIban;
    }
}

