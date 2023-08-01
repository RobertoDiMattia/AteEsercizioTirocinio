package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByNumContoOrderByNumContoDesc(String iban);
}
