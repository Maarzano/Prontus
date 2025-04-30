package InoDev.Prontus.DTO.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientSummaryDTO {
    private long id;
    private String name;
    private String cpf;
    private String email;
}