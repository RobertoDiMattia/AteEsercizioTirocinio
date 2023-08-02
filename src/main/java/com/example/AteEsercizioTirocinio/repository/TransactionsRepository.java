package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByNumConto(String iban);

    @Query(value = "SELECT * FROM Transactions WHERE num_conto = :iban ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Transactions> findLastFiveTransaction(@Param("iban") String iban);
}
