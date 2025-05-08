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
        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());
        if (dto.getRole() != null) user.setRole(dto.getRole());
        if (dto.getCellphone() != null) user.setCellphone(dto.getCellphone());
        if (dto.getActive() != null) user.setActive(dto.getActive());
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