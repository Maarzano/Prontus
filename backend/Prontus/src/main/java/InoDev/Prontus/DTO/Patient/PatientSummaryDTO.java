package InoDev.Prontus.DTO.Patient;

import lombok.Data;

@Data
public class PatientSummaryDTO {
    private long id;
    private String name;
    private String cpf;
    private String email;
}