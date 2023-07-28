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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "num_conto_corrente", referencedColumnName = "num_conto")
    private ContoCorrente contoCorrente;

    private double balance;
    private String transactionType;
    private Date dateTime;
}
