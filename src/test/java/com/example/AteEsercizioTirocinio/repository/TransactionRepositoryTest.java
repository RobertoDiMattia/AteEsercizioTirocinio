package com.example.AteEsercizioTirocinio.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryTest {

    //annotazione DataJapaTest su un istanza di DB (H2)
    // testi che quando li find se c'è un elemento torna questo, altrimenti optional vuoto

    @InjectMocks
    private TransactionRepository transactionRepository;

    @Test
    void findByContoCorrenteId() {
    }
}