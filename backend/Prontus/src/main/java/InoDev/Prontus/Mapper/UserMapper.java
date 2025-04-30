package InoDev.Prontus.Mapper;

import InoDev.Prontus.DTO.User.UserDTO;
import InoDev.Prontus.Models.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getCpf(),
            user.getName(),
            user.getEmail(),
            user.getCellphone(),
            user.getActive()
        );
    }

    public static User toEntity(UserDTO userDTO) {
        return new User(
            userDTO.getId(),
            userDTO.getCpf() != null ? userDTO.getCpf() : "",
            userDTO.getName() != null ? userDTO.getName() : "",
            userDTO.getEmail() != null ? userDTO.getEmail() : "",
            null,
            null,
            userDTO.getCellphone() != null ? userDTO.getCellphone() : "",
            userDTO.getActive() != null ? userDTO.getActive() : false,
            null
        );
    }
}