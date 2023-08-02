package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final TransactionsMapper transactionsMapper;

    public List<TransactionDto> retrieveTransactionById(String iban) {
        var transactions = transactionsRepository.findByNumConto(iban);
        return transactionsMapper.listEntityToListDto(transactions);
    }

    public TransactionDto makeDeposit(String iban, double amount) {

        var transactions = transactionsRepository.findByNumConto(iban);
        var transaction = transactions.stream().findFirst()
                .orElseThrow(() -> new NotFoundException("No iban Match"));
        transaction.setBalance(transaction.getBalance() + amount);
        transactionsRepository.save(transaction);
        var newTrasaction = Transactions.builder()
                .numConto(iban)
                .transactionType("deposit " + amount)
                .dateTime(LocalDate.now())
                .balance(transaction.getBalance())
                .build();

        transactionsRepository.save(newTrasaction);

        return transactionsMapper.entityToDto(newTrasaction);
    }

    public TransactionDto makeWithdrawal(String iban, double amount) {

        var transactions = transactionsRepository.findByNumConto(iban);
        var transaction = transactions.stream().findFirst()
                .orElseThrow(() -> new NotFoundException("No iban Match"));

        if(transaction.getBalance() < amount){
            throw new IllegalArgumentException("the balance can't be lower than amount to withdraw");
        }
        transaction.setBalance(transaction.getBalance() - amount);
        transactionsRepository.save(transaction);
        var newTrasaction = Transactions.builder()
                .numConto(iban)
                .transactionType("withdrawal " + amount)
                .dateTime(LocalDate.now())
                .balance(transaction.getBalance())
                .build();

        transactionsRepository.save(newTrasaction);

        return transactionsMapper.entityToDto(newTrasaction);
    }
}
