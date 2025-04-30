package InoDev.Prontus.DTO.Doctor;

import lombok.Data;

@Data
public class DoctorSummaryDTO {
    private long id;
    private String crm;
    private String name;
    private String specialtyName; 
}
