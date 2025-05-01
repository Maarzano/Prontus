package InoDev.Prontus.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import InoDev.Prontus.DTO.Scheduling.*;
import InoDev.Prontus.Models.Scheduling;

@Mapper(componentModel = "spring")
public interface SchedulingMapper {
    SchedulingMapper INSTANCE = Mappers.getMapper(SchedulingMapper.class);

    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "doctorId", target = "doctor.id")
    Scheduling toModel(CreateSchedulingDTO dto);

    @Mapping(source = "patientId", target = "patient.id")
    @Mapping(source = "doctorId", target = "doctor.id")
    Scheduling toModel(UpdateSchedulingDTO dto);

    @Mapping(source = "patient.id", target = "patientId")
    @Mapping(source = "doctor.id", target = "doctorId")
    SchedulingResponseDTO toDTO(Scheduling scheduling);
}