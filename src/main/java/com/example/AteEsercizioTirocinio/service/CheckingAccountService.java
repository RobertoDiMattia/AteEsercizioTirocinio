package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.CheckingAccountCreationRequestMapper;
import com.example.AteEsercizioTirocinio.mappers.CheckingAccountMapper;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckingAccountService {

    private final CheckingAccountRepository checkingAccountRepository;
    private final CheckingAccountMapper checkingAccountMapper;
    private final UserRepository userRepository;
    private final CheckingAccountCreationRequestMapper checkingAccountCreationRequestMapper;

    public CheckingAccount addContoCorrente(CheckingAccountCreationRequestDto checkingAccountCreationRequestDto) {
        var userId = checkingAccountCreationRequestDto.getUserId();

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + userId));

        var checkingAccount = checkingAccountCreationRequestMapper.creationRequestDtoToEntity(checkingAccountCreationRequestDto);
        checkingAccount.setUser(user);

        return checkingAccountRepository.save(checkingAccount);
    }

    public CheckingAccountDto retrieveContoCorrenteById(Long id) {
        var checkingAccount = checkingAccountRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("c/c not found whit id: " + id));

        return checkingAccountMapper.entityToDto(checkingAccount);
    }

    public double retrieveBalanceByIban(Long id) {
        var checkingAccount = checkingAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found with id: " + id));
        return checkingAccount.getBalance();
    }

    public List<TransactionDto> retrieveLastFiveTransactions(Long id) {
        var checkingAccount = checkingAccountRepository.findLastFiveTransactions(id);
        return checkingAccountMapper.listEntityToListDto(checkingAccount);

    }
}
