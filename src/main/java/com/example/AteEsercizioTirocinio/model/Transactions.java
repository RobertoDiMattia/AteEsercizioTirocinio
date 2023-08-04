package com.example.AteEsercizioTirocinio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conto_corrente", referencedColumnName = "id")
    private ContoCorrente contoCorrente;

    @NotBlank
    private String transactionType;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

}
