package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.service.CheckingAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CheckingAccountControllerTest {

    @Mock
    private CheckingAccountService checkingAccountService;

    @InjectMocks
    private CheckingAccountController checkingAccountController;

    @Test
    void addContoCorrente() {
    }

    @Test
    void retrieveContoCorrenteById() {
    }

    @Test
    void getBalance() {
    }

    @Test
    void getLast5Transactions() {
    }
}