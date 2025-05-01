package InoDev.Prontus.DTO.MedicalRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

import InoDev.Prontus.Utils.Enums.Diagnostics;

@Data
@AllArgsConstructor
public class UpdateMedicalRecordDTO {
    private LocalDateTime dateRegister;
    private Diagnostics diagnostic;
    private String recepie;
    private String anotation;
}
