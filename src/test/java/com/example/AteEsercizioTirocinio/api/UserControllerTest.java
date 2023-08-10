package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.UserCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.UserEditDto;
import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static final String EMAIL = "rob@mail.it";
    private static final String FIRST_NAME = "Roby";
    private static final String LAST_NAME = "Dima";
    private static final Long EXISTING_USER_ID = 1L;
    private static final Long EXISTING_CHECKING_ACCOUNT_ID = 9L;

    @Test
    public void testAddUser() throws Exception {
        var userDto = mockedUserCreationDto();
        var user = mockedUser();

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id", is(EXISTING_USER_ID)))
                .andExpect((ResultMatcher) jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect((ResultMatcher) jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect((ResultMatcher) jsonPath("$.email", is(EMAIL)));
    }

    private UserCreationRequestDto mockedUserCreationDto() {
        UserCreationRequestDto userDto = new UserCreationRequestDto();
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setEmail(EMAIL);
        userDto.setCheckingAccountId(EXISTING_CHECKING_ACCOUNT_ID);
        return userDto;
    }

    private User mockedUser() {
        User user = new User();
        user.setId(EXISTING_USER_ID);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        return user;
    }

    @Test
    public void testRetrieveUserById() throws Exception {
        User user = mockedUser();

//        given(userService.retrieveUserById(1L)).willReturn(user);

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id", is(1)))
                .andExpect((ResultMatcher) jsonPath("$.firstName", is("John")))
                .andExpect((ResultMatcher) jsonPath("$.lastName", is("Doe")))
                .andExpect((ResultMatcher) jsonPath("$.email", is("john@example.com")));
    }

    @Test
    public void testUpdateUser() throws Exception {
        var userEditDto = mockedEditDto();
        var updatedUser = mockedUser();

//        given(userService.updateUser(1L, userEditDto)).willReturn(updatedUser);

        mockMvc.perform(put("/api/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userEditDto)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id", is(EXISTING_USER_ID)))
                .andExpect((ResultMatcher) jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect((ResultMatcher) jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect((ResultMatcher) jsonPath("$.email", is(EMAIL)));
    }

    private UserEditDto mockedEditDto() {
        UserEditDto userEditDto = new UserEditDto();
        userEditDto.setFirstName(FIRST_NAME);
        userEditDto.setLastName(LAST_NAME);
        userEditDto.setEmail(EMAIL);
        return userEditDto;
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/user/1"))
                .andExpect(status().isOk());
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}

