package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContoCorrenteRepository extends JpaRepository<ContoCorrente, Long> {

    //    List<Transactions> findTop5ByNumContoCorrenteOrderByDateTimeDesc(String numConto);

   // Transactions findByBalance(TransactionDto transactionDto);
}
