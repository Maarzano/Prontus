package InoDev.Prontus.DTO.User;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String cpf;
    private String name;
    private String email;
    private String cellphone;
    private String password;
}