package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.service.TransactionsService;
import javax.validation.Valid;

import jakarta.validation.constraints.Min;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/transactions")
@RestController
@Validated
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @GetMapping("/{iban}")
    public ResponseEntity<List<TransactionDto>> retrieveTransactionById(@PathVariable String iban) {
        return ResponseEntity.ok(transactionsService.retrieveTransactionById(iban));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> makeDeposit(
            @RequestParam @Valid String iban,
            @RequestParam @Min(10) double amount
    ){
        return ResponseEntity.ok(transactionsService.makeDeposit(iban, amount));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TransactionDto> makeWithdrawal(
            @RequestParam @Valid String iban,
            @RequestParam @Min(10) double amount
    ){
        return ResponseEntity.ok(transactionsService.makeWithdrawal(iban, amount));
    }

}
