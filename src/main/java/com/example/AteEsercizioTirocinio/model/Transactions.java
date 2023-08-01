package com.example.AteEsercizioTirocinio.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numConto;
    private double balance;
    private String transactionType;
    private LocalDate dateTime;

    public Transactions() {}

    public Transactions(Long id, String numConto, double balance, String transactionType, LocalDate dateTime) {
        this.id = id;
        this.numConto = numConto;
        this.balance = balance;
        this.transactionType = transactionType;
        this.dateTime = dateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumConto() {
        return numConto;
    }

    public void setNumConto(String numConto) {
        this.numConto = numConto;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
