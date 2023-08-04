package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.repository.ContoCorrenteRepository;
import com.example.AteEsercizioTirocinio.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final ContoCorrenteRepository contoCorrenteRepository;
    private final TransactionsRepository transactionsRepository;
    private final TransactionsMapper transactionsMapper;

    //cambiato parametro e metodo findByIban con findByContoCorrenteId
    public List<TransactionDto> retrieveTransactionById(Long id) {
        var transactions = transactionsRepository.findByContoCorrenteId(id);
        return transactionsMapper.listEntityToListDto(transactions);
    }

    public TransactionDto makeDeposit(Long id, double amount) {
        var contiCorrenti = contoCorrenteRepository.findById(id);
        var contoCorrente = contiCorrenti.stream().findFirst()
                .orElseThrow(() -> new NotFoundException("No iban Match"));

        // Aggiorna il saldo
        double newBalance = contoCorrente.getBalance() + amount;
        contoCorrente.setBalance(newBalance);
        contoCorrenteRepository.save(contoCorrente);

        var newTransaction = Transactions.builder()
                .transactionType("deposit " + amount)
                .dateTime(LocalDateTime.now())
                .build();

        return transactionsMapper.entityToDto(newTransaction);
    }

    public TransactionDto makeWithdrawal(Long id, double amount) {
        var contiCorrenti = contoCorrenteRepository.findById(id);
        var contoCorrente = contiCorrenti.stream().findFirst()
                .orElseThrow(() -> new NotFoundException("No iban Match"));

        if (contoCorrente.getBalance() < amount) {
        throw new IllegalArgumentException("The balance can't be lower than the amount to withdraw");
        }
        // Aggiorna il saldo
        double newBalance = contoCorrente.getBalance() - amount;
        contoCorrente.setBalance(newBalance);
        contoCorrenteRepository.save(contoCorrente);

        var newTransaction = Transactions.builder()
                .transactionType("withdrawal " + amount)
                .dateTime(LocalDateTime.now())
                .build();
        // transactionsRepository.save(newTransaction); (Non serve più)
        return transactionsMapper.entityToDto(newTransaction);
    }

    public double retrieveBalance(Long id) {
        var contoCorrente = contoCorrenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found with id: " + id));
        return contoCorrente.getBalance();
    }

    public List<TransactionDto> retrieveLastFiveTransactions(Long contoCorrenteId) {
        var transactions = transactionsRepository.findLastFiveTransactions(contoCorrenteId);
        return transactionsMapper.listEntityToListDto(transactions);
    }
}
