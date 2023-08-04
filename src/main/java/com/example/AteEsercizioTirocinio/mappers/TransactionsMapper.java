package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.Transaction;
import com.example.AteEsercizioTirocinio.dto.TransactionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionsMapper {

    Transaction dtoToEntity(TransactionDto transactionDto);

    TransactionDto entityToDto(Transaction transaction);

    List<TransactionDto> listEntityToListDto(List<Transaction> transactions);
}
