package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.mappers.ContoCorrenteMapper;
import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.repository.ContoCorrenteRepository;
import com.example.AteEsercizioTirocinio.transactionsDto.ContoCorrenteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContoCorrenteService {

    @Autowired

    private ContoCorrenteRepository contoCorrenteRepository;

    private final ContoCorrenteMapper contoCorrenteMapper;

    public ContoCorrenteService(ContoCorrenteMapper contoCorrenteMapper) {
        this.contoCorrenteMapper = contoCorrenteMapper;
    }

    public ContoCorrente addContoCorrente(ContoCorrenteDto contoCorrenteDto) {
        return contoCorrenteRepository.save(contoCorrenteMapper.contoCorrenteDtoToContoCorrente(contoCorrenteDto));
    }

    public ContoCorrente retrieveContoCorrenteById(Long id) {
        return contoCorrenteRepository.retrieveByContoCorrenteId(id);
    }
}
