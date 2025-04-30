package InoDev.Prontus.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDTO {
    private String name;
    private String email;
    private String cellphone;
    private Boolean active;
}
