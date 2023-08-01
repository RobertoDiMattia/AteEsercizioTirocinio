package com.example.AteEsercizioTirocinio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContoCorrenteDto {

    private Long id;
    private Long userId;
    private String iban;
}
