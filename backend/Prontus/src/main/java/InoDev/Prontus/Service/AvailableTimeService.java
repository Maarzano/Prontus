package InoDev.Prontus.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import InoDev.Prontus.DTO.AvailableTime.*;
import InoDev.Prontus.Exceptions.ResourceNotFoundException;
import InoDev.Prontus.Mappers.AvailableTimeMapper;
import InoDev.Prontus.Models.AvailableTime;
import InoDev.Prontus.Models.Doctor;
import InoDev.Prontus.Repository.AvailbleTimeRepository;
import InoDev.Prontus.Repository.DoctorRepository;

@Service
public class AvailableTimeService {

    private final AvailbleTimeRepository availableTimeRepository;
    private final DoctorRepository doctorRepository;

    public AvailableTimeService(AvailbleTimeRepository availableTimeRepository, DoctorRepository doctorRepository) {
        this.availableTimeRepository = availableTimeRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public AvailableTimeDTO createAvailableTime(CreateAvailableTimeDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));

        AvailableTime availableTime = AvailableTimeMapper.toModel(dto, doctor);
        availableTime = availableTimeRepository.save(availableTime);
        return AvailableTimeMapper.toDTO(availableTime);
    }

    @Transactional
    public AvailableTimeDTO updateAvailableTime(Long id, UpdateAvailableTimeDTO dto) {
        AvailableTime availableTime = availableTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AvailableTime not found with id: " + id));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));

        availableTime = AvailableTimeMapper.toModel(dto, availableTime, doctor);
        availableTime = availableTimeRepository.save(availableTime);
        return AvailableTimeMapper.toDTO(availableTime);
    }

    public AvailableTimeDTO getAvailableTimeById(Long id) {
        AvailableTime availableTime = availableTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AvailableTime not found with id: " + id));
        return AvailableTimeMapper.toDTO(availableTime);
    }

    public List<AvailableTimeDTO> getAllAvailableTimes() {
        return availableTimeRepository.findAll().stream()
                .map(AvailableTimeMapper::toDTO)
                .toList();
    }

    @Transactional
    public void deleteAvailableTime(Long id) {
        if (!availableTimeRepository.existsById(id)) {
            throw new ResourceNotFoundException("AvailableTime not found with id: " + id);
        }
        availableTimeRepository.deleteById(id);
    }
}