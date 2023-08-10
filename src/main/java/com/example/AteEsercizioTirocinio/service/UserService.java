package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.UserCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.UserEditDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.UserMapper;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User addUser(UserCreationRequestDto userCreationRequestDto) {
        return userRepository.save(userMapper.creationDtoToEntity(userCreationRequestDto));
    }

    public User retrieveUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User updateUser(Long id, UserEditDto userEditDto) {

        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }

        return userRepository.save(userMapper.editDtoToEntity(userEditDto));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
