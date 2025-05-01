package InoDev.Prontus.Mappers;

import InoDev.Prontus.DTO.MedicalRecord.*;
import InoDev.Prontus.Models.MedicalRecord;
import InoDev.Prontus.Models.Scheduling;

public class MedicalRecordMapper {

    public static MedicalRecord toModel(CreateMedicalRecordDTO dto, Scheduling scheduling) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setSchedulingId(scheduling);
        medicalRecord.setDateRegister(java.time.LocalDateTime.now());
        medicalRecord.setDiagnostic(dto.getDiagnostic());
        medicalRecord.setRecepie(dto.getRecepie());
        medicalRecord.setAnotation(dto.getAnotation());
        return medicalRecord;
    }

    public static MedicalRecord toModel(UpdateMedicalRecordDTO dto, MedicalRecord medicalRecord) {
        medicalRecord.setDateRegister(dto.getDateRegister());
        medicalRecord.setDiagnostic(dto.getDiagnostic());
        medicalRecord.setRecepie(dto.getRecepie());
        medicalRecord.setAnotation(dto.getAnotation());
        return medicalRecord;
    }

    public static MedicalRecordDTO toDTO(MedicalRecord medicalRecord) {
        return new MedicalRecordDTO(
            medicalRecord.getId(),
            medicalRecord.getSchedulingId().getId(),
            medicalRecord.getDateRegister(),
            medicalRecord.getDiagnostic(),
            medicalRecord.getRecepie(),
            medicalRecord.getAnotation()
        );
    }
}