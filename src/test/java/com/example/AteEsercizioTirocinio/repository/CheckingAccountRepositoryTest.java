package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import com.example.AteEsercizioTirocinio.model.Transaction;
import com.example.AteEsercizioTirocinio.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CheckingAccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Test
    @Transactional
    public void testFindLastFiveTransactions() {

        User managedUser = entityManager.merge(mockedUser());
        CheckingAccount checkingAccount = entityManager.merge(mockedCheckingAccount(managedUser));
        checkingAccount.setTransactions(new ArrayList<>());
        for (Transaction t : mockedTransactions()) {
            Transaction saved = entityManager.merge(t);
            checkingAccount.getTransactions().add(saved);
        }

        checkingAccount = entityManager.merge(checkingAccount);

        List<Transaction> transactions = checkingAccountRepository.findLastFiveTransactions(checkingAccount.getId());

        assertThat(transactions).hasSize(5);
    }

    private User mockedUser() {
        return User.builder()
                .id(99L)
                .firstName("rob")
                .lastName("dima")
                .email("rob@gmail.com")
                .build();
    }

    private CheckingAccount mockedCheckingAccount(User user) {
        return CheckingAccount.builder()
                .id(25L)
                .user(user)
                .iban("IBAN12345678910")
                .balance(1000.0)
                .build();
    }

    private List<Transaction> mockedTransactions() {
        var transaction1 = Transaction.builder()
                .id(1L)
                .dateTime(LocalDateTime.now())
                .amount(500)
                .type(Transaction.Type.DEPOSIT)
                .build();
        var transaction2 = Transaction.builder()
                .id(2L)
                .dateTime(LocalDateTime.now())
                .type(Transaction.Type.DEPOSIT)
                .amount(450)
                .build();
        var transaction3 = Transaction.builder()
                .id(3L)
                .dateTime(LocalDateTime.now())
                .amount(400)
                .type(Transaction.Type.DEPOSIT)
                .build();
        var transaction4 = Transaction.builder()
                .id(4L)
                .dateTime(LocalDateTime.now())
                .amount(250)
                .type(Transaction.Type.WITHDRAWAL)
                .build();
        var transaction5 = Transaction.builder()
                .id(5L)
                .dateTime(LocalDateTime.now())
                .amount(300)
                .type(Transaction.Type.WITHDRAWAL)
                .build();
        return List.of(transaction1, transaction2, transaction3, transaction4, transaction5);
    }
}