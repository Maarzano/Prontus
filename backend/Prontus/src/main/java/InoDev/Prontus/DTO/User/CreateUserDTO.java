package InoDev.Prontus.DTO.User;

import InoDev.Prontus.Utils.Enums.AdmRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserDTO {
    private String cpf;
    private String name;
    private String email;
    private String password;
    private AdmRole role;
    private String cellphone;
}