package InoDev.Prontus.DTO.Doctor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorDTO {
    private long id;
    private String crm;
    private long userId;
    private long specialty;
    private long addressId;
}
