package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.model.User;
import com.example.AteEsercizioTirocinio.transactionsDto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
