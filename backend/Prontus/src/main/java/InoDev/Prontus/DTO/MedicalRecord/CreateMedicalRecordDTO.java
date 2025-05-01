package InoDev.Prontus.DTO.MedicalRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

import InoDev.Prontus.Utils.Enums.Diagnostics;

@Data
@AllArgsConstructor
public class CreateMedicalRecordDTO {
    private long schedulingId;
    private Diagnostics diagnostic;
    private String recepie;
    private String anotation;
}
