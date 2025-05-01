package InoDev.Prontus.DTO.AvailableTime;

import java.time.LocalTime;

import InoDev.Prontus.Utils.Enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAvailableTimeDTO {
    private long doctorId;
    private WeekDay daysweek;
    private LocalTime startTime;
    private LocalTime endTime;
}
