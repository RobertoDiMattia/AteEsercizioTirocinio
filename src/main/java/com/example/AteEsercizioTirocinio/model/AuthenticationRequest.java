package com.example.AteEsercizioTirocinio.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;
}
