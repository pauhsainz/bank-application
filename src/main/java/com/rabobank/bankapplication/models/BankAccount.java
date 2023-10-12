package com.rabobank.bankapplication.models;

import jakarta.persistence.*;
import com.rabobank.bankapplication.utils.IBANUtil;
import lombok.*;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//POJO (plain ol' java object)
//LOMBOK -- annotate to get instant getters & setters
//JAVA RECORDS
@Entity
public class BankAccount {
    private long balance;
    @Id
    public String iban;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToMany(mappedBy = "from")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "to")
    private List<Transaction> incomingTransactions;

    public BankAccount() {

    }
    public BankAccount(User user, String iban) {
        this.user = user;
        this.iban = iban;
    }

    @Transient
    public List<Transaction> getAllTransactions() {
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        transactionArrayList.add((Transaction) incomingTransactions);
        transactionArrayList.add((Transaction) outgoingTransactions);
        transactionArrayList.sort(Comparator.comparing(Transaction::getDate));
        return transactionArrayList;
    }
    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    public void setOutgoingTransactions(List<Transaction> outgoingTransactions) {
        this.outgoingTransactions = outgoingTransactions;
    }

    public List<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setIncomingTransactions(List<Transaction> incomingTransactions) {
        this.incomingTransactions = incomingTransactions;
    }
}

