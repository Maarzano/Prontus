package InoDev.Prontus.DTO.AvailableTime;

import java.sql.Time;

import InoDev.Prontus.Utils.Enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateAvailableTime {
    private long doctorId;
    private WeekDay daysweek;
    private Time starttime;
    private Time endtime;
}
