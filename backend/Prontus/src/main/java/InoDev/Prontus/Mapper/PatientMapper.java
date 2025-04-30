package InoDev.Prontus.Mapper;

import InoDev.Prontus.DTO.Patient.PatientDTO;
import InoDev.Prontus.Models.Patient;

public class PatientMapper {

    public static PatientDTO toDTO(Patient patient) {
        return new PatientDTO(
            patient.getId(),
            patient.getName(),
            patient.getCpf(),
            patient.getDataNasc(),
            patient.getCellphone(),
            patient.getEmail(),
            AddressMapper.toDTO(patient.getAddress())
        );
    }

    public static Patient toEntity(PatientDTO patientDTO) {
        return new Patient(
            patientDTO.getId(),
            patientDTO.getName(),
            patientDTO.getCpf(),
            patientDTO.getDataNasc(),
            patientDTO.getCellphone(),
            patientDTO.getEmail(),
            AddressMapper.toEntity(patientDTO.getAddress())
        );
    }
}