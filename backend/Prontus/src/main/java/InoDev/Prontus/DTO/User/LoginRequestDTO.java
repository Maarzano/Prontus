package InoDev.Prontus.DTO.User;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
