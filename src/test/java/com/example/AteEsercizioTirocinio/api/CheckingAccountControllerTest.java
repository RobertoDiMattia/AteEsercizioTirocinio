package com.example.AteEsercizioTirocinio.api;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.service.CheckingAccountService;
import com.example.AteEsercizioTirocinio.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CheckingAccountController.class)
class CheckingAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckingAccountService checkingAccountService;

    private static final Long EXISTING_CHECKING_ACCOUNT_ID = 5L;
    private static final Long EXISTING_USER_ID = 9L;
    private static final double BALANCE = 1000.0;
    private static final String IBAN = "IT9bb12a-72d0-4dda-ab4d-ac83fae15";

    @Test
    void testAddCheckingAccount() throws Exception {
        var checkingAccountDto = mockedCheckingAccountDto();

        when(checkingAccountService.addCheckingAccount(any())).thenReturn(checkingAccountDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/checkingAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.json(mockedCheckingAccountCreationDto())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(EXISTING_CHECKING_ACCOUNT_ID))
                .andExpect(jsonPath("$.userId").value(EXISTING_USER_ID))
                .andExpect(jsonPath("$.balance").value(BALANCE))
                .andExpect(jsonPath("$.iban").value(IBAN))
                .andExpect(jsonPath("$.transactions").isEmpty());
    }

    @Test
    void testRetrieveCheckingAccountById() throws Exception {
        var checkingAccountDto = mockedCheckingAccountDto();

        when(checkingAccountService.retrieveCheckingAccountById(anyLong())).thenReturn(checkingAccountDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/checkingAccount/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(EXISTING_CHECKING_ACCOUNT_ID))
                .andExpect(jsonPath("$.userId").value(EXISTING_USER_ID))
                .andExpect(jsonPath("$.balance").value(BALANCE))
                .andExpect(jsonPath("$.iban").value(IBAN))
                .andExpect(jsonPath("$.transactions").isEmpty());
    }

    @Test
    void testGetBalance() throws Exception {
        when(checkingAccountService.retrieveBalanceById(anyLong())).thenReturn(BALANCE);

        var response = mockMvc.perform(MockMvcRequestBuilders.get("/api/checkingAccount/1/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.json((mockedCheckingAccountDto()))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(Double.valueOf(response)).isEqualTo(BALANCE);
    }

    private CheckingAccountCreationRequestDto mockedCheckingAccountCreationDto() {
        return CheckingAccountCreationRequestDto.builder()
                .userId(EXISTING_USER_ID)
                .build();
    }

    private CheckingAccountDto mockedCheckingAccountDto() {
        return CheckingAccountDto.builder()
                .id(EXISTING_CHECKING_ACCOUNT_ID)
                .balance(BALANCE)
                .iban(IBAN)
                .transactions(Collections.emptyList())
                .UserId(EXISTING_USER_ID)
                .build();
    }
}