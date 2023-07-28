package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.service.TransactionsService;
import com.example.AteEsercizioTirocinio.DTO.TransactionDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/transactions")
@RestController
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/create")
    public ResponseEntity<Transactions> addTransaction (@RequestBody @Valid TransactionDto transactionDto){
        return ResponseEntity.ok(transactionsService.addTransaction(transactionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transactions> retrieveTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionsService.retrieveTransactionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transactions> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionsService.updateTransaction(id, transactionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        transactionsService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transactions> makeDeposit(@RequestBody @Valid TransactionDto transactionDto){
        return ResponseEntity.ok(transactionsService.makeDeposit(transactionDto));
    }

    @PostMapping("/withdrawl")
    public ResponseEntity<Transactions> makeWithdrawl(@RequestBody @Valid TransactionDto transactionDto){
        return ResponseEntity.ok(transactionsService.makeWithdrawl(transactionDto));
    }

    @GetMapping("/balance")
    public ResponseEntity<Transactions> getBalance(@RequestParam @Valid TransactionDto transactionDto){
        return ResponseEntity.ok(transactionsService.getBalance(transactionDto));
    }

    @GetMapping("/last5transactions")
    public ResponseEntity<List<Transactions>> getLast5transactions(@RequestParam @Valid String numConto){
        return ResponseEntity.ok(transactionsService.getLast5transactions(numConto));
    }
}
