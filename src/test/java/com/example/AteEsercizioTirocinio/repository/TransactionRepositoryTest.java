package com.example.AteEsercizioTirocinio.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryTest {

    @InjectMocks
    private TransactionRepository transactionRepository;

    @Test
    void findByContoCorrenteId() {
    }
}