package com.rabobank.bankapplication.models;

import com.rabobank.bankapplication.services.SortingService;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private long amount;
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
