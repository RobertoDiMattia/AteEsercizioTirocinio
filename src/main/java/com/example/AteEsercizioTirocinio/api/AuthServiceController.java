package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.LoginRequest;
import com.example.AteEsercizioTirocinio.dto.RegistrationRequest;
import com.example.AteEsercizioTirocinio.service.AuthService;
import com.example.AteEsercizioTirocinio.service.MyUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthServiceController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        val authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        return ResponseEntity.ok("login avvenuto con successo");

        // Genera un token JWT
//        String jwt = Jwts.builder()
//                .setSubject(loginRequest.getEmail())  //l'utente come soggetto del token
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000))  //data di scadenza,1 giorno
//                .signWith(SignatureAlgorithm.HS256, "secretkey")  // firma token con una chiave segreta
//                .compact();  // costruisce il token
//
//        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest registrationRequest) {

        // salva l'utente nel database
        userDetailsService.saveUser(registrationRequest);

        return ResponseEntity.ok("Registrazione completata con successo");
    }
}