package com.example.AteEsercizioTirocinio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "checking_account", referencedColumnName = "id")
    private CheckingAccount checkingAccount;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @NotNull
    private double amount;

    @NotNull
    public String type;

    @Getter
    @AllArgsConstructor
    public enum Type {
        DEPOSIT("Deposit"),
        WITHDRAWAL("Withdrawal");

        private final String value;
    }
}
