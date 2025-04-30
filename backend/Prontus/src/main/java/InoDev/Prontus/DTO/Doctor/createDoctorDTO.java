package InoDev.Prontus.DTO.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class createDoctorDTO {
    private String crm;
    private long userId;
    private long specialtyId;
    private long addressId;

}
