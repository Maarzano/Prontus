package InoDev.Prontus.DTO.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateDoctorDTO {
    private String crm;
    private long specialtyId;
    private long addressId; 
}
