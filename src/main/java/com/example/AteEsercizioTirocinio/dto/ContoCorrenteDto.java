package com.example.AteEsercizioTirocinio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContoCorrenteDto {

    @NotNull
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @NotBlank
    private String iban;

    @NotNull
    private double balance;
}
