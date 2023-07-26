package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.service.TransactionsService;
import com.example.AteEsercizioTirocinio.transactionsDto.TransactionDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/create")
    public ResponseEntity<TransactionDto> addTransaction (@RequestBody @Valid TransactionDto transactionDto){
        return ResponseEntity.ok(transactionsService.addTransaction(transactionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionsService.getTransactionById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionsService.updateTransaction(id, transactionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionsService.deleteTransaction(id));
    }

}
