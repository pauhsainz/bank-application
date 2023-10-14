package com.rabobank.bankapplication.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Data
public class BankAccount {
    @Id
    private String iban;
    private long balance;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "from")
    private List<Transaction> outgoingTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "to")
    private List<Transaction> incomingTransactions = new ArrayList<>();
    @Transient
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionArrayList = new ArrayList<>();
        transactionArrayList.addAll(incomingTransactions);
        transactionArrayList.addAll(outgoingTransactions);
        transactionArrayList.sort(Comparator.comparing(Transaction::getDate));
        return transactionArrayList;
    }


}
