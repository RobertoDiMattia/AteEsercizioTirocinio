package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.service.UserService;
import com.example.AteEsercizioTirocinio.transactionsDto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> retrieveUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.retrieveUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
