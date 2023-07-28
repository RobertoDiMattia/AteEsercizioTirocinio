package com.example.AteEsercizioTirocinio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContoCorrenteDto {

    private Long id;
    private Long userId;
    private UUID numConto;
}
