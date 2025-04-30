package InoDev.Prontus.DTO.AvailableTime;

import java.sql.Time;

import InoDev.Prontus.Models.Doctor;
import InoDev.Prontus.Utils.Enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAvailableTime {
    private Doctor doctor;
    private WeekDay daysweek;
    private Time starttime;
    private Time endtime;
}
