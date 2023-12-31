package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.service.CheckingAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/checkingAccount")
@RestController
@RequiredArgsConstructor
public class CheckingAccountController {

    private final CheckingAccountService checkingAccountService;

    @PostMapping
    public ResponseEntity<CheckingAccountDto> addContoCorrente(@RequestBody @Valid CheckingAccountCreationRequestDto checkingAccountCreationRequestDto) {
        return ResponseEntity.ok(checkingAccountService.addCheckingAccount(checkingAccountCreationRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckingAccountDto> retrieveContoCorrenteById(@PathVariable Long id) {
        return ResponseEntity.ok(checkingAccountService.retrieveCheckingAccountById(id));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(checkingAccountService.retrieveBalanceById(id));
    }

    @GetMapping("/{id}/last5transactions")
    public ResponseEntity<List<TransactionDto>> getLast5Transactions(@PathVariable Long id) {
        return ResponseEntity.ok(checkingAccountService.retrieveLastFiveTransactions(id));
    }

}
