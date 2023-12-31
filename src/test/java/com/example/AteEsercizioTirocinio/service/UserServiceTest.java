package com.example.AteEsercizioTirocinio.service;

import com.example.AteEsercizioTirocinio.dto.UserCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.UserDto;
import com.example.AteEsercizioTirocinio.dto.UserEditDto;
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

import static org.assertj.core.api.Assertions.assertThat;
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

    private static final String EMAIL = "rob@mail.it";
    private static final String FIRST_NAME = "Roby";
    private static final String LAST_NAME = "Dima";
    private static final Long EXISTING_USER_ID = 1L;

    @Test
    public void testAddUser() {
        var userCreationRequestDto = mockedUserCreationDto();
        var userDto = mockedUserDto();

        when(userMapper.creationDtoToEntity(any())).thenReturn(new User());
        when(userMapper.entityToDto((User) any())).thenReturn(userDto);

        var addedUser = userService.addUser(userCreationRequestDto);

        assertThat(addedUser).isNotNull();
        assertThat(addedUser.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(addedUser.getLastName()).isEqualTo(LAST_NAME);
        assertThat(addedUser.getEmail()).isEqualTo(EMAIL);

        //esercitazione
        assertNotNull(addedUser);
        assertEquals(FIRST_NAME, addedUser.getFirstName());
        assertEquals(LAST_NAME, addedUser.getLastName());
        assertEquals(EMAIL, addedUser.getEmail());

        verify(userMapper, times(1)).creationDtoToEntity(userCreationRequestDto);
    }

    @Test
    public void testRetrieveUserById_UserFound() {
        var user = mockedUser();
        var result = mockedUserDto();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userMapper.entityToDto(user)).thenReturn(result);

        var retrievedUser = userService.retrieveUserById(EXISTING_USER_ID);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(EXISTING_USER_ID);
        assertEquals(FIRST_NAME, retrievedUser.getFirstName());
        assertEquals(LAST_NAME, retrievedUser.getLastName());
        assertEquals(EMAIL, retrievedUser.getEmail());

        //ESERCITAZIONE
        assertNotNull(result);
        assertEquals(EXISTING_USER_ID, result.getId());
        assertEquals(FIRST_NAME, result.getFirstName());
        assertEquals(LAST_NAME, result.getLastName());
        assertEquals(EMAIL, result.getEmail());
    }

    @Test
    public void testRetrieveUserByIdNotFound() {
        Long userId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.retrieveUserById(userId));

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testUpdateUserFound() {
        var editDto = mockedUserEditDto();
        var user = mockedUser();
        var userDto = mockedUserDto();

        when(userRepository.existsById(EXISTING_USER_ID)).thenReturn(true);
        when(userMapper.editDtoToEntity(any())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.entityToDto(user)).thenReturn(userDto);

        var resultUser = userService.updateUser(EXISTING_USER_ID, editDto);

        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getId()).isEqualTo(EXISTING_USER_ID);
        assertThat(resultUser.getFirstName()).isEqualTo(editDto.getFirstName());
        assertThat(resultUser.getLastName()).isEqualTo(editDto.getLastName());
        assertThat(resultUser.getEmail()).isEqualTo(editDto.getEmail());
    }

    @Test
    public void testUpdateUserNotFound() {
        when(userRepository.existsById(EXISTING_USER_ID)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> userService.updateUser(EXISTING_USER_ID, new UserEditDto()));

        verify(userRepository, times(1)).existsById(EXISTING_USER_ID);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(EXISTING_USER_ID);

        userService.deleteUser(EXISTING_USER_ID);

        verify(userRepository, times(1)).deleteById(EXISTING_USER_ID);
    }

    private User mockedUser() {
        return User.builder()
                .id(EXISTING_USER_ID)
                .email(EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();
    }

    private UserEditDto mockedUserEditDto() {
        return UserEditDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .build();
    }

    private UserDto mockedUserDto() {
        return UserDto.builder()
                .id(EXISTING_USER_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .checkingAccountId(10L)
                .build();
    }

    private UserCreationRequestDto mockedUserCreationDto() {
        return UserCreationRequestDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .build();
    }
}