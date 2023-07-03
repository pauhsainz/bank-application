package com.rabobank.bankapplication;

import java.io.Console;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.models.Transaction;
        import java.io.Console;
        import java.math.BigDecimal;
        import java.util.List;
        import java.util.Scanner;
public class BankingApp {
//    public static void main(String[] args) {
//        Console console = System.console();
//        if (console == null) {
//            System.out.println("Couldn't get Console instance");
//            System.exit(0);
//        }
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to the Rabobank!");
//        System.out.print("Enter your first name: ");
//        String firstName = console.readLine();
//        System.out.print("Enter your last name: ");
//        String lastName = console.readLine();
//        System.out.print("Enter your username: ");
//        String username = console.readLine();
//        console.readPassword("Enter your password:");
//        System.out.print("Enter your starting balance: ");
//        BigDecimal balance = scanner.nextBigDecimal();
//
//        BankAccount account = new BankAccount(firstName, lastName, username, balance);
//
//        while (true) {
//            int choice = getChoice(scanner);
//
//            switch (choice) {
//                case 1 -> deposit(scanner, account);
//                case 2 -> withdraw(scanner, account);
//                case 3 -> transfer(scanner, lastName, account);
//                case 4 -> account.printTransactionHistory();
//                case 5 -> {
//                    System.out.println("Thank you for using Rabobank");
//                    return;
//                }
//                default -> System.out.println("Invalid choice");
//            }
//        }
//    }
//
//    private static int getChoice(Scanner scanner) {
//        System.out.println();
//        System.out.println("What would you like to do?");
//        System.out.println("1. Deposit");
//        System.out.println("2. Withdraw");
//        System.out.println("3. Transfer");
//        System.out.println("4. View transaction history");
//        System.out.println("5. Quit");
//        System.out.print("Enter your choice (1-5): ");
//        int choice = scanner.nextInt();
//        return choice;
//    }
//
//    private static void transfer(Scanner scanner, String lastName, BankAccount account) {
//        System.out.print("Enter name of recipient: ");
//        String recipientName = scanner.next();
//        System.out.print("Enter amount to transfer: ");
//        BigDecimal transferAmount = scanner.nextBigDecimal();
//        scanner.nextLine();
//        String transferCategory = getCategory();
//        BankAccount recipient = new BankAccount(recipientName, lastName, lastName, new BigDecimal(1235));
//        account.transfer(recipient, transferAmount, transferCategory);
//    }
//
//
//    private static void withdraw(final Scanner scanner, final BankAccount account) {
//        System.out.print("Enter amount to withdraw: ");
//        final BigDecimal withdrawAmount = scanner.nextBigDecimal();
//        scanner.nextLine();
//        String withdrawCategory = getCategory();
//        account.withdraw(withdrawAmount, withdrawCategory);
//    }
//
//    private static void deposit(final Scanner scanner, final BankAccount account) {
//        System.out.print("Enter amount to deposit: ");
//        final BigDecimal depositAmount = scanner.nextBigDecimal();
//        scanner.nextLine();
//        String depositCategory = getCategory();
//        account.deposit(depositAmount, depositCategory);
//        System.out.printf("$%.2f deposited.%n", depositAmount);
//    }
//
//
}
