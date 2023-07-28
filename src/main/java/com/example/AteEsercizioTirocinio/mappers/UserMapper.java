package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.DTO.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User DtoToEntity(UserDto userDto);

    UserDto EntityToDto(User user);
}
