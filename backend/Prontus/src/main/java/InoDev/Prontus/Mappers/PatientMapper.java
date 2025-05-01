package InoDev.Prontus.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import InoDev.Prontus.DTO.Patient.*;
import InoDev.Prontus.Models.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mapping(source = "addressId", target = "address.id")
    Patient toModel(CreatePatientDTO dto);

    @Mapping(source = "addressId", target = "address.id")
    Patient toModel(UpdatePatientDTO dto);

    @Mapping(source = "address.id", target = "addressId")
    PatientDTO toDTO(Patient patient);

    PatientSummaryDTO toSummaryDTO(Patient patient);
}