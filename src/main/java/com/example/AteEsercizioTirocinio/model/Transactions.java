package com.example.AteEsercizioTirocinio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contoCorrenteId;

    @ManyToOne
    @JoinColumn(name = "conto_corrente_id", referencedColumnName = "userId")
    private ContoCorrente contoCorrente;

    private String transactionType;
    private double amount;
    private Date dateTime;

}
