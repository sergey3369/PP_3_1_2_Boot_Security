package ru.kata.spring.boot_security.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mappings({
            @Mapping(target = "dtoId", source = "user.id"),
            @Mapping(target = "dtoName", source = "user.name"),
            @Mapping(target = "dtoLastName", source = "user.lastName"),
            @Mapping(target = "dtoAge", source = "user.age"),
            @Mapping(target = "dtoRoles", source = "user.roles")
    })
    UserDto toDto(User user);
}
