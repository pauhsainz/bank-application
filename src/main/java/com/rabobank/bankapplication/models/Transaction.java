package com.rabobank.bankapplication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

@Entity
public class Transaction {
    private BigDecimal amount;

    @Id
    @GeneratedValue
    private Long id;
    private String category;

    private Date date;
    //FROM
    private String fromIBAN;
    //TO
    private String toIBAN;

    public Transaction(){};
    public Transaction(BigDecimal amount, String category, Date date, String fromIBAN, String toIBAN) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.fromIBAN = fromIBAN;
        this.toIBAN = toIBAN;
    }

    public String toString() {
        return String.format("%s: $%.2f (%s) [%s]", this.date, this.amount, this.category, this.fromIBAN, this.toIBAN);
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

    private static BigDecimal readBigDecimalInput(Scanner scanner) {
        BigDecimal value = null;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                String input = scanner.nextLine();
                value = new BigDecimal(input);
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }

        return value;
    }

    private static String readDateInput(Scanner scanner) {
        String value = null;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                String input = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate.parse(input, formatter);
                value = input;
                isValidInput = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd/MM/yyyy.");
            }
        }

        return value;
    }

    private static boolean checkAmountAgainstBalance(BigDecimal amount, BigDecimal balance) {
        return amount.compareTo(balance) <= 0;
    }

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

    public String getFromIBAN() {
        return fromIBAN;
    }

    public void setFromIBAN(String fromIBAN) {
        this.fromIBAN = fromIBAN;
    }

    public String getToIBAN() {
        return toIBAN;
    }

    public void setToIBAN(String toIBAN) {
        this.toIBAN = toIBAN;
    }
}
