package InoDev.Prontus.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String cpf;
    private String name;
    private String email;
    private String password;
    private String cellphone;
    private Boolean active;
}