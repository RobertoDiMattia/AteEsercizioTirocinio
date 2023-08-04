package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.ContoCorrenteCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.dto.UserDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.ContoCorrenteMapper;
import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.ContoCorrenteRepository;
import com.example.AteEsercizioTirocinio.dto.ContoCorrenteDto;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContoCorrenteService {

    private final ContoCorrenteRepository contoCorrenteRepository;
    private final TransactionsService transactionsService;
    private final ContoCorrenteMapper contoCorrenteMapper;

    public ContoCorrente addContoCorrente(ContoCorrenteCreationRequestDto contoCorrenteCreationRequestDto) {
        var contoCorrenteId = contoCorrenteCreationRequestDto.getId();

        ContoCorrente contoCorrente = contoCorrenteRepository.findById(contoCorrenteId)
                .orElseThrow(() -> new IllegalArgumentException("Conto corrente not found wiht id " + contoCorrenteId));

        var userDto = contoCorrenteCreationRequestDto.getUser();
        if(userDto != null){
            return contoCorrente;
        }

        var user = User.builder()
                .id(contoCorrenteCreationRequestDto.getId())
                .name(contoCorrenteCreationRequestDto.getName())
                .lastName(contoCorrenteCreationRequestDto.getLastName())
                .email(contoCorrenteCreationRequestDto.getEmail())
                .build();

        contoCorrente.setUser(user);
        return contoCorrenteRepository.save(contoCorrente);
    }

    public ContoCorrenteDto retrieveContoCorrenteById(Long id) {
        ContoCorrente contoCorrente = contoCorrenteRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("c/c not found whit id: " + id));

        return contoCorrenteMapper.entityToDto(contoCorrente);
    }

    public double retrieveBalanceByIban(Long id) {
        return transactionsService.retrieveBalance(id);
    }

    public List<TransactionDto> retrieveLastFiveTransactions(Long contoCorrenteId) {
        return transactionsService.retrieveLastFiveTransactions(contoCorrenteId);
    }

}
