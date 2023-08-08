package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.mappers.CheckingAccountMapper;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public static final String EMAIL = "rob@mail.it";
    public static final String FIRST_NAME = "Roby";
    public static final String LAST_NAME = "Dima";
    private static final Long EXISTING_CHECKING_ACCOUNT_ID = 1L;
    private static final String IBAN = "IT12345678910111213";
    private static final double BALANCE = 1000;

    @Test
    public void testAddCheckingAccount() {
        CheckingAccountCreationRequestDto creationRequestDto = new CheckingAccountCreationRequestDto();
        creationRequestDto.setUserId(EXISTING_USER_ID);
        User user = User.builder()
                .id(EXISTING_USER_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .build();
        when(userRepository.findById(EXISTING_USER_ID)).thenReturn(Optional.of(user));
        String generatedIban = "IT12345678910111213";
        when(checkingAccountService.generateIban()).thenReturn(generatedIban);
        CheckingAccount checkingAccount = CheckingAccount.builder()
                .user(user)
                .iban(generatedIban)
                .balance(0.0)
                .build();
        when(checkingAccountMapper.creationRequestDtoToEntity(creationRequestDto)).thenReturn(checkingAccount);
        when(checkingAccountRepository.save(checkingAccount)).thenReturn(checkingAccount);
        var checkingAccountDto = CheckingAccountDto.builder()
                .id(checkingAccount.getId())
                .userId(EXISTING_USER_ID)
                .iban(generatedIban)
                .balance(0.0)
                .transactions(new ArrayList<>())
                .build();
        when(checkingAccountMapper.entityToDto(checkingAccount)).thenReturn(checkingAccountDto);
        var addedCheckingAccount = checkingAccountService.addCheckingAccount(creationRequestDto);
        assertNotNull(addedCheckingAccount);
        assertEquals(EXISTING_USER_ID, addedCheckingAccount.getUserId());
        assertEquals(generatedIban, addedCheckingAccount.getIban());
        assertEquals(0.0, addedCheckingAccount.getBalance());

        verify(userRepository, times(1)).findById(EXISTING_USER_ID);
        verify(checkingAccountRepository, times(1)).save(checkingAccount);
        verify(checkingAccountMapper, times(1)).creationRequestDtoToEntity(creationRequestDto);
        verify(checkingAccountMapper, times(1)).entityToDto(checkingAccount);
    }

    @Test
    void validAddCheckingAccount_validResponse() {
        CheckingAccountCreationRequestDto creationRequestDto = new CheckingAccountCreationRequestDto();
        creationRequestDto.setUserId(EXISTING_USER_ID);
        User user = new User();
        when(userRepository.findById(EXISTING_USER_ID)).thenReturn(Optional.of(user));
        CheckingAccount checkingAccount = new CheckingAccount();
        when(checkingAccountMapper.creationRequestDtoToEntity(creationRequestDto)).thenReturn(checkingAccount);
        when(checkingAccountRepository.save(checkingAccount)).thenReturn(checkingAccount);
        CheckingAccountDto checkingAccountDto = new CheckingAccountDto();
        when(checkingAccountMapper.entityToDto(checkingAccount)).thenReturn(checkingAccountDto);
        CheckingAccountDto addedCheckingAccount = checkingAccountService.addCheckingAccount(creationRequestDto);
        assertNotNull(addedCheckingAccount);
        verify(userRepository, times(1)).findById(EXISTING_USER_ID);
        verify(checkingAccountRepository, times(1)).save(checkingAccount);
        verify(checkingAccountMapper, times(1)).creationRequestDtoToEntity(creationRequestDto);
        verify(checkingAccountMapper, times(1)).entityToDto(checkingAccount);
    }

    @Test
    public void testRetrieveBalanceById() {
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(900.0);
        when(checkingAccountRepository.findById(EXISTING_CHECKING_ACCOUNT_ID))
                .thenReturn(Optional.of(checkingAccount));
        double balance = checkingAccountService.retrieveBalanceById(EXISTING_CHECKING_ACCOUNT_ID);
        assertEquals(BALANCE, balance);
        verify(checkingAccountRepository, times(1)).findById(EXISTING_CHECKING_ACCOUNT_ID);
    }

    @Test
    public void testRetrieveLastFiveTransactions() {
        var checkingAccount = CheckingAccount.builder()
                .id(EXISTING_CHECKING_ACCOUNT_ID)
                .user(new User())
                .iban(IBAN)
                .balance(BALANCE)
                .transactions(new ArrayList<>())
                .build();
        List<CheckingAccount> transactions = new ArrayList<>();
        transactions.add(checkingAccount);
        when(checkingAccountRepository.findLastFiveTransactions(EXISTING_CHECKING_ACCOUNT_ID))
                .thenReturn(transactions);
        List<CheckingAccountDto> transactionDtos = new ArrayList<>();
        when(checkingAccountMapper.listEntityToListDto(transactions)).thenReturn(transactionDtos);
        List<CheckingAccountDto> retrievedTransactions =
                checkingAccountService.retrieveLastFiveTransactions(EXISTING_CHECKING_ACCOUNT_ID);
        assertNotNull(retrievedTransactions);
        assertEquals(1, retrievedTransactions.size());
        var retrievedTransactionDto = retrievedTransactions.get(0);
        assertEquals(EXISTING_CHECKING_ACCOUNT_ID, retrievedTransactionDto.getId());
        assertEquals(IBAN, retrievedTransactionDto.getIban());
        assertEquals(BALANCE, retrievedTransactionDto.getBalance());
        verify(checkingAccountRepository, times(1)).findLastFiveTransactions(EXISTING_CHECKING_ACCOUNT_ID);
        verify(checkingAccountMapper, times(1)).listEntityToListDto(transactions);
    }

    private User getUser() {
        return User.builder()
                .id(EXISTING_USER_ID)
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();
    }

    private CheckingAccount getCheckingAccount() {
        return CheckingAccount.builder()
                .id(EXISTING_CHECKING_ACCOUNT_ID)
                .iban(IBAN)
                .balance(BALANCE)
                .build();

    }
}