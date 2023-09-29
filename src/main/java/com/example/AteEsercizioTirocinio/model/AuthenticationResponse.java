package com.example.AteEsercizioTirocinio.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationResponse {

    private final String jwt;

}