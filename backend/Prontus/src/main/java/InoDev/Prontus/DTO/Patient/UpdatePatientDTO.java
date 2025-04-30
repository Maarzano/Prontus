package InoDev.Prontus.DTO.Patient;

import lombok.Data;

import java.sql.Date;

@Data
public class UpdatePatientDTO {
    private String name;
    private Date dataNasc;
    private String cellphone;
    private String email;
    private long addressId;
}