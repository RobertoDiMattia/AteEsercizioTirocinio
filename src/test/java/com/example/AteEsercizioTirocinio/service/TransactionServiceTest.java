package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import com.example.AteEsercizioTirocinio.model.Transaction;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    private static final String EMAIL = "rob@mail.it";
    private static final String FIRST_NAME = "Roby";
    private static final String LAST_NAME = "Dima";
    private static final Long EXISTING_USER_ID = 12L;
    private static final String IBAN = "IT12345678910";
    private static final double BALANCE = 2000;
    private static final double AMOUNT = 300;
    private static final Long EXISTING_TRANSACTION_ID = 5L;

    @Test
    public void testMakeDeposit() {
        var checkingAccount = mockedCheckingAccount();
        when(checkingAccountRepository.findById(EXISTING_CHECKING_ACCOUNT_ID))
                .thenReturn(Optional.of(checkingAccount));

        double newBalance = checkingAccount.getBalance() + AMOUNT;
        checkingAccount.setBalance(newBalance);
        when(checkingAccountRepository.save(any())).thenReturn(checkingAccount);

        var newTransaction = mockedTransaction();

        when(transactionRepository.save(newTransaction)).thenReturn(newTransaction);

        var newTransactionDto = mockedTransactionDto();

        when(transactionsMapper.entityToDto(newTransaction)).thenReturn(newTransactionDto);

        var depositedTransaction = transactionService.makeDeposit(EXISTING_CHECKING_ACCOUNT_ID, AMOUNT);

        assertThat(depositedTransaction).isNotNull();
        assertThat(depositedTransaction.getCheckingAccountId()).isEqualTo(EXISTING_CHECKING_ACCOUNT_ID);
        assertThat(depositedTransaction.getType()).isEqualTo(Transaction.Type.DEPOSIT);
        assertThat(depositedTransaction.getAmount()).isEqualTo(AMOUNT);

        //esercitazione
        assertNotNull(depositedTransaction);
        assertEquals(EXISTING_CHECKING_ACCOUNT_ID, depositedTransaction.getCheckingAccountId());
        assertEquals(Transaction.Type.DEPOSIT, depositedTransaction.getType());
        assertEquals(AMOUNT, depositedTransaction.getAmount());

        verify(checkingAccountRepository, times(1)).findById(EXISTING_CHECKING_ACCOUNT_ID);
    }

    @Test
    public void testMakeWithdrawal() {
        var checkingAccount = mockedCheckingAccount();
        when(checkingAccountRepository.findById(EXISTING_CHECKING_ACCOUNT_ID))
                .thenReturn(Optional.of(checkingAccount));

        if (checkingAccount.getBalance() < AMOUNT) {
            assertThatThrownBy(() -> transactionService.makeWithdrawal(EXISTING_CHECKING_ACCOUNT_ID, AMOUNT))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("The balance can't be lower than the amount to withdraw");
            return;
        }

        double newBalance = checkingAccount.getBalance() - AMOUNT;
        checkingAccount.setBalance(newBalance);
        when(checkingAccountRepository.save(any())).thenReturn(checkingAccount);

        Transaction newTransaction = mockedTransaction();
        when(transactionRepository.save(any())).thenReturn(newTransaction);

        var newTransactionDto = mockedTransactionDto();
        when(transactionsMapper.entityToDto(any())).thenReturn(newTransactionDto);

        var response = transactionService.makeWithdrawal(EXISTING_CHECKING_ACCOUNT_ID, AMOUNT);

        assertThat(response).isNotNull();
        assertThat(response.getCheckingAccountId()).isEqualTo(EXISTING_CHECKING_ACCOUNT_ID);
        assertThat(response.getType()).isEqualTo(Transaction.Type.WITHDRAWAL);
        assertThat(response.getAmount()).isEqualTo(AMOUNT);

        //Esercitazione
        assertNotNull(response);
        assertEquals(EXISTING_CHECKING_ACCOUNT_ID, response.getCheckingAccountId());
        assertEquals(Transaction.Type.WITHDRAWAL, response.getType());
        assertEquals(AMOUNT, response.getAmount());
    }

    private static Transaction mockedTransaction() {
        return Transaction.builder()
                .type(Transaction.Type.WITHDRAWAL)
                .type(Transaction.Type.DEPOSIT)
                .amount(AMOUNT)
                .dateTime(LocalDateTime.now())
                .build();
    }

    private TransactionDto mockedTransactionDto() {
        return TransactionDto.builder()
                .id(EXISTING_TRANSACTION_ID)
                .checkingAccountId(EXISTING_CHECKING_ACCOUNT_ID)
                .type(Transaction.Type.DEPOSIT)
                .dateTime(LocalDateTime.now())
                .amount(AMOUNT)
                .build();
    }

    private CheckingAccount mockedCheckingAccount() {
        return CheckingAccount.builder()
                .id(EXISTING_CHECKING_ACCOUNT_ID)
                .user(mockedUser())
                .iban(IBAN)
                .transactions(Collections.emptyList())
                .balance(BALANCE)
                .build();
    }

    private User mockedUser() {
        return User.builder()
                .id(EXISTING_USER_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .build();
    }
}