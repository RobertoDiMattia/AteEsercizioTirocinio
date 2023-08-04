package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.service.TransactionsService;

import jakarta.validation.constraints.Min;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/transactions")
@RestController
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @GetMapping("/{contoCorrenteId}")
    public ResponseEntity<List<TransactionDto>> retrieveTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionsService.retrieveTransactionById(id));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> makeDeposit(
            @RequestParam Long id,
            @RequestParam @Min(10) double amount
    ){
        return ResponseEntity.ok(transactionsService.makeDeposit(id, amount));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TransactionDto> makeWithdrawal(
            @RequestParam Long id,
            @RequestParam @Min(10) double amount
    ){
        return ResponseEntity.ok(transactionsService.makeWithdrawal(id, amount));
    }

}
