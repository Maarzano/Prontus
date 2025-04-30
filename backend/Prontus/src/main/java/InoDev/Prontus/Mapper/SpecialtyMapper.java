package InoDev.Prontus.Mapper;

import InoDev.Prontus.DTO.Specialty.SpecialtyDTO;
import InoDev.Prontus.Models.Specialty;

public class SpecialtyMapper {

    public static SpecialtyDTO toDTO(Specialty specialty) {
        return new SpecialtyDTO(
            specialty.getId(),
            specialty.getSpecialty()
        );
    }

    public static Specialty toEntity(SpecialtyDTO specialtyDTO) {
        return new Specialty(
            specialtyDTO.getId(),
            specialtyDTO.getSpecialty()
        );
    }
}