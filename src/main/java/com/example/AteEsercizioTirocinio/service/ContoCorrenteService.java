package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.ContoCorrenteMapper;
import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.ContoCorrenteRepository;
import com.example.AteEsercizioTirocinio.dto.ContoCorrenteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContoCorrenteService {

    private final ContoCorrenteRepository contoCorrenteRepository;
    private final TransactionsService transactionsService;
    private final ContoCorrenteMapper contoCorrenteMapper;

    public ContoCorrente addContoCorrente(ContoCorrenteDto contoCorrenteDto) {
        return contoCorrenteRepository.save(contoCorrenteMapper.dtoToEntity(contoCorrenteDto));
    }

    public ContoCorrenteDto retrieveContoCorrenteById(Long id) {
        ContoCorrente contoCorrente = contoCorrenteRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("c/c not found whit id: " + id));

        return contoCorrenteMapper.entityToDto(contoCorrente);
    }

    public double retrieveBalance(String iban) {
        return transactionsService.retrieveBalance(iban);
    }

    public List<TransactionDto> retrieveLastFiveTransactions(String iban) {
        return transactionsService.retrieveLastFiveTransactions(iban);
    }

    public ContoCorrente createFromUser(User user) {
        var contoCorrente = ContoCorrente.builder()
                .iban(UUID.randomUUID().toString())
                .userId(user.getId())
                .build();
        return contoCorrenteRepository.save(contoCorrente);
    }
}
