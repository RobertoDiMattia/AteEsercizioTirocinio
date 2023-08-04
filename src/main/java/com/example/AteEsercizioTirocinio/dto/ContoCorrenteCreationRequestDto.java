package com.example.AteEsercizioTirocinio.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotNull
    private Long userId;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    private UserDto user;
}
