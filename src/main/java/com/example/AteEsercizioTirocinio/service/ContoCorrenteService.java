package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.ContoCorrenteMapper;
import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.repository.ContoCorrenteRepository;
import com.example.AteEsercizioTirocinio.DTO.ContoCorrenteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContoCorrenteService {

    private final ContoCorrenteRepository contoCorrenteRepository;

    private final ContoCorrenteMapper contoCorrenteMapper;

    public ContoCorrente addContoCorrente(ContoCorrenteDto contoCorrenteDto) {
        return contoCorrenteRepository.save(contoCorrenteMapper.DtoToEntity(contoCorrenteDto));
    }

    public ContoCorrente retrieveContoCorrenteById(Long id) {
        return contoCorrenteRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("contoCorrente not found whit id: " + id));
    }
}
