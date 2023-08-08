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
    public static final String FIRST_NAME = "Roby";
    public static final String LAST_NAME = "Dima";
    private static final Long EXISTING_USER_ID = 1L;

    //fatto senza var per senpai che li odia xD
    @Test
    public void testAddUser() {
        UserDto userDto = new UserDto();
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setEmail(EMAIL);
        User user = new User();
        when(userMapper.dtoToEntity(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        User addedUser = userService.addUser(userDto);
        assertNotNull(addedUser);
        assertEquals(FIRST_NAME, addedUser.getFirstName());
        assertEquals(LAST_NAME, addedUser.getLastName());
        assertEquals(EMAIL, addedUser.getEmail());
        verify(userMapper, times(1)).dtoToEntity(userDto);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testRetrieveUserById_UserFound() {
        Long userId = EXISTING_USER_ID;
        User userEntity = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        User result = userService.retrieveUserById(userId);
        assertNotNull(result);
        assertEquals(userEntity, result);
    }

    @Test
    public void testRetrieveUserByIdNotFound() {
        Long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.retrieveUserById(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setId(EXISTING_USER_ID);
        User user = new User();
        when(userMapper.dtoToEntity(userDto)).thenReturn(user);
        when(userRepository.existsById(EXISTING_USER_ID)).thenReturn(true);
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.updateUser(userDto);
        assertNotNull(updatedUser);
        assertEquals(EXISTING_USER_ID, updatedUser.getId());
        verify(userRepository, times(1)).existsById(EXISTING_USER_ID);
        verify(userMapper, times(1)).dtoToEntity(userDto);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUserNotFound() {
        UserDto userDto = new UserDto();
        userDto.setId(EXISTING_USER_ID);
        when(userRepository.existsById(EXISTING_USER_ID)).thenReturn(false);
        assertThrows(NotFoundException.class, () -> userService.updateUser(userDto));
        verify(userRepository, times(1)).existsById(EXISTING_USER_ID);
        verifyNoMoreInteractions(userMapper, userRepository);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(EXISTING_USER_ID);
        userService.deleteUser(EXISTING_USER_ID);
        verify(userRepository, times(1)).deleteById(EXISTING_USER_ID);
    }

    private User getUser() {
        return User.builder()
                .id(EXISTING_USER_ID)
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();
    }
}