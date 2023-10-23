package com.rabobank.bankapplication.services;

import com.rabobank.bankapplication.models.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortingServiceTest {

    @Test
    void testCategorization() {
        Transaction transaction = new Transaction();
        transaction.setFromIban("NLAHGSD9283674983");
        transaction.setAmount((long) 150.76);
        transaction.setDate(LocalDateTime.now());
        transaction.setDescription("vintageisland");
        String sortedCategory = String.valueOf(SortingService.categorize(transaction));
        assertEquals("Clothing", sortedCategory);

    }
}
