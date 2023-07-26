package com.example.AteEsercizioTirocinio.transactionsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContoCorrenteDto {

    private Long id;
    private double balance;
    private String numConto;
}
