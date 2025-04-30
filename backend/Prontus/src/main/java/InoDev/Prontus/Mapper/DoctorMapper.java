package InoDev.Prontus.Mapper;

import InoDev.Prontus.DTO.Doctor.DoctorDTO;
import InoDev.Prontus.Models.Doctor;

public class DoctorMapper {

    public static DoctorDTO toDTO(Doctor doctor) {
        return new DoctorDTO(
            doctor.getId(),
            doctor.getCrm(),
            UserMapper.toDTO(doctor.getUser()),
            SpecialtyMapper.toDTO(doctor.getSpecialty()),
            AddressMapper.toDTO(doctor.getAddress())
        );
    }

    public static Doctor toEntity(DoctorDTO doctorDTO) {
        return new Doctor(
            doctorDTO.getId(),
            doctorDTO.getCrm(),
            UserMapper.toEntity(doctorDTO.getUser()),
            SpecialtyMapper.toEntity(doctorDTO.getSpecialty()),
            AddressMapper.toEntity(doctorDTO.getAddress())
        );
    }
}