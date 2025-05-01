package InoDev.Prontus.Mappers;

import InoDev.Prontus.DTO.User.*;
import InoDev.Prontus.Models.User;

public class UserMapper {

    public static User toModel(CreateUserDTO dto) {
        User user = new User();
        user.setCpf(dto.getCpf());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setCellphone(dto.getCellphone());
        user.setActive(true); // Default value
        return user;
    }

    public static User toModel(UpdateUserDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setCellphone(dto.getCellphone());
        user.setActive(dto.getActive());
        return user;
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getCpf(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.getCellphone(),
            user.getActive(),
            user.getCreatedAt()
        );
    }
}