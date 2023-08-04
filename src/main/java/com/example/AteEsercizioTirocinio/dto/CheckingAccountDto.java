package com.example.AteEsercizioTirocinio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckingAccountDto {

    @NotNull
    private Long id;

    @NotEmpty
    private List<TransactionDto> transactions;

    @NotBlank
    private String iban;

    @NotNull
    private double balance;
}
