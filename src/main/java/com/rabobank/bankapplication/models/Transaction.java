package com.rabobank.bankapplication.models;

import com.rabobank.bankapplication.services.SortingService;
import jakarta.persistence.*;
import java.util.Date;
import static com.rabobank.bankapplication.services.SortingService.Category.*;


@Entity
public class Transaction {
    private long amount;

    @Id
    @GeneratedValue
    private Long id;
    private String category;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "from_bank_account_id")
    private BankAccount from;
    @ManyToOne
    @JoinColumn(name = "to_bank_account_id")
    private BankAccount to;
    private String fromIban;

    private String toIban;

    public Transaction() {
        this.date = new Date();
    }

    public Transaction(long amount, String category, String fromIban, String toIban) {
        this.amount = amount;
        this.category = category;
        this.date = new Date();
        this.fromIban = fromIban;
        this.toIban = toIban;
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

    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCategory(String category) {
    }

    public void setBankAccount(BankAccount bankAccount) {
    }

    public BankAccount getFrom() {
        return from;
    }

    public void setFrom(BankAccount from) {
        this.from = from;
    }

    public BankAccount getTo() {
        return to;
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
