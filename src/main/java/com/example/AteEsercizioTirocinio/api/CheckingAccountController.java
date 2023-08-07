package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import com.example.AteEsercizioTirocinio.service.CheckingAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/contoCorrente")
@RestController
@RequiredArgsConstructor
public class CheckingAccountController {

    private final CheckingAccountService checkingAccountService;

    @PostMapping("/create")
    public ResponseEntity<CheckingAccount> addContoCorrente(@RequestBody @Valid CheckingAccountCreationRequestDto checkingAccountCreationRequestDto){
        return ResponseEntity.ok(checkingAccountService.addCheckingAccount(checkingAccountCreationRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckingAccountDto> retrieveContoCorrenteById(@PathVariable Long id) {
        return ResponseEntity.ok(checkingAccountService.retrieveContoCorrenteById(id));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(checkingAccountService.retrieveBalanceByIban(id));
    }

    @GetMapping("/{id}/last5transactions")
    public ResponseEntity<List<CheckingAccountDto>> getLast5Transactions(@PathVariable Long id) {
        return ResponseEntity.ok(checkingAccountService.retrieveLastFiveTransactions(id));
    }

}
