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
        var transactions = transactionsRepository.findByNumContoOrderByNumContoDesc(iban);
        return transactionsMapper.listEntityToListDto(transactions);
    }

    public TransactionDto makeDeposit(String iban, double amount) {

        var transactions = transactionsRepository.findByNumContoOrderByNumContoDesc(iban);
        var transaction = transactions.stream().findFirst().orElseThrow(() -> new NotFoundException("No iban Match"));
        var newTrasaction = Transactions.builder()
                .numConto(iban)
                .transactionType("deposit " + amount)
                .dateTime(LocalDate.now())
                .balance((transaction.getBalance() + amount))
                .build();

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
