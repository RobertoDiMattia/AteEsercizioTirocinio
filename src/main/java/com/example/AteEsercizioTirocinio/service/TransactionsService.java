package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.Transaction;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final CheckingAccountRepository checkingAccountRepository;
    private final TransactionsRepository transactionsRepository;
    private final TransactionsMapper transactionsMapper;

    public List<TransactionDto> retrieveTransactionById(Long id) {
        var transactions = transactionsRepository.findByContoCorrenteId(id);
        return transactionsMapper.listEntityToListDto(transactions);
    }

    public TransactionDto makeDeposit(Long id, double amount) {
        var checkingAccounts = checkingAccountRepository.findById(id);
        var checkingAccount = checkingAccounts.stream().findFirst()
                .orElseThrow(() -> new NotFoundException("No iban Match"));

        double newBalance = checkingAccount.getBalance() + amount;
        checkingAccount.setBalance(newBalance);
        checkingAccountRepository.save(checkingAccount);

        var newTransaction = Transaction.builder()
                .type(Transaction.Type.DEPOSIT)
                .amount(amount)
                .dateTime(LocalDateTime.now())
                .build();
        return transactionsMapper.entityToDto(newTransaction);
    }

    public TransactionDto makeWithdrawal(Long id, double amount) {
        var contiCorrenti = checkingAccountRepository.findById(id);
        var contoCorrente = contiCorrenti.stream().findFirst()
                .orElseThrow(() -> new NotFoundException("No iban Match"));

        if (contoCorrente.getBalance() < amount) {
        throw new IllegalArgumentException("The balance can't be lower than the amount to withdraw");
        }

        double newBalance = contoCorrente.getBalance() - amount;
        contoCorrente.setBalance(newBalance);
        checkingAccountRepository.save(contoCorrente);

        var newTransaction = Transaction.builder()
                .type(Transaction.Type.WITHDRAWAL)
                .amount(amount)
                .dateTime(LocalDateTime.now())
                .build();
        return transactionsMapper.entityToDto(newTransaction);
    }
}
