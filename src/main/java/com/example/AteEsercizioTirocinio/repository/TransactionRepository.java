package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCheckingAccountId(Long checkingAccountId);

}
