package InoDev.Prontus.DTO.User;

import java.time.LocalDateTime;

import InoDev.Prontus.Utils.Enums.AdmRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String cpf;
    private String name;
    private String email;
    private AdmRole role;
    private String cellphone;
    private Boolean active;
    private LocalDateTime createdAt;
}