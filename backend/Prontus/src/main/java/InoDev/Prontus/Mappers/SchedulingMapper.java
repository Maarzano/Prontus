package InoDev.Prontus.Mappers;

import InoDev.Prontus.DTO.Scheduling.*;
import InoDev.Prontus.Models.Scheduling;
import InoDev.Prontus.Models.Doctor;
import InoDev.Prontus.Models.Patient;

public class SchedulingMapper {

    public static Scheduling toModel(CreateSchedulingDTO dto, Patient patient, Doctor doctor) {
        if (patient == null || doctor == null) {
            throw new IllegalArgumentException("Patient and Doctor cannot be null");
        }

        Scheduling scheduling = new Scheduling();
        scheduling.setPatient(patient);
        scheduling.setDoctor(doctor);
        scheduling.setDateTime(dto.getDateTime());
        scheduling.setStatusScheduling(dto.getStatusScheduling());
        return scheduling;
    }

    public static Scheduling toModel(UpdateSchedulingDTO dto, Scheduling scheduling, Patient patient, Doctor doctor) {
        if (scheduling == null || patient == null || doctor == null) {
            throw new IllegalArgumentException("Scheduling, Patient, and Doctor cannot be null");
        }

        scheduling.setPatient(patient);
        scheduling.setDoctor(doctor);
        scheduling.setDateTime(dto.getDateTime());
        scheduling.setStatusScheduling(dto.getStatusScheduling());
        return scheduling;
    }

    public static SchedulingResponseDTO toDTO(Scheduling scheduling) {
        if (scheduling == null) {
            throw new IllegalArgumentException("Scheduling cannot be null");
        }

        return new SchedulingResponseDTO(
            scheduling.getId(),
            scheduling.getPatient() != null ? scheduling.getPatient().getId() : 0,
            scheduling.getDoctor() != null ? scheduling.getDoctor().getId() : 0,
            scheduling.getDateTime(),
            scheduling.getStatusScheduling()
        );
    }
}