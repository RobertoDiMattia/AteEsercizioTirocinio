package com.example.AteEsercizioTirocinio.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContoCorrenteCreationRequestDto {

    @NotNull
    private Long id;
}
