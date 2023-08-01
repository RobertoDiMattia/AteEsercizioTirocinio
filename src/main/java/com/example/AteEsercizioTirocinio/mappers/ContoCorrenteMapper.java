package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.dto.ContoCorrenteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContoCorrenteMapper {

    ContoCorrente dtoToEntity(ContoCorrenteDto contoCorrenteDto);

    ContoCorrenteDto entityToDto(ContoCorrente contoCorrente);
}
