package InoDev.Prontus.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import InoDev.Prontus.DTO.Doctor.*;
import InoDev.Prontus.Models.Doctor;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mapping(source = "specialtyId", target = "specialty.id")
    @Mapping(source = "addressId", target = "address.id")
    Doctor toModel(CreateDoctorDTO dto);

    @Mapping(source = "specialtyId", target = "specialty.id")
    @Mapping(source = "addressId", target = "address.id")
    Doctor toModel(UpdateDoctorDTO dto);

    @Mapping(source = "specialty.id", target = "specialtyId")
    @Mapping(source = "address.id", target = "addressId")
    DoctorDTO toDTO(Doctor doctor);

    @Mapping(source = "specialty.specialty", target = "specialtyName")
    DoctorSummaryDTO toSummaryDTO(Doctor doctor);
}