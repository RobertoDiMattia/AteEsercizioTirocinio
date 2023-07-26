package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.service.ContoCorrenteService;
import com.example.AteEsercizioTirocinio.transactionsDto.ContoCorrenteDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContoCorrenteController {

    @Autowired
    private ContoCorrenteService contoCorrenteService;

    @PostMapping("/create")
    public ResponseEntity<ContoCorrenteDto> addContoCorrente (@RequestBody @Valid ContoCorrenteDto contoCorrenteDto){
        return ResponseEntity.ok(userService.addContoCorrente(contoCorrenteDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContoCorrenteDto> getContoCorrenteById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getContoCorrenteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContoCorrenteDto> updateContoCorrente(@PathVariable Long id, @RequestBody ContoCorrenteDto contoCorrenteDto) {
        return ResponseEntity.ok(userService.updateUser(id, contoCorrenteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContoCorrente(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteContoCorrente(id));
    }
}
