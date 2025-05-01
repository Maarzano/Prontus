package InoDev.Prontus.DTO.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class PatientDTO {
    private long id;
    private String name;
    private String cpf;
    private Date dataNasc;
    private String cellphone;
    private String email;
    private long addressId;
}