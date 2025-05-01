package InoDev.Prontus.Mappers;

import InoDev.Prontus.DTO.Specialty.*;
import InoDev.Prontus.Models.Specialty;

public class SpecialtyMapper {

    public static Specialty toModel(CreateSpecialtyDTO dto) {
        Specialty specialty = new Specialty();
        specialty.setSpecialty(dto.getSpecialty());
        return specialty;
    }

    public static Specialty toModel(UpdateSpecialtyDTO dto, Specialty specialty) {
        specialty.setSpecialty(dto.getSpecialty());
        return specialty;
    }

    public static SpecialtyDTO toDTO(Specialty specialty) {
        return new SpecialtyDTO(
            specialty.getId(),
            specialty.getSpecialty()
        );
    }
}