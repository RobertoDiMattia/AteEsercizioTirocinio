package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.service.TransactionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/transactions")
@RestController
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @GetMapping("/{pan}")
    public ResponseEntity<List<TransactionDto>> retrieveTransactionById(@PathVariable String iban) {
        return ResponseEntity.ok(transactionsService.retrieveTransactionById(iban));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> makeDeposit(
            @RequestParam @Valid String iban,
            @RequestParam @Valid double amount
    ){
        return ResponseEntity.ok(transactionsService.makeDeposit(iban, amount));
    }

//    @PostMapping("/withdrawal")
//    public ResponseEntity<Transactions> makeWithdrawal(@RequestBody @Valid TransactionDto transactionDto){
//        return ResponseEntity.ok(transactionsService.makeWithdrawal(transactionDto));
//    }

}
