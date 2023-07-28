package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
