package InoDev.Prontus.DTO.Doctor;

import InoDev.Prontus.DTO.Address.AddressDTO;
import InoDev.Prontus.DTO.Specialty.SpecialtyDTO;
import InoDev.Prontus.DTO.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorDTO {
    private long id;
    private String crm;
    private UserDTO user;
    private SpecialtyDTO specialty;
    private AddressDTO address;
}
