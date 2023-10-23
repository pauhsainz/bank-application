package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.services.SortingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class TransactionTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testGetCo2Emission() {
        // Create a test transaction with specific category and amount
        Transaction transaction = new Transaction();
        transaction.setAmount(100); // Set the desired amount
        transaction.setDescription("Test");
        transaction.setCategory(SortingService.Category.Furniture); // Set a category

        long expectedCo2Emission = 100 * 105; // Expected emission based on the category and amount

        assertEquals(expectedCo2Emission, transaction.getCo2Emission());
    }

    @Test
    public void testToString() {
        // Create a test transaction with specific values
        Transaction transaction = new Transaction();
        transaction.setFromIban("fromIban");
        transaction.setDescription("Test Description");
        transaction.setAmount(100);
        transaction.setDate(LocalDateTime.now());

        String expectedString = "Transaction{" +
                "fromIban='fromIban', " +
                "description='Test Description', " +
                "amount=100, " +
                "transactionDate=" + transaction.getDate() +
                "}";

        assertEquals(expectedString, transaction.toString());
    }
}

