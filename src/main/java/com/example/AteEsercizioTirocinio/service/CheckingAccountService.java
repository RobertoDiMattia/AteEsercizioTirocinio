package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.CheckingAccountMapper;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CheckingAccountService {

    private final CheckingAccountRepository checkingAccountRepository;
    private final CheckingAccountMapper checkingAccountMapper;
    private final UserRepository userRepository;

    public CheckingAccountDto addCheckingAccount(CheckingAccountCreationRequestDto checkingAccountCreationRequestDto) {
        var userId = checkingAccountCreationRequestDto.getUserId();
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));

        String iban = generateIban();

        var checkingAccount = checkingAccountMapper.creationRequestDtoToEntity(checkingAccountCreationRequestDto);
        checkingAccount.setUser(user);
        checkingAccount.setIban(iban);

        return checkingAccountMapper.entityToDto(checkingAccountRepository.save(checkingAccount));
    }

    private String generateIban() {
        var random = new Random();

        String countryCode = "IT";
        String bankCode = String.format("%05d", random.nextInt(100000));
        String branchCode = String.format("%05d", random.nextInt(100000));
        String accountNumber = String.format("%010d", random.nextLong());

        return countryCode + bankCode + branchCode + accountNumber;
    }

    public CheckingAccountDto retrieveContoCorrenteById(Long id) {
        var checkingAccount = checkingAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("c/c not found whit id: " + id));

        return checkingAccountMapper.entityToDto(checkingAccount);
    }

    public double retrieveBalanceById(Long id) {
        var checkingAccount = checkingAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found with id: " + id));
        return checkingAccount.getBalance();
    }

    public List<CheckingAccountDto> retrieveLastFiveTransactions(Long id) {
        var checkingAccount = checkingAccountRepository.findLastFiveTransactions(id);
        return checkingAccountMapper.listEntityToListDto(checkingAccount);
    }
}
