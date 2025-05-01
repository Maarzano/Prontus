package InoDev.Prontus.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import InoDev.Prontus.DTO.MedicalRecord.*;
import InoDev.Prontus.Exceptions.ResourceNotFoundException;
import InoDev.Prontus.Mappers.MedicalRecordMapper;
import InoDev.Prontus.Models.MedicalRecord;
import InoDev.Prontus.Models.Scheduling;
import InoDev.Prontus.Repository.MedicalRecordRepository;
import InoDev.Prontus.Repository.SchedulingRepository;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final SchedulingRepository schedulingRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, SchedulingRepository schedulingRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.schedulingRepository = schedulingRepository;
    }

    @Transactional
    public MedicalRecordDTO createMedicalRecord(CreateMedicalRecordDTO dto) {
        Scheduling scheduling = schedulingRepository.findById(dto.getSchedulingId())
                .orElseThrow(() -> new ResourceNotFoundException("Scheduling not found with id: " + dto.getSchedulingId()));

        MedicalRecord medicalRecord = MedicalRecordMapper.toModel(dto, scheduling);
        medicalRecord = medicalRecordRepository.save(medicalRecord);
        return MedicalRecordMapper.toDTO(medicalRecord);
    }

    @Transactional
    public MedicalRecordDTO updateMedicalRecord(Long id, UpdateMedicalRecordDTO dto) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord not found with id: " + id));

        medicalRecord = MedicalRecordMapper.toModel(dto, medicalRecord);
        medicalRecord = medicalRecordRepository.save(medicalRecord);
        return MedicalRecordMapper.toDTO(medicalRecord);
    }

    public MedicalRecordDTO getMedicalRecordById(Long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord not found with id: " + id));
        return MedicalRecordMapper.toDTO(medicalRecord);
    }

    public List<MedicalRecordDTO> getAllMedicalRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(MedicalRecordMapper::toDTO)
                .toList();
    }

    @Transactional
    public void deleteMedicalRecord(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("MedicalRecord not found with id: " + id);
        }
        medicalRecordRepository.deleteById(id);
    }
}