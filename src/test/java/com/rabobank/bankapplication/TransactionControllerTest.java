package com.rabobank.bankapplication;

import com.rabobank.bankapplication.models.Transaction;
import com.rabobank.bankapplication.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BankApplication.class)
public class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TransactionRepository transactionRepository;

    private Transaction createTransaction(long amount, String fromIban, String category) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setFromIban(fromIban);
        transaction.setDescription(category);
        transaction.setDate(LocalDateTime.now());

        return transaction;
    }
    @Test
    @WithMockUser(username = "test@test.com")
    public void getTransactions() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(createTransaction(45L, "NL01436456457577", "HM"));
        transactions.add(createTransaction(200L, "NL01436456457577", "wizzair"));

        when(transactionRepository.getTransactionsByFromIban("NL01436456457577")).thenReturn(transactions);

        mockMvc.perform(get("/transactions").param("iban", "NL01436456457577"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].fromIban", is("NL01436456457577")));
    }
}