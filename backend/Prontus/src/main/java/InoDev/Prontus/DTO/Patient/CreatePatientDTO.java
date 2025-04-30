package InoDev.Prontus.DTO.Patient;

import lombok.Data;

import java.sql.Date;

@Data
public class CreatePatientDTO {
    private String name;
    private String cpf;
    private Date dataNasc;
    private String cellphone;
    private String email;
    private long addressId;
}