package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionsMapper transactionsMapper;

    @Test
    void findByCheckingAccountId() {
        var testTransactionDto = mockedTransactionDto();
        var transaction = transactionsMapper.dtoToEntity(testTransactionDto);
        transactionRepository.save(transaction);

        Long checkingAccountId = 1L;
        List<TransactionDto> transactions = transactionsMapper.listEntityToListDto(transactionRepository.findByCheckingAccountId(checkingAccountId));

        assertThat(transactions).isNotEmpty();
    }

    public TransactionDto mockedTransactionDto() {
        return TransactionDto.builder()
                .id(1L)
                .checkingAccountId(1L)
                .dateTime(LocalDateTime.now())
                .amount(100.0)
                .type(Transaction.Type.DEPOSIT)
                .build();
    }
}