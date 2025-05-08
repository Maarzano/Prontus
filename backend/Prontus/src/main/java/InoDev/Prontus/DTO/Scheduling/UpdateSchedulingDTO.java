package InoDev.Prontus.DTO.Scheduling;

import java.time.LocalDateTime;

import InoDev.Prontus.Utils.Enums.StatusScheduling;
import lombok.Data;

@Data
public class UpdateSchedulingDTO {
    private long patient;
    private long doctor;
    private LocalDateTime dateTime;
    private StatusScheduling statusScheduling;
}
