package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionsMapper {

    Transactions dtoToEntity(TransactionDto transactionDto);

    TransactionDto entityToDto(Transactions transactions);
}
