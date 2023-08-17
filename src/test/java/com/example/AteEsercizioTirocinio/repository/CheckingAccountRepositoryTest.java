package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CheckingAccountRepositoryTest {

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Test
    void testFindLastFiveTransactions() {
        var checkingAccount = mockedCheckingAccount();
        checkingAccountRepository.save(checkingAccount);

        List<TransactionDto> foundTransactions = checkingAccountRepository.findLastFiveTransactions(checkingAccount.getId());

        assertThat(foundTransactions).isEmpty();
    }

    private CheckingAccount mockedCheckingAccount() {
        return CheckingAccount.builder()
                .id(1L)
                .iban("IT12345678910")
                .transactions(Collections.emptyList())
                .balance(1000)
                .build();
    }
}