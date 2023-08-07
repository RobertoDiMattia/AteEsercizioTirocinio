package com.example.AteEsercizioTirocinio.model;

import jakarta.persistence.*;
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
    @JoinColumn(name = "checking_account_id", referencedColumnName = "id")
    private CheckingAccount checkingAccount;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @NotNull
    private double amount;

    @NotNull
    public Type type;

    @Getter
    @AllArgsConstructor
    public enum Type {
        DEPOSIT,
        WITHDRAWAL

    }
}
