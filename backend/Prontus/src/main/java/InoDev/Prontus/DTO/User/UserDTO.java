package InoDev.Prontus.DTO.User;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String cpf;
    private String name;
    private String email;
    private String cellphone;
    private Boolean active;
}