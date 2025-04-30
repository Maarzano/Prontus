package InoDev.Prontus.DTO.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorSummaryDTO {
    private long id;
    private String crm;
    private String name;
    private String specialtyName; 
}
