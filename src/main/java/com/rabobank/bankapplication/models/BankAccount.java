package com.rabobank.bankapplication.models;

import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Data
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
        transactionArrayList.addAll(incomingTransactions);
        transactionArrayList.addAll(outgoingTransactions);
        transactionArrayList.sort(Comparator.comparing(Transaction::getDate));
        return transactionArrayList;
    }
}
