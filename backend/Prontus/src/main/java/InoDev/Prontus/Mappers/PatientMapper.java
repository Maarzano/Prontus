package InoDev.Prontus.Mappers;

import InoDev.Prontus.DTO.Patient.*;
import InoDev.Prontus.Models.Patient;
import InoDev.Prontus.Models.Address;

public class PatientMapper {

    public static Patient toModel(CreatePatientDTO dto, Address address) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setCpf(dto.getCpf());
        patient.setDataNasc(java.sql.Date.valueOf(dto.getDataNasc()));
        patient.setCellphone(dto.getCellphone());
        patient.setEmail(dto.getEmail());
        patient.setAddress(address);
        return patient;
    }

    public static Patient toModel(UpdatePatientDTO dto, Patient patient, Address address) {
        patient.setName(dto.getName());
        patient.setDataNasc(java.sql.Date.valueOf(dto.getDataNasc()));
        patient.setCellphone(dto.getCellphone());
        patient.setEmail(dto.getEmail());
        patient.setAddress(address);
        return patient;
    }

    public static PatientDTO toDTO(Patient patient) {
        return new PatientDTO(
            patient.getId(),
            patient.getName(),
            patient.getCpf(),
            patient.getDataNasc().toLocalDate(),
            patient.getCellphone(),
            patient.getEmail(),
            patient.getAddress() != null ? patient.getAddress().getId() : 0
        );
    }

    public static PatientSummaryDTO toSummaryDTO(Patient patient) {
        return new PatientSummaryDTO(
            patient.getId(),
            patient.getName(),
            patient.getCpf(),
            patient.getEmail()
        );
    }
}