package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByContoCorrenteId(Long id);

    @Query(value = "SELECT * FROM Transactions WHERE conto_corrente_id = :contoCorrenteId ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Transaction> findLastFiveTransactions(@Param("contoCorrenteId") Long contoCorrenteId);

}
