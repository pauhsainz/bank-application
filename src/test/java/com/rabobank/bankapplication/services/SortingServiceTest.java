//package com.rabobank.bankapplication.services;
//
//import com.rabobank.bankapplication.models.Transaction;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class SortingServiceTest {
//
//    @Test
//    void testCategorization() {
//        Transaction transaction = new Transaction((long) 150.76, LocalDateTime.now(),"NLAHGSD9283674983", "vintageisland");
//        System.out.println(transaction.getCategory());
//        String sortedCategory = String.valueOf(SortingService.categorize(transaction));
//        assertEquals("Clothing", sortedCategory);
//
//    }
//}
