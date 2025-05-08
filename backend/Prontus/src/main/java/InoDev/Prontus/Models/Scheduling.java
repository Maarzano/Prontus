package InoDev.Prontus.Models;

import java.time.LocalDateTime;

import InoDev.Prontus.Utils.Enums.StatusScheduling;
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
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedulings")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduling_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false, unique = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", nullable = false, unique = false)
    private Doctor doctor;

    @Column(name = "date_time", nullable = false, unique = false)
    @Future(message= "A data de agendamento tem que ser superior ao dia de hoje!")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_scheduling", nullable = false, unique = false)
    private StatusScheduling statusScheduling;

}
