package InoDev.Prontus.DTO.User;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private String name;
    private String email;
    private String cellphone;
    private Boolean active;
}
