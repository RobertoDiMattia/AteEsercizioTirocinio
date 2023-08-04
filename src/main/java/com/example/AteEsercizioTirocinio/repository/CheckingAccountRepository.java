package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {

    Optional<CheckingAccount> findById(Long id);

    @Query(value = "SELECT * FROM Transactions WHERE conto_corrente_id = :contoCorrenteId ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<TransactionDto> findLastFiveTransactions(Long contoCorrenteId);
}