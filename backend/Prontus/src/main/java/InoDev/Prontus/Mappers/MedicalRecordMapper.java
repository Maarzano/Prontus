package InoDev.Prontus.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import InoDev.Prontus.DTO.MedicalRecord.*;
import InoDev.Prontus.Models.MedicalRecord;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {
    MedicalRecordMapper INSTANCE = Mappers.getMapper(MedicalRecordMapper.class);

    @Mapping(source = "schedulingId", target = "scheduling.id")
    MedicalRecord toModel(CreateMedicalRecordDTO dto);

    @Mapping(source = "schedulingId", target = "scheduling.id")
    MedicalRecord toModel(UpdateMedicalRecordDTO dto);

    @Mapping(source = "scheduling.id", target = "schedulingId")
    MedicalRecordDTO toDTO(MedicalRecord medicalRecord);
}