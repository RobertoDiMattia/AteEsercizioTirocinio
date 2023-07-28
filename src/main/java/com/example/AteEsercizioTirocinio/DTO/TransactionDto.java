package com.example.AteEsercizioTirocinio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;
    private UUID numContoCorrente;
    private String transactionType;
    private double balance;
    private Date dateTime;

}

