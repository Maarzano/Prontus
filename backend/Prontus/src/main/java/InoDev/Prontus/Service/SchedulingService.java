package InoDev.Prontus.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import InoDev.Prontus.DTO.Scheduling.*;
import InoDev.Prontus.Exceptions.ResourceNotFoundException;
import InoDev.Prontus.Mappers.SchedulingMapper;
import InoDev.Prontus.Models.Scheduling;
import InoDev.Prontus.Models.Doctor;
import InoDev.Prontus.Models.Patient;
import InoDev.Prontus.Repository.SchedulingRepository;
import InoDev.Prontus.Repository.DoctorRepository;
import InoDev.Prontus.Repository.PatientRepository;

@Service
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public SchedulingService(SchedulingRepository schedulingRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.schedulingRepository = schedulingRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public SchedulingResponseDTO createScheduling(CreateSchedulingDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + dto.getPatientId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));

        Scheduling scheduling = SchedulingMapper.toModel(dto, patient, doctor);
        scheduling = schedulingRepository.save(scheduling);
        return SchedulingMapper.toDTO(scheduling);
    }

    @Transactional
    public SchedulingResponseDTO updateScheduling(Long id, UpdateSchedulingDTO dto) {
        Scheduling scheduling = schedulingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scheduling not found with id: " + id));
        Patient patient = patientRepository.findById(dto.getPatient())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + dto.getPatient()));
        Doctor doctor = doctorRepository.findById(dto.getDoctor())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctor()));

        scheduling = SchedulingMapper.toModel(dto, scheduling, patient, doctor);
        scheduling = schedulingRepository.save(scheduling);
        return SchedulingMapper.toDTO(scheduling);
    }

    public SchedulingResponseDTO getSchedulingById(Long id) {
        Scheduling scheduling = schedulingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scheduling not found with id: " + id));
        return SchedulingMapper.toDTO(scheduling);
    }

    public List<SchedulingResponseDTO> getAllSchedulings() {
        return schedulingRepository.findAll().stream()
                .map(SchedulingMapper::toDTO)
                .toList();
    }

    @Transactional
    public void deleteScheduling(Long id) {
        if (!schedulingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Scheduling not found with id: " + id);
        }
        schedulingRepository.deleteById(id);
    }
}