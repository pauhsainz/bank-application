package com.rabobank.bankapplication;


import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.Transaction;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class BankingApp {

        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                // Create a BankAccount
                System.out.println("Creating a new Bank Account");
                System.out.print("Enter first name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter last name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter initial balance: ");
                BigDecimal balance = readBigDecimalInput(scanner);

                System.out.print("Enter self IBAN: ");
                String selfIBAN = scanner.nextLine();

                BankAccount bankAccount = new BankAccount(firstName, lastName, username, balance, selfIBAN);

                // Create a Transaction
                System.out.println("\nCreating a new Transaction");
                System.out.print("Enter the transaction amount: ");
                BigDecimal amount = readBigDecimalInput(scanner);

                System.out.print("Enter the transaction category: ");
                String category = scanner.nextLine();

                System.out.print("Enter the recipient's IBAN: ");
                String toIBAN = scanner.nextLine();

                Transaction transaction = new Transaction(amount, category, selfIBAN, toIBAN);

                scanner.close();

                // Perform operations with BankAccount and Transaction
                bankAccount.deposit(amount, category, selfIBAN);
                bankAccount.transfer(transaction, bankAccount);
                bankAccount.withdraw(amount, category, selfIBAN);

                // Print BankAccount and Transaction details
                System.out.println("\nBank Account Details:");
                System.out.println("First Name: " + bankAccount.getFirstName());
                System.out.println("Last Name: " + bankAccount.getLastName());
                System.out.println("Username: " + bankAccount.getUsername());
                System.out.println("Balance: " + bankAccount.getBalance());
                System.out.println("Self IBAN: " + bankAccount.getSelfIban());

                System.out.println("\nTransaction Details:");
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println("Category: " + transaction.getCategory());
                System.out.println("From IBAN: " + transaction.getFromIban());
                System.out.println("To IBAN: " + transaction.getToIban());
        }

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

}

