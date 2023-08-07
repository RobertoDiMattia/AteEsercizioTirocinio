package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void retrieveTransactionById() {
    }

    @Test
    void makeDeposit() {
    }

    @Test
    void makeWithdrawal() {
    }
}