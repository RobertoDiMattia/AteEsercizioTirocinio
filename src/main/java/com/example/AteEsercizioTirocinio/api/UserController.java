package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.UserCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.UserDto;
import com.example.AteEsercizioTirocinio.dto.UserEditDto;
import com.example.AteEsercizioTirocinio.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserCreationRequestDto userCreationRequestDto) {
        return ResponseEntity.ok(userService.addUser(userCreationRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> retrieveUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.retrieveUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserEditDto userEditDto) {
        return ResponseEntity.ok(userService.updateUser(id, userEditDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> retrieveUserList(
            @RequestParam(defaultValue = "0") @Min(0) int pageNumb,
            @RequestParam(defaultValue = "10") @Min(1) int pageSize
    ) {
        return ResponseEntity.ok(userService.retrieveAllUsers(pageNumb, pageSize));
    }

//    @GetMapping
//    public ResponseEntity<Page<UserDto>> retrieveUserList(
//            @RequestParam(defaultValue = "0") @Min(0) int pageNumb,
//            @RequestParam(defaultValue = "10") @Min(1) int pageSize
//    ) {
//        return ResponseEntity.ok(userService.retrieveAllUsers(pageNumb, pageSize));
//    }
}
