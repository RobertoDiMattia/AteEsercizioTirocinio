package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CheckingAccountMapper {

    CheckingAccount dtoToEntity(CheckingAccountDto checkingAccountDto);

    CheckingAccountDto entityToDto(CheckingAccount checkingAccount);

    List<TransactionDto> listEntityToListDto(List<TransactionDto> checkingAccount);
}
