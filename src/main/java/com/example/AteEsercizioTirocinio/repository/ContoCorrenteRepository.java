package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContoCorrenteRepository extends JpaRepository {

    ContoCorrente findByUserId(Long userId);

}
