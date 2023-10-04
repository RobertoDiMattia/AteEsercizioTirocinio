package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.model.AuthenticationResponse;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthServiceController {

    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
        String jwt = authService.authenticate(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}