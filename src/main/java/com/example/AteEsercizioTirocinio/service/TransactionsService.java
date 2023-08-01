package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.exceptions.BadRequestException;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.repository.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;

    private final TransactionsMapper transactionsMapper;

    public TransactionsService(TransactionsRepository transactionsRepository, TransactionsMapper transactionsMapper) {
        this.transactionsRepository = transactionsRepository;
        this.transactionsMapper = transactionsMapper;
    }


    public List<Transactions> retrieveTransactionById(String pan) {
        return transactionsRepository.findByNumConto(pan);
    }

    public TransactionDto makeDeposit(String pan, double amount) {

        var transactions = transactionsRepository.findByNumConto(pan);
        var newTrasaction = new Transactions();
        var transaction = transactions.stream().findFirst().orElseThrow(() -> new NotFoundException("No Pan Match"));
            newTrasaction.setNumConto(pan);
            newTrasaction.setTransactionType("deposit " + amount);
            newTrasaction.setDateTime(LocalDate.now());
            newTrasaction.setBalance(transaction.getBalance() + amount);

            transactionsRepository.save(newTrasaction);

        return transactionsMapper.entityToDto(newTrasaction);
    }

//    public Transactions makeWithdrawal(TransactionDto transactionDto) {
//        // importo prelievo positivo e saldo sufficiente
//        if (transactionDto.getBalance() <= 0) {
//            throw new BadRequestException("The balance of the withdrawal isn't > 0");
//        }
//
//        Transactions transaction = transactionsMapper.dtoToEntity(transactionDto);
//        transaction.setTransactionType("withdrawal");
//        transaction.setDateTime(new Date());
//
//        // saldo sufficiente per prelievo
//        ContoCorrente contoCorrente = transaction.getContoCorrente();
//        double balanceAfterWithdrawal = contoCorrente.getBalance() - transaction.getBalance();
//        if (balanceAfterWithdrawal < 0) {
//            throw new BadRequestException("Balance insufficient for withdrawal");
//        }
//        // OPPURE saldo disponibile è sufficiente per prelievo
////        ContoCorrente contoCorrente = transaction.getContoCorrente();
////        double actualBalance = contoCorrente.getBalance();
////        double amountWithdrawal = transaction.getBalance();
////        if (actualBalance < amountWithdrawal) {
////            throw new BadRequestException("Balance insufficient for withdrawal");
////        }
//
//        contoCorrente.setBalance(balanceAfterWithdrawal); // Aggiorna il saldo del conto corrente
//        contoCorrenteRepository.save(contoCorrente); // Salva il conto corrente aggiornato
//
//        return transactionsRepository.save(transaction);
//    }

}
