package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.UserDto;
import com.example.AteEsercizioTirocinio.exceptions.NotFoundException;
import com.example.AteEsercizioTirocinio.mappers.UserMapper;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    public static final String EMAIL = "rob@mail.it";
    public static final String NAME = "Roby";
    public static final String LAST_NAME = "Dima";

    @Test
    void addUser() {
        when(userRepository.save(any())).thenReturn(getUser());
        var out = userService.addUser(new UserDto());

        assertEquals(1L, out.getId());
        assertEquals(EMAIL, out.getEmail());
    }

    @Test
    public void testAddUser() {

        UserDto userDto = new UserDto();
        User userEntity = new User();

        when(userMapper.dtoToEntity(userDto)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        User result = userService.addUser(userDto);

        assertNotNull(result);
        assertEquals(userEntity, result);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void retrieveUserById() {

        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(getUser()));
        var out = userService.retrieveUserById(2L);

        assertEquals(1L, out.getId());
        assertEquals(NAME, out.getFirstName());
    }

    //testare anche i path dove ci sono le eccezioni
    //convenzione di scrittura dei nomi dei metodi di test
    @Test
    public void RetrieveUserById_UserFound() {
        Long userId = 1L;
        User userEntity = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        User result = userService.retrieveUserById(userId);
        assertNotNull(result);
        assertEquals(userEntity, result);
    }

    @Test
    public void RetrieveUserById_UserNotFound() {

        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            userService.retrieveUserById(userId);
        });
    }

    @Test
    void updateUser() {

    }

    @Test
    void deleteUser() {
    }

    private User getUser() {
        return User.builder()
                .id(1L)
                .email(EMAIL)
                .firstName(NAME)
                .lastName(LAST_NAME)
                .build();
    }
}