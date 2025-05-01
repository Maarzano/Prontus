package InoDev.Prontus.Mappers;

import InoDev.Prontus.DTO.AvailableTime.*;
import InoDev.Prontus.Models.AvailableTime;
import InoDev.Prontus.Models.Doctor;

public class AvailableTimeMapper {

    public static AvailableTime toModel(CreateAvailableTimeDTO dto, Doctor doctor) {
        AvailableTime availableTime = new AvailableTime();
        availableTime.setDoctor(doctor);
        availableTime.setDaysweek(dto.getDaysweek());
        availableTime.setStarttime(java.sql.Time.valueOf(dto.getStartTime()));
        availableTime.setEndtime(java.sql.Time.valueOf(dto.getEndTime()));
        return availableTime;
    }

    public static AvailableTime toModel(UpdateAvailableTimeDTO dto, AvailableTime availableTime, Doctor doctor) {
        availableTime.setDoctor(doctor);
        availableTime.setDaysweek(dto.getDaysweek());
        availableTime.setStarttime(java.sql.Time.valueOf(dto.getStartTime()));
        availableTime.setEndtime(java.sql.Time.valueOf(dto.getEndTime()));
        return availableTime;
    }

    public static AvailableTimeDTO toDTO(AvailableTime availableTime) {
        return new AvailableTimeDTO(
            availableTime.getId(),
            availableTime.getDoctor().getId(),
            availableTime.getDaysweek(),
            availableTime.getStarttime().toLocalTime(),
            availableTime.getEndtime().toLocalTime()
        );
    }
}