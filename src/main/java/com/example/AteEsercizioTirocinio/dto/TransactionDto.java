package com.example.AteEsercizioTirocinio.dto;

import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public class TransactionDto {

    private String numConto;
    private String transactionType;

    @Min(10)
    private double balance;

    private LocalDate dateTime;

    public TransactionDto() {}

    public TransactionDto(String numConto, String transactionType, double balance, LocalDate dateTime) {
        this.numConto = numConto;
        this.transactionType = transactionType;
        this.balance = balance;
        this.dateTime = dateTime;
    }

    public String getNumConto() {
        return numConto;
    }

    public void setNumConto(String numConto) {
        this.numConto = numConto;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}

