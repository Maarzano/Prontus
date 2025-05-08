package InoDev.Prontus.Mappers;

import InoDev.Prontus.DTO.AvailableTime.*;
import InoDev.Prontus.Models.AvailableTime;
import InoDev.Prontus.Models.Doctor;

public class AvailableTimeMapper {

    public static AvailableTime toModel(CreateAvailableTimeDTO dto, Doctor doctor) {
        AvailableTime availableTime = new AvailableTime();
        availableTime.setDoctor(doctor);
        availableTime.setDaysweek(dto.getDaysweek());
        availableTime.setStarttime(dto.getStartTime());
        availableTime.setEndtime(dto.getEndTime());
        return availableTime;
    }

    public static AvailableTime toModel(UpdateAvailableTimeDTO dto, AvailableTime availableTime, Doctor doctor) {
        if (availableTime == null || doctor == null) {
            throw new IllegalArgumentException("AvailableTime and Doctor cannot be null");
        }
        availableTime.setDoctor(doctor);
    
        if (dto.getDaysweek() != null) {
            availableTime.setDaysweek(dto.getDaysweek());
        }
    
        if (dto.getStartTime() != null) {
            availableTime.setStarttime(dto.getStartTime());
        }
    
        if (dto.getEndTime() != null) {
            availableTime.setEndtime(dto.getEndTime());
        }
    
        return availableTime;
    }
    

    public static AvailableTimeDTO toDTO(AvailableTime availableTime) {
        return new AvailableTimeDTO(
            availableTime.getId(),
            availableTime.getDoctor().getId(),
            availableTime.getDaysweek(),
            availableTime.getStarttime(),
            availableTime.getEndtime()
        );
    }
}