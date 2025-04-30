package InoDev.Prontus.DTO.MedicalRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

import InoDev.Prontus.Utils.Enums.Diagnostics;

@Data
@AllArgsConstructor
public class CreateMedicalRecord {
    private Diagnostics diagnostic;
    private String recepie;
    private String anotation;
}
