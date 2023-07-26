package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.service.UserService;
import com.example.AteEsercizioTirocinio.transactionsDto.ContoCorrenteDto;
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
    public ResponseEntity<UserDto> addUser (@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(UserService.addUser(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(UserService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(UserService.updateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(UserService.deleteUser(id));
    }

}
