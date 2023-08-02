package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.UserDto;
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

    @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.retrieveUserById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
