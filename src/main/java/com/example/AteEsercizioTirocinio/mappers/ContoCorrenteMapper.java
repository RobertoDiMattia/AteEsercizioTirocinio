package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.transactionsDto.ContoCorrenteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContoCorrenteMapper {

    ContoCorrenteMapper INSTANCE = Mappers.getMapper(ContoCorrenteMapper.class);

    @Mapping(target = "id", ignore = true)
    ContoCorrente contoCorrenteDtoToContoCorrente(ContoCorrenteDto contoCorrenteDto);

    ContoCorrenteDto contoCorrenteToContoCorrenteDto(ContoCorrente contoCorrente);
}
