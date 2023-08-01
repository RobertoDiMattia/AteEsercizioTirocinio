package com.example.AteEsercizioTirocinio.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private String numConto;
    private String transactionType;

    @Min(10)
    private double balance;

    private LocalDate dateTime;
}

