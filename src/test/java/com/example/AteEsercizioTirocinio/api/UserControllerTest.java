package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    //web mvc test mockare la chiamata al controller (non come il service anche se ci può stare)
//    @Test
//    void addUser() {
//
//        var userDto = new UserEditDto(1L, "John", "Doe", "john@example.com",9L);
//        var user = new User();
//        when(userService.addUser(any())).thenReturn(user);
//        ResponseEntity<User> response = userController.addUser(userDto);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(user, response.getBody());
//        verify(userService).addUser(userDto);
//    }

    @Test
    void retrieveUserById() {

        Long userId = 1L;
        User retrievedUser = new User();
        when(userService.retrieveUserById(userId)).thenReturn(retrievedUser);
        ResponseEntity<User> response = userController.retrieveUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(retrievedUser, response.getBody());
        verify(userService).retrieveUserById(userId);
    }

//    @Test
//    void updateUser() {
//        var userCreationRequestDto = new UserCreationRequestDto(1L, "John", "Doe", "john@example.com");
//        User updatedUser = new User();
//        when(userService.updateUser(any(UserCreationRequestDto.class))).thenReturn(updatedUser);
//        ResponseEntity<User> response = userController.updateUser(userCreationRequestDto);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(updatedUser, response.getBody());
//        verify(userService).updateUser(any(UserCreationRequestDto.class));
//    }

    @Test
    void deleteUser() {
        Long userId = 1L;
        ResponseEntity response = userController.deleteUser(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).deleteUser(userId);
    }
}