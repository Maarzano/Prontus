package InoDev.Prontus.DTO.Doctor;

import lombok.Data;

@Data
public class createDoctorDTO {
    private String crm;
    private long userId;
    private long specialtyId;
    private long addressId;

}
