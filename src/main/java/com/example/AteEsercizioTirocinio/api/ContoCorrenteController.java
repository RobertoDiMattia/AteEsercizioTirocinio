package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.service.ContoCorrenteService;
import com.example.AteEsercizioTirocinio.dto.ContoCorrenteDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/contoCorrente")
@RestController
public class ContoCorrenteController {

    private final ContoCorrenteService contoCorrenteService;

    public ContoCorrenteController(ContoCorrenteService contoCorrenteService) {
        this.contoCorrenteService = contoCorrenteService;
    }


    @PostMapping("/create")
    public ResponseEntity<ContoCorrente> addContoCorrente(@RequestBody @Valid ContoCorrenteDto contoCorrenteDto){
        return ResponseEntity.ok(contoCorrenteService.addContoCorrente(contoCorrenteDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContoCorrenteDto> retrieveContoCorrenteById(@PathVariable Long id) {
        return ResponseEntity.ok(contoCorrenteService.retrieveContoCorrenteById(id));
    }

//    @GetMapping("/{id}/balance")
//    public ResponseEntity<Double> getBalance(@PathVariable Long id) {
//        double balance = contoCorrenteService.getBalance(id);
//        return ResponseEntity.ok(balance);
//    }

//    @GetMapping("/{id}/last5transactions")
//    public ResponseEntity<List<TransactionDto>> getLast5Transactions(@PathVariable Long id) {
//        List<TransactionDto> transactions = contoCorrenteService.getLast5Transactions(id);
//        return ResponseEntity.ok(transactions);
//    }

}
