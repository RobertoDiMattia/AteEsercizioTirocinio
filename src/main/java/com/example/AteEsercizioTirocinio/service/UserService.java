package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.UserMapper;
import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.dto.UserDto;
import com.example.AteEsercizioTirocinio.repository.ContoCorrenteRepository;
import com.example.AteEsercizioTirocinio.repository.TransactionsRepository;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ContoCorrenteRepository contoCorrenteRepository;
    private final TransactionsRepository transactionsRepository;
    private final UserMapper userMapper;

    public User addUser(UserDto userDto) {
        var uuid = UUID.randomUUID().toString();
        var user = userMapper.dtoToEntity(userDto);
        userRepository.save(user);
        var contoCorrente = ContoCorrente.builder()
                .iban(uuid)
                .userId(user.getId())
                .build();
        contoCorrenteRepository.save(contoCorrente);
        var transaction = Transactions.builder()
                .numConto(contoCorrente.getIban())
                .balance(0)
                .dateTime(LocalDate.now())
                .transactionType("")
                .build();
        transactionsRepository.save(transaction);
        return user;
    }

    public User retrieveUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User updateUser(UserDto userDto) {
        var id = userDto.getId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userRepository.save(userMapper.dtoToEntity(userDto));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
