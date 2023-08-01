package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToEntity(UserDto userDto);

    UserDto entityToDto(User user);
}
