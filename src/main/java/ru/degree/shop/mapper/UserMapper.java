package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import ru.degree.shop.DTO.user.UserDto;
import ru.degree.shop.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toUser(UserDto userDto);
}
