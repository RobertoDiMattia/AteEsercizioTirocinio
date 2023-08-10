package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.dto.UserCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.UserEditDto;
import com.example.AteEsercizioTirocinio.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User creationDtoToEntity(UserCreationRequestDto userCreationRequestDto);

    User editDtoToEntity(UserEditDto userEditDto);
}
