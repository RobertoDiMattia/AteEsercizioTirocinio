package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.ContoCorrenteMapper;
import com.example.AteEsercizioTirocinio.mappers.TransactionsMapper;
import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.repository.ContoCorrenteRepository;
import com.example.AteEsercizioTirocinio.dto.ContoCorrenteDto;
import com.example.AteEsercizioTirocinio.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContoCorrenteService {

    private final ContoCorrenteRepository contoCorrenteRepository;
    private final TransactionsRepository transactionsRepository;
    private final TransactionsMapper transactionsMapper;
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
        var transactions = transactionsRepository.findByNumConto(iban);
        var transaction = transactions.stream().findFirst().orElseThrow();
        return transaction.getBalance();
    }

    public List<TransactionDto> retrieveLastFiveTransactions(String iban) {
        var transaction = transactionsRepository.findLastFiveTransaction(iban);
        return transactionsMapper.listEntityToListDto(transaction);
    }
}
