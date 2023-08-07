package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.UserDto;
import com.example.AteEsercizioTirocinio.mappers.UserMapper;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public static final String EMAIL = "rob@mail.it";
    public static final String NAME = "Roby";
    public static final String LAST_NAME = "Dima";

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void addUser() {
        when(userRepository.save(any())).thenReturn(getUser());
        var out = userService.addUser(new UserDto());

        assertEquals(1L, out.getId());
        assertEquals(EMAIL, out.getEmail());
    }

    @Test
    void retrieveUserById() {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(getUser()));
        var out = userService.retrieveUserById(2L);

        assertEquals(1L, out.getId());
        assertEquals(NAME, out.getFirstName());
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