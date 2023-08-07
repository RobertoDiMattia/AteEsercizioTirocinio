package com.example.AteEsercizioTirocinio.dto;

import com.example.AteEsercizioTirocinio.model.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    @NotNull
    private Long id;

    @NotBlank
    private Transaction.Type type;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private double amount;

}

