package InoDev.Prontus.DTO.MedicalRecord;

import InoDev.Prontus.Utils.Enums.Diagnostics;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MedicalRecordDTO {
    private long id;
    private long schedulingId;
    private LocalDateTime dateRegister;
    private Diagnostics diagnostic;
    private String recepie;
    private String anotation;
}
