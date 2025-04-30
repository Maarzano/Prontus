package InoDev.Prontus.DTO.Doctor;

import InoDev.Prontus.DTO.Address.AddressDTO;
import InoDev.Prontus.DTO.Specialty.SpecialtyDTO;
import InoDev.Prontus.DTO.User.UserDTO;
import lombok.Data;

@Data
public class DoctorDTO {
    private long id;
    private String crm;
    private UserDTO user;
    private SpecialtyDTO specialty;
    private AddressDTO address;
}
