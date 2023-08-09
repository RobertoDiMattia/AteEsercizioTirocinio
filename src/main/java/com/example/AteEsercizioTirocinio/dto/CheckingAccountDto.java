package com.example.AteEsercizioTirocinio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckingAccountDto {

    @NotNull
    private Long id;

    @NotNull
    private Long UserId;

    @NotEmpty
    private List<TransactionDto> transactions;

    @NotBlank
    private String iban;

    @NotNull
    private double balance;
}
