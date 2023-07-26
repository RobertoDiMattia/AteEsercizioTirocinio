package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import com.example.AteEsercizioTirocinio.transactionsDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto addUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        return userRepository.save(user);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }


    public UserDto updateUser(Long id, UserDto updatedUserDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found with id: " + updatedUserDto.getId()));

        user.setName(updatedUserDto.getName());
        user.setLastName(updatedUserDto.getLastName());
        user.setEmail(updatedUserDto.getEmail());

        userRepository.save(updatedUserDto);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
