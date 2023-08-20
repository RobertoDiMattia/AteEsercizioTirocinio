package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.mappers.CheckingAccountMapper;
import com.example.AteEsercizioTirocinio.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class CheckingAccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Autowired
    private CheckingAccountMapper checkingAccountMapper;

//    @Test
//    public void testFindLastFiveTransactions() {
//        var testCheckingAccountDto = entityManager.persistAndFlush(mockedCheckingAccount());
//        var testCheckingAccount = checkingAccountMapper.listEntityToListDto((List<TransactionDto>) testCheckingAccountDto);
//        entityManager.persistAndFlush(testCheckingAccount);
//
//        List<TransactionDto> lastFiveTransactions = checkingAccountRepository.findLastFiveTransactions(testCheckingAccount.getId());
//
//        assertThat(lastFiveTransactions).hasSize(5);
//    }
//
//    private CheckingAccountDto mockedCheckingAccount() {
//        List<TransactionDto> transactionDtoList = (List<TransactionDto>) mockedTransactionDto();
//        return CheckingAccountDto.builder()
//                .id(1L)
//                .UserId(123L)
//                .transactions(transactionDtoList)
//                .iban("IBAN12345678910")
//                .balance(1000.0)
//                .build();
//    }

    private TransactionDto mockedTransactionDto() {
        var transaction1 = TransactionDto.builder()
                .id(2L)
                .dateTime(LocalDateTime.now())
                .amount(500)
                .type(Transaction.Type.DEPOSIT)
                .build();
        var transaction2 = TransactionDto.builder()
                .id(1L)
                .dateTime(LocalDateTime.now())
                .amount(450)
                .build();
        var transaction3 = TransactionDto.builder()
                .id(2L)
                .dateTime(LocalDateTime.now())
                .amount(400)
                .type(Transaction.Type.DEPOSIT)
                .build();
        var transaction4 = TransactionDto.builder()
                .id(2L)
                .dateTime(LocalDateTime.now())
                .amount(250)
                .type(Transaction.Type.WITHDRAWAL)
                .build();
        var transaction5 = TransactionDto.builder()
                .id(2L)
                .dateTime(LocalDateTime.now())
                .amount(300)
                .type(Transaction.Type.WITHDRAWAL)
                .build();
        return (TransactionDto) List.of(transaction1, transaction2, transaction3, transaction4, transaction5);
    }
}

//@DataJpaTest
//class CheckingAccountRepositoryTest {
//
//    @Autowired
//    private CheckingAccountRepository checkingAccountRepository;
//
//    @Test
//    void testFindLastFiveTransactions() {
//        var checkingAccount = mockedCheckingAccount();
//        checkingAccountRepository.save(checkingAccount);
//
//        List<Transaction> transactions = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            transactions.add(createMockedTransaction(checkingAccount, i));
//        }
//        checkingAccount.setTransactions(transactions);
//        checkingAccountRepository.save(checkingAccount);
//
//        List<TransactionDto> foundTransactions = checkingAccountRepository.findLastFiveTransactions(checkingAccount.getId());
//
//        assertThat(foundTransactions).hasSize(5);
//        assertThat(foundTransactions.get(0).getAmount()).isEqualTo(1000);
//        assertThat(foundTransactions.get(4).getAmount()).isEqualTo(600);
//
//    }
//
//    private CheckingAccount mockedCheckingAccount() {
//        return CheckingAccount.builder()
//                .id(1L)
//                .iban("IT12345678910")
//                .balance(1000)
//                .build();
//    }
//
//    private Transaction createMockedTransaction(CheckingAccount checkingAccount, int i) {
//        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(10).plusMinutes(i);
//        return Transaction.builder()
//                .checkingAccount(checkingAccount)
//                .amount(i * 100)
//                .dateTime(dateTime)
//                .type(i % 2 == 0 ? Transaction.Type.DEPOSIT : Transaction.Type.WITHDRAWAL)
//                .build();
//    }