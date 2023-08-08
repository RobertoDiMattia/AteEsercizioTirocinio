package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.mappers.CheckingAccountMapper;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CheckingAccountServiceTest {

    @Mock
    private CheckingAccountRepository checkingAccountRepository;

    @Mock
    private CheckingAccountMapper checkingAccountMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CheckingAccountCreationRequestMapper checkingAccountCreationRequestMapper;

    @InjectMocks
    private CheckingAccountService checkingAccountService;

    @Test
    void addContoCorrente() {
    }

    @Test
    void retrieveContoCorrenteById() {
    }

    @Test
    void retrieveBalanceByIban() {
    }

    @Test
    void retrieveLastFiveTransactions() {
    }
}