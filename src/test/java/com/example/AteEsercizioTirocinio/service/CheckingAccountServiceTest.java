package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.mappers.CheckingAccountMapper;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import com.example.AteEsercizioTirocinio.model.Transaction;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckingAccountServiceTest {

    @Mock
    private CheckingAccountRepository checkingAccountRepository;

    @Mock
    private CheckingAccountMapper checkingAccountMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CheckingAccountService checkingAccountService;

    private static final Long EXISTING_USER_ID = 1L;
    private static final String EMAIL = "rob@mail.it";
    private static final String FIRST_NAME = "Roby";
    private static final String LAST_NAME = "Dima";
    private static final Long EXISTING_CHECKING_ACCOUNT_ID = 3L;
    private static final String IBAN = "IT12345678910";
    private static final double BALANCE = 1000;

    @Test
    void testAddCheckingAccounts() {
        var requestDto = new CheckingAccountCreationRequestDto(EXISTING_USER_ID);
        var user = mockedUser();
        var checkingAccount = mockedCheckingAccount();
        var checkingAccountDto = mockedCheckingAccountDto();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(checkingAccountMapper.creationRequestDtoToEntity(any())).thenReturn(checkingAccount);
        when(checkingAccountMapper.entityToDto(any())).thenReturn(checkingAccountDto);

        var response = checkingAccountService.addCheckingAccount(requestDto);

        assertThat(response.getId()).isEqualTo(EXISTING_CHECKING_ACCOUNT_ID);
        assertThat(response.getUserId()).isEqualTo(EXISTING_USER_ID);
        assertThat(response.getIban()).startsWith("IT").hasSize(13).isEqualTo(IBAN);
        assertThat(response.getBalance()).isEqualTo(BALANCE);
        assertThat(response.getTransactions()).isEmpty();

        verify(checkingAccountRepository, times(1)).save(checkingAccount);
    }

    @Test
    public void testRetrieveBalanceById() {
        var checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(BALANCE);

        when(checkingAccountRepository.findById(EXISTING_CHECKING_ACCOUNT_ID))
                .thenReturn(Optional.of(checkingAccount));
        double balance = checkingAccountService.retrieveBalanceById(EXISTING_CHECKING_ACCOUNT_ID);

        assertEquals(BALANCE, balance);

        verify(checkingAccountRepository, times(1)).findById(EXISTING_CHECKING_ACCOUNT_ID);
    }

    @Test
    public void testRetrieveLastFiveTransactions() {
        var transactionDtoList = mockedTransactionDto();

        when(checkingAccountRepository.findLastFiveTransactions(anyLong())).thenReturn(List.of(Transaction.builder().build()));
        when(checkingAccountMapper.listEntityToListDto(any())).thenReturn(transactionDtoList);

        var response =
                checkingAccountService.retrieveLastFiveTransactions(EXISTING_CHECKING_ACCOUNT_ID);
        var retrievedTransactionDto = response.get(0);

        assertNotNull(response);
        assertEquals(5, response.size());
        assertEquals(EXISTING_CHECKING_ACCOUNT_ID, retrievedTransactionDto.getId());

        verify(checkingAccountRepository, times(1))
                .findLastFiveTransactions(EXISTING_CHECKING_ACCOUNT_ID);
    }

    private CheckingAccountDto mockedCheckingAccountDto() {
        return CheckingAccountDto.builder()
                .id(EXISTING_CHECKING_ACCOUNT_ID)
                .UserId(EXISTING_USER_ID)
                .iban(IBAN)
                .balance(BALANCE)
                .transactions(Collections.emptyList())
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

    private List<TransactionDto> mockedTransactionDto() {
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
        return List.of(transaction1, transaction2, transaction3, transaction4, transaction5);
    }

}