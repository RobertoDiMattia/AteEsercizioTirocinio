package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.model.Transaction;
import com.example.AteEsercizioTirocinio.service.TransactionService;
import com.example.AteEsercizioTirocinio.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.example.AteEsercizioTirocinio.model.Transaction.Type.DEPOSIT;
import static com.example.AteEsercizioTirocinio.model.Transaction.Type.WITHDRAWAL;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
//@Import(TestJacksonConfig.class)
//@Import(TestUtils.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    private static final Long EXISTING_CHECKING_ACCOUNT_ID = 5L;
    private static final double AMOUNT_DEPOSIT = 1500.0;
    private static final double AMOUNT_WITHDRAWAL = 390.0;
    private static final Long EXISTING_TRANSACTION_ID = 123L;
    private static final Long EXISTING_CHECHING_ACCOUNT_ID = 59L;

    @Test
    void testRetrieveTransactionByCheckingAccountId() throws Exception {
        var transactionsDto = Arrays.asList(
                mockedTransactionDto(DEPOSIT, AMOUNT_DEPOSIT),
                mockedTransactionDto(WITHDRAWAL, AMOUNT_WITHDRAWAL)
        );

        when(transactionService.retrieveTransactionByCheckingAccountId(anyLong())).thenReturn(transactionsDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions/{checkingAccount}", EXISTING_CHECKING_ACCOUNT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.json(transactionsDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amount").value(AMOUNT_DEPOSIT))
                .andExpect(jsonPath("$[0].id").value(EXISTING_TRANSACTION_ID))
                .andExpect(jsonPath("$[1].amount").value(AMOUNT_WITHDRAWAL));

    }

//    @Test
//    void testMakeDeposit() throws Exception {
//        var transactionDto = mockedTransactionDtoDeposit(anyDouble());
//
//        when(transactionService.makeDeposit(anyLong(), anyDouble())).thenReturn(transactionDto);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions/deposit")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(TestUtils.json(transactionDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().json(TestUtils.json(transactionDto)));
//    }

//    @Test
//    void testMakeWithdrawal() throws Exception {
//        var transactionDto = mockedTransactionDtoWithdrawal(anyDouble());

//        when(transactionService.makeWithdrawal(anyLong(), anyDouble())).thenReturn(transactionDto);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions/withdrawal")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(TestUtils.json(transactionDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().json(TestUtils.json(transactionDto)));
//    }

    private TransactionDto mockedTransactionDto(Transaction.Type type, double amount) {
        return TransactionDto.builder()
                .id(EXISTING_TRANSACTION_ID)
                .type(type)
                .checkingAccountId(EXISTING_CHECHING_ACCOUNT_ID)
                .dateTime(LocalDateTime.now())
                .amount(amount)
                .build();
    }
}