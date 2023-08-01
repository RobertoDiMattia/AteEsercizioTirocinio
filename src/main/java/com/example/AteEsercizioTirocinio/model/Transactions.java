package com.example.AteEsercizioTirocinio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numConto;
    private double balance;
    private String transactionType;
    private LocalDate dateTime;
}
