package InoDev.Prontus.Mappers;

import InoDev.Prontus.DTO.Doctor.*;
import InoDev.Prontus.Models.Doctor;
import InoDev.Prontus.Models.Specialty;
import InoDev.Prontus.Models.Address;

public class DoctorMapper {

    public static Doctor toModel(CreateDoctorDTO dto, Specialty specialty, Address address) {
        Doctor doctor = new Doctor();
        doctor.setCrm(dto.getCrm());
        doctor.setSpecialty(specialty);
        doctor.setAddress(address);
        return doctor;
    }

    public static Doctor toModel(UpdateDoctorDTO dto, Doctor doctor, Specialty specialty, Address address) {
        doctor.setCrm(dto.getCrm());
        doctor.setSpecialty(specialty);
        doctor.setAddress(address);
        return doctor;
    }

    public static DoctorDTO toDTO(Doctor doctor) {
        return new DoctorDTO(
            doctor.getId(),
            doctor.getCrm(),
            doctor.getUser().getId(),
            doctor.getSpecialty().getId(),
            doctor.getAddress() != null ? doctor.getAddress().getId() : 0
        );
    }

    public static DoctorSummaryDTO toSummaryDTO(Doctor doctor) {
        return new DoctorSummaryDTO(
            doctor.getId(),
            doctor.getCrm(),
            doctor.getUser().getName(),
            doctor.getSpecialty().getSpecialty().name()
        );
    }
}