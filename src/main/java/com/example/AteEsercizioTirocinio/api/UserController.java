package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.UserCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.UserEditDto;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid UserCreationRequestDto userCreationRequestDto) {
        return ResponseEntity.ok(userService.addUser(userCreationRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.retrieveUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserEditDto userEditDto) {
        return ResponseEntity.ok(userService.updateUser(id, userEditDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
