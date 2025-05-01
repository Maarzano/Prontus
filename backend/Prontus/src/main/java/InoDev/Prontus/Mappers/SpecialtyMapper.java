package InoDev.Prontus.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import InoDev.Prontus.DTO.Specialty.*;
import InoDev.Prontus.Models.Specialty;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {
    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    Specialty toModel(CreateSpecialtyDTO dto);

    Specialty toModel(UpdateSpecialtyDTO dto);

    SpecialtyDTO toDTO(Specialty specialty);
}