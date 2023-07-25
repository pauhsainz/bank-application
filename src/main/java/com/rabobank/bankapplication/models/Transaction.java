package com.rabobank.bankapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {
    private BigDecimal amount;

    @Id
    @GeneratedValue
    private Long id;
    private String category;

    private Date date;
    //FROM
    private String fromIban;
    //TO
    private String toIban;

    public Transaction(){};
    public Transaction(BigDecimal amount, String category, String fromIban, String toIban) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.fromIban = fromIban;
        this.toIban = toIban;
    }

    public String toString() {
        return String.format("%s: $%.2f (%s) [%s]", this.date, this.amount, this.category, this.fromIban, this.toIban);
    }

//    public static void main(String[] args) {
//        // Create a sample transaction
//        Transaction transaction = createTransactionFromUserInput();
//        System.out.println("Transaction Details:");
//        System.out.println(transaction.toString());
//
//        // Check if the amount is less than the balance
//        BigDecimal balance = new BigDecimal("1000"); // Assuming the balance is 1000 for demonstration purposes
//        boolean isAmountValid = checkAmountAgainstBalance(transaction.getAmount(), balance);
//        if (!isAmountValid) {
//            System.out.println("Amount exceeds the balance.");
//        }
//    }

//    public static Transaction createTransactionFromUserInput() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter the transaction amount: ");
//        BigDecimal amount = readBigDecimalInput(scanner);
//
//        System.out.print("Enter the transaction category: ");
//        String category = scanner.nextLine();
//
//        System.out.print("Enter the transaction date (dd/MM/yyyy): ");
//        String date = readDateInput(scanner);
//
//        System.out.print("Enter the recipient's IBAN ");
//        String toIBAN = readDateInput(scanner);
//
//        scanner.close();
//
//
//        return new Transaction(amount, category, date, fromIBAN, toIBAN);
//    }


    public String getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromIban() {
        return fromIban;
    }

    public void setFromIban(String fromIBAN) {
        this.fromIban = fromIBAN;
    }

    public String getToIban() {
        return toIban;
    }

    public void setToIban(String toIBAN) {
        this.toIban = toIBAN;
    }
}
