package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import com.example.AteEsercizioTirocinio.model.Transaction;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

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

    private static final Long EXISTING_CHECKING_ACCOUNT_ID = 1L;
    private static final String IBAN = "IT12345678910";
    private static final double BALANCE = 2000;
    private static final double AMOUNT_DEPOSIT = 300;
    private static final double AMOUNT_WITHDRAWAL = 100;
    private static final Long EXISTING_TRANSACTION_ID = 5L;

    @Test
    public void testMakeDeposit() {
        var checkingAccount = mockedCheckingAccount();
        when(checkingAccountRepository.findById(EXISTING_CHECKING_ACCOUNT_ID))
                .thenReturn(Optional.of(checkingAccount));

        double newBalance = checkingAccount.getBalance() + AMOUNT_DEPOSIT;
        checkingAccount.setBalance(newBalance);
        when(checkingAccountRepository.save(any())).thenReturn(checkingAccount);

        var newTransaction = mockedTransactionDeposit();
        Mockito.lenient().when(transactionRepository.save(any())).thenReturn(newTransaction);

        var newTransactionDto = mockedTransactionDtoDeposit();
        Mockito.lenient().when(transactionsMapper.entityToDto(any())).thenReturn(newTransactionDto);

        var response = transactionService.makeDeposit(EXISTING_CHECKING_ACCOUNT_ID, AMOUNT_DEPOSIT);

        assertThat(response).isNotNull();
        assertThat(response.getCheckingAccountId()).isEqualTo(EXISTING_CHECKING_ACCOUNT_ID);
        assertThat(response.getType()).isEqualTo(Transaction.Type.DEPOSIT);
        assertThat(response.getAmount()).isEqualTo(AMOUNT_DEPOSIT);

        verify(checkingAccountRepository, times(1)).findById(EXISTING_CHECKING_ACCOUNT_ID);
    }

    @Test
    public void testMakeWithdrawal() {
        var checkingAccount = mockedCheckingAccount();
        when(checkingAccountRepository.findById(EXISTING_CHECKING_ACCOUNT_ID))
                .thenReturn(Optional.of(checkingAccount));

        if (checkingAccount.getBalance() < AMOUNT_WITHDRAWAL) {
            assertThatThrownBy(() -> transactionService.makeWithdrawal(EXISTING_CHECKING_ACCOUNT_ID, AMOUNT_WITHDRAWAL))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("The balance can't be lower than the amount to withdraw");
            return;
        }

        double newBalance = checkingAccount.getBalance() - AMOUNT_WITHDRAWAL;
        checkingAccount.setBalance(newBalance);
        when(checkingAccountRepository.save(any())).thenReturn(checkingAccount);

        var newTransaction = mockedTransactionWithdrawal();
        Mockito.lenient().when(transactionRepository.save(any())).thenReturn(newTransaction);

        var newTransactionDto = mockedTransactionDtoWithdrawal();
        Mockito.lenient().when(transactionsMapper.entityToDto(any())).thenReturn(newTransactionDto);

        var response = transactionService.makeWithdrawal(EXISTING_CHECKING_ACCOUNT_ID, AMOUNT_WITHDRAWAL);

        assertThat(response).isNotNull();
        assertThat(response.getCheckingAccountId()).isEqualTo(EXISTING_CHECKING_ACCOUNT_ID);
        assertThat(response.getType()).isEqualTo(Transaction.Type.WITHDRAWAL);
        assertThat(response.getAmount()).isEqualTo(AMOUNT_WITHDRAWAL);

        //Esercitazione
//        assertNotNull(response);
//        assertEquals(EXISTING_CHECKING_ACCOUNT_ID, response.getCheckingAccountId());
//        assertEquals(Transaction.Type.WITHDRAWAL, response.getType());
//        assertEquals(AMOUNT_WITHDRAWAL, response.getAmount());
    }

    private Transaction mockedTransactionWithdrawal() {
        return Transaction.builder()
                .type(Transaction.Type.WITHDRAWAL)
                .amount(AMOUNT_WITHDRAWAL)
                .dateTime(LocalDateTime.now())
                .build();
    }

    private Transaction mockedTransactionDeposit() {
        return Transaction.builder()
                .type(Transaction.Type.DEPOSIT)
                .amount(AMOUNT_DEPOSIT)
                .dateTime(LocalDateTime.now())
                .build();
    }

    private TransactionDto mockedTransactionDtoWithdrawal() {
        return TransactionDto.builder()
                .id(EXISTING_TRANSACTION_ID)
                .checkingAccountId(EXISTING_CHECKING_ACCOUNT_ID)
                .type(Transaction.Type.WITHDRAWAL)
                .dateTime(LocalDateTime.now())
                .amount(AMOUNT_WITHDRAWAL)
                .build();
    }

    private TransactionDto mockedTransactionDtoDeposit() {
        return TransactionDto.builder()
                .id(EXISTING_TRANSACTION_ID)
                .checkingAccountId(EXISTING_CHECKING_ACCOUNT_ID)
                .type(Transaction.Type.DEPOSIT)
                .dateTime(LocalDateTime.now())
                .amount(AMOUNT_DEPOSIT)
                .build();
    }

    private CheckingAccount mockedCheckingAccount() {
        return CheckingAccount.builder()
                .id(EXISTING_CHECKING_ACCOUNT_ID)
                .iban(IBAN)
                .transactions(Collections.emptyList())
                .balance(BALANCE)
                .build();
    }
}