package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.dto.UserCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.UserEditDto;
import com.example.AteEsercizioTirocinio.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToEntity(UserCreationRequestDto userCreationRequestDto);

    User dtoToEntity(UserEditDto userEditDto);

}
