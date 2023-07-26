package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.transactionsDto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
