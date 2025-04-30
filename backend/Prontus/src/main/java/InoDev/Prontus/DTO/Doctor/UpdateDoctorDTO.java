package InoDev.Prontus.DTO.Doctor;

import lombok.Data;

@Data
public class UpdateDoctorDTO {
    private String crm;
    private Long specialtyId;
    private Long addressId; 
}
