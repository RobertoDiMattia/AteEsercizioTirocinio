package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.ContoCorrenteMapper;
import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.transactionsDto.ContoCorrenteDto;

public class ContoCorrenteService {

    public ContoCorrenteDto addContoCorrente(ContoCorrenteDto contoCorrenteDto) {
        ContoCorrente contoCorrente = ContoCorrenteMapper.INSTANCE.contoCorrenteDtoToContoCorrente(contoCorrenteDto);
        ContoCorrente savedContoCorrente = contoCorrenteRepository.save(contoCorrente);
        return ContoCorrenteMapper.INSTANCE.contoCorrenteToContoCorrenteDto(savedContoCorrente);
    }

    public ContoCorrenteDto updateContoCorrente(Long id, ContoCorrenteDto contoCorrenteDto) {
        ContoCorrente contoCorrente = contoCorrenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conto corrente non trovato"));

        ContoCorrente updatedContoCorrente = ContoCorrenteMapper.INSTANCE.contoCorrenteDtoToContoCorrente(contoCorrenteDto);
        updatedContoCorrente.setId(id); // Assicurati che l'id del conto corrente sia impostato correttamente
        ContoCorrente savedContoCorrente = contoCorrenteRepository.save(updatedContoCorrente);
        return ContoCorrenteMapper.INSTANCE.contoCorrenteToContoCorrenteDto(savedContoCorrente);
    }
}
