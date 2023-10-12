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
import java.util.Arrays;

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

    @Test
    @WithMockUser(username = "test@test.com")
    public void getTransactions() throws Exception {
        when(transactionRepository.getTransactionsByFromIban("NL01436456457577"))
                .thenReturn(Arrays.asList(
                        new Transaction(45L,LocalDateTime.now(), "NL01436456457577", "HM"),
                        new Transaction(200L, LocalDateTime.now(), "NL01436456457577", "wizzair")
                ));

        mockMvc.perform(get("/transactions").param("iban", "NL01436456457577"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].fromIban", is("NL01436456457577")));
    }


    @Test
    public void postTransactions() throws Exception {

    }

}