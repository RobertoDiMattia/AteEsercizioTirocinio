package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.ContoCorrenteCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.TransactionDto;
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
    private final UserRepository userRepository;

//    public ContoCorrente addContoCorrente(ContoCorrenteCreationRequestDto contoCorrenteCreationRequestDto) {
//        return contoCorrenteRepository.save(contoCorrenteMapper.dtoToEntity(contoCorrenteCreationRequestDto));
//    }

    public ContoCorrente addContoCorrente(ContoCorrenteCreationRequestDto contoCorrenteCreationRequestDto) {
        Long contoCorrenteId = contoCorrenteCreationRequestDto.getId();
        if (contoCorrenteId == null) {
            throw new IllegalArgumentException("you need id for create conto corrente");
        }

        ContoCorrente contoCorrente = contoCorrenteRepository.findById(contoCorrenteId)
                .orElseThrow(() -> new IllegalArgumentException("Conto corrente not found wiht id " + contoCorrenteId));

        var userDto = contoCorrente.getUser();
        if (userDto == null) {
            throw new IllegalArgumentException("User information is missing in the request.");
        }

        var user = new User();  // Creazione di un nuovo utente
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //problemi con il builder
//        User user = new User.UserBuilder()
//                .id(userDto.getId())
//                .name(userDto.getName())
//                .lastName(userDto.getLastName())
//                .email(userDto.getEmail())
//                .build();

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
