package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.transactionsDto.ContoCorrenteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContoCorrenteMapper {

    ContoCorrente contoCorrenteDtoToContoCorrente(ContoCorrenteDto contoCorrenteDto);

    ContoCorrenteDto contoCorrenteToContoCorrenteDto(ContoCorrente contoCorrente);
}
