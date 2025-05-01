package InoDev.Prontus.DTO.Specialty;

import InoDev.Prontus.Utils.Enums.Specialties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateSpecialtyDTO {
    private Specialties specialty;
}