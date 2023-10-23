package com.rabobank.bankapplication.controllers;

import org.springframework.test.web.servlet.ResultActions;
import com.rabobank.bankapplication.controllers.BankAccountController;
import com.rabobank.bankapplication.models.BankAccount;
import com.rabobank.bankapplication.services.BankAccountService;
import com.rabobank.bankapplication.utils.IBANUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;



class BankAccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BankAccountService bankAccountService;

    private BankAccountController bankAccountController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        bankAccountController = new BankAccountController(bankAccountService);
        mockMvc = MockMvcBuilders.standaloneSetup(bankAccountController).build();
    }

    @Test
    void testCreateBankAccount() throws Exception {
        // Mock the IBAN generation
        String generatedIban = IBANUtil.generateIBAN();
        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setIban(generatedIban);

        // Mock the service to return the new bank account
        when(bankAccountService.createBankAccount(Mockito.any(BankAccount.class))).thenReturn(newBankAccount);

        // Perform a POST request to create a new bank account
        ResultActions resultActions = mockMvc.perform(post("/bank-accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Add assertions to check the response content
        resultActions.andExpect(jsonPath("$.iban", is(generatedIban)));

        // You can add more assertions to check other fields in the response as needed.
    }
}
