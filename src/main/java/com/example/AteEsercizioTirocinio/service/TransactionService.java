package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.Transaction;
import com.example.AteEsercizioTirocinio.repository.CheckingAccountRepository;
import com.example.AteEsercizioTirocinio.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final CheckingAccountRepository checkingAccountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionsMapper transactionsMapper;

    public List<TransactionDto> retrieveTransactionByCheckingAccountId(Long checkingAccountId) {
        var transactions = transactionRepository.findByCheckingAccountId(checkingAccountId);
        return transactionsMapper.listEntityToListDto(transactions);
    }


    public TransactionDto makeDeposit(Long id, double amount) {
        var checkingAccounts = checkingAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No iban Match"));

        var newBalance = checkingAccounts.getBalance() + amount;
        checkingAccounts.setBalance(newBalance);
        checkingAccountRepository.save(checkingAccounts);

        var newTransaction = Transaction.builder()
                .type(Transaction.Type.DEPOSIT)
                .amount(amount)
                .dateTime(LocalDateTime.now())
                .build();
        return transactionsMapper.entityToDto(newTransaction);
    }

    public TransactionDto makeWithdrawal(Long id, double amount) {
        var checkingAccounts = checkingAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No iban Match"));

        if (checkingAccounts.getBalance() < amount) {
            throw new IllegalArgumentException("The balance can't be lower than the amount to withdraw");
        }

        double newBalance = checkingAccounts.getBalance() - amount;
        checkingAccounts.setBalance(newBalance);
        checkingAccountRepository.save(checkingAccounts);

        var newTransaction = Transaction.builder()
                .type(Transaction.Type.WITHDRAWAL)
                .amount(amount)
                .dateTime(LocalDateTime.now())
                .build();
        return transactionsMapper.entityToDto(newTransaction);
    }
}
