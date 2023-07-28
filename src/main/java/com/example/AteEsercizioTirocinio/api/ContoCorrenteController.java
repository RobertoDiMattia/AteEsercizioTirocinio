package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.service.ContoCorrenteService;
import com.example.AteEsercizioTirocinio.DTO.ContoCorrenteDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/contoCorrente")
@RestController
public class ContoCorrenteController {

    @Autowired
    private ContoCorrenteService contoCorrenteService;

    @PostMapping("/create")
    public ResponseEntity<ContoCorrente> addContoCorrente(@RequestBody @Valid ContoCorrenteDto contoCorrenteDto){
        return ResponseEntity.ok(contoCorrenteService.addContoCorrente(contoCorrenteDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContoCorrente> retrieveContoCorrenteById(@PathVariable Long id) {
        return ResponseEntity.ok(contoCorrenteService.retrieveContoCorrenteById(id));
    }
}
