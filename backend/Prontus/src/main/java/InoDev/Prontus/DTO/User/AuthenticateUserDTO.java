package InoDev.Prontus.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateUserDTO {
    private String email;
    private String password;
}