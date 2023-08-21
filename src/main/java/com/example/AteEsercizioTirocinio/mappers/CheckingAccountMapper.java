package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.dto.CheckingAccountCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.CheckingAccountDto;
import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import com.example.AteEsercizioTirocinio.model.CheckingAccount;
import com.example.AteEsercizioTirocinio.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CheckingAccountMapper {

    CheckingAccountDto entityToDto(CheckingAccount checkingAccount);

    List<TransactionDto> listEntityToListDto(List<Transaction> transactions);

    CheckingAccount creationRequestDtoToEntity(CheckingAccountCreationRequestDto checkingAccountCreationRequestDto);

}
