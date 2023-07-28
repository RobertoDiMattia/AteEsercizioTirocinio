package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.DTO.TransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionsMapper {

    Transactions DtoToEntity(TransactionDto transactionDto);

    TransactionDto EntityToDto(Transactions transactions);
}
