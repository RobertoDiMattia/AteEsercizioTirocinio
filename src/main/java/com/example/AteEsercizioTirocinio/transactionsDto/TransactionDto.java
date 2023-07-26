package com.example.AteEsercizioTirocinio.transactionsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;
    private Long contoCorrenteId;
    private String transactionType;
    private double amount;
    private Date dateTime;

}

