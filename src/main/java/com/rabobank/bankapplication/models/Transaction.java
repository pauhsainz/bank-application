package com.rabobank.bankapplication.models;

import com.rabobank.bankapplication.services.SortingService;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
public class Transaction {
    private long amount;

    @Id
    @GeneratedValue
    private Long id;
    private String category;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "from_bank_account_id")
    private BankAccount from;
    @ManyToOne
    @JoinColumn(name = "to_bank_account_id")
    private BankAccount to;
    private String fromIban;

    private String toIban;

    public Transaction(){};

    public Transaction(long amount, LocalDateTime date, String fromIban, String category) {
        this.amount = amount;
        this.date = date;
        this.fromIban = fromIban;
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.from = bankAccount;
    }
    public void setTo(BankAccount to) {
        this.to = to;
    }

    public String getFromIban() {
        return fromIban;
    }

    public void setFromIban(String fromIban) {
        this.fromIban = fromIban;
    }

    public void setFrom(BankAccount from) {
        this.from = from;
    }

    public String getToIban() {
        return toIban;
    }

    public void setToIban(String toIban) {
        this.toIban = toIban;
    }


    public long getCo2Emission() {
        SortingService.Category categoryEnum = SortingService.categorize(this);
        long co2Emission = 0;

        switch (categoryEnum) {
            case Furniture, General, DrugStores:
                co2Emission = amount * 105;
                break;
            case Energy:
                co2Emission = amount * 604;
                break;
            case Clothing:
                co2Emission = amount * 1143;
                break;
            case Flights:
                co2Emission = amount * 1572;
                break;
            case Groceries, Restaurant:
                co2Emission = amount * 802;
                break;
            case PublicTransport:
                co2Emission = amount * 83;
                break;
            case Trains:
                co2Emission = amount * 14;
                break;
            default:
                co2Emission = 0;
        }

        return co2Emission;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "fromIban='" + fromIban + '\'' +
                ", toIban='" + toIban + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + date +
                '}';
    }
}
