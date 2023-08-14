package com.example.AteEsercizioTirocinio.mappers;

import com.example.AteEsercizioTirocinio.dto.UserCreationRequestDto;
import com.example.AteEsercizioTirocinio.dto.UserDto;
import com.example.AteEsercizioTirocinio.dto.UserEditDto;
import com.example.AteEsercizioTirocinio.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User creationDtoToEntity(UserCreationRequestDto userCreationRequestDto);

    UserDto entityToDto(User user);

//    Page<UserDto> entityToDto(Page<User> user);

    User editDtoToEntity(UserEditDto userEditDto);

    UserDto editEntityToDto(UserEditDto userEditDto);
}
