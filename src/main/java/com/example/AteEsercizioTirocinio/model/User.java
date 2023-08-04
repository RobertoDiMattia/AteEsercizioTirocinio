package com.example.AteEsercizioTirocinio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @OneToOne(mappedBy = "user")
    private ContoCorrente contoCorrente;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;
}
