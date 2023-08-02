package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.service.ContoCorrenteService;
import com.example.AteEsercizioTirocinio.dto.ContoCorrenteDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/contoCorrente")
@RestController
@RequiredArgsConstructor
public class ContoCorrenteController {

    private final ContoCorrenteService contoCorrenteService;

    @PostMapping("/create")
    public ResponseEntity<ContoCorrente> addContoCorrente(@RequestBody @Valid ContoCorrenteDto contoCorrenteDto){
        return ResponseEntity.ok(contoCorrenteService.addContoCorrente(contoCorrenteDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContoCorrenteDto> retrieveContoCorrenteById(@PathVariable Long id) {
        return ResponseEntity.ok(contoCorrenteService.retrieveContoCorrenteById(id));
    }

    @GetMapping("/{iban}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable String iban) {
        return ResponseEntity.ok(contoCorrenteService.retrieveBalance(iban));
    }

    @GetMapping("/{iban}/last5transactions")
    public ResponseEntity<List<TransactionDto>> getLast5Transactions(@PathVariable String iban) {
        return ResponseEntity.ok(contoCorrenteService.retrieveLastFiveTransactions(iban));
    }

}
