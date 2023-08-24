package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.UserCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.UserDto;
import com.example.AteEsercizioTirocinio.dto.UserEditDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.UserMapper;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto addUser(UserCreationRequestDto userCreationRequestDto) {
        var user = userRepository.save(userMapper.creationDtoToEntity(userCreationRequestDto));
        return userMapper.entityToDto(user);
    }

    public UserDto retrieveUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.entityToDto(user);
    }

    public UserDto updateUser(Long id, UserEditDto userEditDto) {

        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found");
        }

        var user = userMapper.editDtoToEntity(userEditDto);
        return userMapper.entityToDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

//    public List<UserDto> retrieveAllUsers(int pageNumb, int pageSize) {
//        var pageble = PageRequest.of(pageNumb, pageSize);
//        var user = userRepository.findAll(pageble).getContent();
//        return userMapper.entityToDto(user);
//    } LIST

//    public Page<UserDto> retrieveAllUsers(int pageNum, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNum, pageSize);
//        Page<User> userPage = userRepository.findAll(pageable);
//        return userPage.map(userMapper::entityToDto);
//    } PAGE

    public Page<UserDto> retrieveAllUsers(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize))
                .map(userMapper::entityToDto);
    }
}
