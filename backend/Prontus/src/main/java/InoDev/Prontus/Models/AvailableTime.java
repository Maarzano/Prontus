package InoDev.Prontus.Models;

import java.sql.Time;
import java.time.LocalTime;
import java.time.LocalTime;

import InoDev.Prontus.Utils.Enums.WeekDay;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "availabletime")
public class AvailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_time_id")
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id", unique = false, nullable = false, referencedColumnName = "doctor_id")
    private Doctor doctor;

    @Column(name = "daysweek", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private WeekDay daysweek;
    
    @Column(name = "start_time", nullable = false, unique = false)
    private LocalTime starttime;

    @Column(name = "end_time", nullable = false, unique = false)
    private LocalTime endtime;
}
