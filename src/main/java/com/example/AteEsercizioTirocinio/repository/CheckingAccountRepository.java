package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import com.example.AteEsercizioTirocinio.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {

    Optional<CheckingAccount> findById(Long id);

//    @Query(value = "SELECT * FROM Transaction t WHERE t.checking_account_id = :checking_account_id ORDER BY t.id DESC LIMIT 5", nativeQuery = true)

    @Query(value = "SELECT * FROM \"transaction\" WHERE checking_account_id = :checking_account_id ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Transaction> findLastFiveTransactions(@Param("checking_account_id") Long checkingAccountId);
}

