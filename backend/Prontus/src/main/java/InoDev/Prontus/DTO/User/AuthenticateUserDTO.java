package InoDev.Prontus.DTO.User;

import lombok.Data;

@Data
public class AuthenticateUserDTO {
    private String email;
    private String password;
}