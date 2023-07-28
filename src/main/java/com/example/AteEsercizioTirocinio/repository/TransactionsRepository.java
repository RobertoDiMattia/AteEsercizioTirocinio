package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.DTO.TransactionDto;
import com.example.AteEsercizioTirocinio.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

//    List<Transactions> findTop5ByNumContoCorrenteOrderByDateTimeDesc(String numConto);

    Transactions findByBalance(TransactionDto transactionDto);
}
