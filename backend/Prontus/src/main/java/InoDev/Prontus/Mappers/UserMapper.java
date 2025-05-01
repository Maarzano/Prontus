package InoDev.Prontus.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import InoDev.Prontus.DTO.User.*;
import InoDev.Prontus.Models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(CreateUserDTO dto);

    User toModel(UpdateUserDTO dto);

    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);
}