package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private CheckingAccountRepository checkingAccountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionsMapper transactionsMapper;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void addTransaction() {
    }

    @Test
    void retrieveTransactionById() {
    }

    @Test
    void updateTransaction() {
    }

    @Test
    void deleteTransaction() {
    }

    @Test
    void makeDeposit() {
    }

    @Test
    void makeWithdrawl() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void getLast5transactions() {
    }
}