package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.service.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/transactions")
@RestController
public class TransactionsController {

    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/{pan}")
    public ResponseEntity<List<Transactions>> retrieveTransactionById(@PathVariable String pan) {
        return ResponseEntity.ok(transactionsService.retrieveTransactionById(pan));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> makeDeposit(
            @RequestParam @Valid String pan,
            @RequestParam @Valid double amount
    ){
        return ResponseEntity.ok(transactionsService.makeDeposit(pan, amount));
    }

//    @PostMapping("/withdrawal")
//    public ResponseEntity<Transactions> makeWithdrawal(@RequestBody @Valid TransactionDto transactionDto){
//        return ResponseEntity.ok(transactionsService.makeWithdrawal(transactionDto));
//    }

}
