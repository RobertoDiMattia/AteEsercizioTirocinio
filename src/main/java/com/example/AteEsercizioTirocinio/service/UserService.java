package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.UserMapper;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.dto.UserDto;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ContoCorrenteService contoCorrenteService;
    private final TransactionsService transactionsService;
    private final UserMapper userMapper;

    public User addUser(UserDto userDto) {
        var user = userMapper.dtoToEntity(userDto);
        userRepository.save(user);
        var conto = contoCorrenteService.createFromUser(user);
        var transaction = transactionsService.createFromContoCorrente(conto);
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
