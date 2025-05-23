package InoDev.Prontus.DTO.Scheduling;

import java.time.LocalDateTime;

import InoDev.Prontus.Utils.Enums.StatusScheduling;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSchedulingDTO {
    private long patientId;
    private long doctorId;
    private LocalDateTime dateTime;
    private StatusScheduling statusScheduling;
}
