package InoDev.Prontus.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import InoDev.Prontus.DTO.AvailableTime.*;
import InoDev.Prontus.Models.AvailableTime;

@Mapper(componentModel = "spring")
public interface AvailableTimeMapper {
    AvailableTimeMapper INSTANCE = Mappers.getMapper(AvailableTimeMapper.class);

    @Mapping(source = "doctorId", target = "doctor.id")
    @Mapping(source = "startTime", target = "starttime", qualifiedByName = "localTimeToSqlTime")
    @Mapping(source = "endTime", target = "endtime", qualifiedByName = "localTimeToSqlTime")
    AvailableTime toModel(CreateAvailableTimeDTO dto);

    @Mapping(source = "doctorId", target = "doctor.id")
    @Mapping(source = "startTime", target = "starttime", qualifiedByName = "localTimeToSqlTime")
    @Mapping(source = "endTime", target = "endtime", qualifiedByName = "localTimeToSqlTime")
    AvailableTime toModel(UpdateAvailableTimeDTO dto);

    @Mapping(source = "doctor.id", target = "doctorId")
    @Mapping(source = "starttime", target = "startTime", qualifiedByName = "sqlTimeToLocalTime")
    @Mapping(source = "endtime", target = "endTime", qualifiedByName = "sqlTimeToLocalTime")
    AvailableTimeDTO toDTO(AvailableTime availableTime);

    default java.sql.Time localTimeToSqlTime(java.time.LocalTime localTime) {
        return localTime == null ? null : java.sql.Time.valueOf(localTime);
    }

    default java.time.LocalTime sqlTimeToLocalTime(java.sql.Time sqlTime) {
        return sqlTime == null ? null : sqlTime.toLocalTime();
    }
}