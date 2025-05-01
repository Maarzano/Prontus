package InoDev.Prontus.DTO.Specialty;

import InoDev.Prontus.Utils.Enums.Specialties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpecialtyDTO {
    private long id;
    private Specialties specialty;
}
