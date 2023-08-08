package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.service.TransactionService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/transactions")
@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{checkingAccount}")
    public ResponseEntity<List<TransactionDto>> retrieveTransactionByCheckingAccountId(@PathVariable Long checkingAccount) {
        return ResponseEntity.ok(transactionService.retrieveTransactionByCheckingAccountId(checkingAccount));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> makeDeposit(
            @RequestParam Long id,
            @RequestParam @Min(10) double amount
    ) {
        return ResponseEntity.ok(transactionService.makeDeposit(id, amount));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<TransactionDto> makeWithdrawal(
            @RequestParam Long id,
            @RequestParam @Min(10) double amount
    ){
        return ResponseEntity.ok(transactionService.makeWithdrawal(id, amount));
    }

}
