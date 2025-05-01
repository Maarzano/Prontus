package InoDev.Prontus.DTO.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdatePatientDTO {
    private String name;
    private LocalDate dataNasc;
    private String cellphone;
    private String email;
    private long addressId;
}