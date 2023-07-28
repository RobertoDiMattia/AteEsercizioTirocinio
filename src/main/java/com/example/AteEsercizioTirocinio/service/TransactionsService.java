package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.repository.TransactionsRepository;
import com.example.AteEsercizioTirocinio.DTO.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsService {

    private TransactionsRepository transactionsRepository;

    private TransactionsMapper transactionsMapper;

    public Transactions addTransaction(TransactionDto transactionDto) {
        return transactionsRepository.save(transactionsMapper.DtoToEntity(transactionDto));
    }

    public Transactions retrieveTransactionById(Long id) {
        return transactionsRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("transaction not found with id: " + id));
    }


    public Transactions updateTransaction(Long id, TransactionDto transactionDto) {
        Transactions transactions = transactionsRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("transaction not found"));

        return transactionsRepository.save(transactions);
    }

    public void deleteTransaction(Long id) {
        transactionsRepository.deleteById(id);
    }

    public Transactions makeDeposit(TransactionDto transactionDto) {
        return transactionsRepository.save(transactionsMapper.DtoToEntity(transactionDto));
    }

    public Transactions makeWithdrawl(TransactionDto transactionDto) {
        return transactionsRepository.save(transactionsMapper.DtoToEntity(transactionDto));
    }

    public Transactions getBalance(TransactionDto transactionDto) {
        return transactionsRepository.findByBalance(transactionDto);
    }

    public List<Transactions> getLast5transactions(String numConto) {
//        return transactionsRepository.findTop5ByNumContoCorrenteOrderByDateTimeDesc(numConto);
        return List.of();
    }
}
