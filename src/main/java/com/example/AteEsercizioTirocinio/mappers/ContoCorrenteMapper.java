package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.ContoCorrente;
import com.example.AteEsercizioTirocinio.DTO.ContoCorrenteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContoCorrenteMapper {

    ContoCorrente DtoToEntity(ContoCorrenteDto contoCorrenteDto);

    ContoCorrenteDto EntityToDto(ContoCorrente contoCorrente);
}
