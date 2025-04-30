package InoDev.Prontus.DTO.MedicalRecord;

import InoDev.Prontus.Models.Scheduling;
import InoDev.Prontus.Utils.Enums.Diagnostics;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MedicalRecordDTO {
    private long id;
    private Scheduling scheduling_id;
    private LocalDateTime date_Register;
    private Diagnostics diagnostic;
    private String recepie;
    private String anotation;
}
