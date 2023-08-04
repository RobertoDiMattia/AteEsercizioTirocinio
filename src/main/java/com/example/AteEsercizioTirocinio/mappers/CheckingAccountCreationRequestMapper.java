package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckingAccountCreationRequestMapper {

    CheckingAccount creationRequestDtoToEntity(CheckingAccountCreationRequestDto checkingAccountCreationRequestDto);

    CheckingAccountDto creationRequestEntityToDto(CheckingAccount checkingAccount);
}

