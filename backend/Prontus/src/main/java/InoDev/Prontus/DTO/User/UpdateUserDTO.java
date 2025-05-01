package InoDev.Prontus.DTO.User;

import InoDev.Prontus.Utils.Enums.AdmRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDTO {
    private String name;
    private String email;
    private String password;
    private AdmRole role;
    private String cellphone;
    private Boolean active;
}
