package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.Transactions;
import com.example.AteEsercizioTirocinio.transactionsDto.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionsMapper {

    TransactionsMapper INSTANCE = Mappers.getMapper(TransactionsMapper.class);

    Transactions transactionDtoToTransaction(TransactionDto transactionDto);

    TransactionDto transactionToTransactionDto(Transactions transactions);
}
