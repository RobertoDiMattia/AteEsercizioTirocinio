package com.example.AteEsercizioTirocinio.repository;

import com.example.AteEsercizioTirocinio.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndRetrieveUser() {

        var user = User.builder()
                .firstName("Robi")
                .lastName("Dima")
                .email("ro@example.com")
                .build();

        userRepository.save(user);

        Optional<User> retrievedUser = userRepository.findById(user.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals(user.getFirstName(), retrievedUser.get().getFirstName());
    }
}