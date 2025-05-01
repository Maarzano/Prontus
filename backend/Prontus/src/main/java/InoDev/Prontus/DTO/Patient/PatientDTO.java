package InoDev.Prontus.DTO.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PatientDTO {
    private long id;
    private String name;
    private String cpf;
    private LocalDate dataNasc;
    private String cellphone;
    private String email;
    private long addressId;
}