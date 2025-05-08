package InoDev.Prontus.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import InoDev.Prontus.DTO.Patient.*;
import InoDev.Prontus.Exceptions.ResourceNotFoundException;
import InoDev.Prontus.Mappers.PatientMapper;
import InoDev.Prontus.Models.Patient;
import InoDev.Prontus.Models.Address;
import InoDev.Prontus.Repository.PatientRepository;
import InoDev.Prontus.Repository.AddressRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;

    public PatientService(PatientRepository patientRepository, AddressRepository addressRepository) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public PatientDTO createPatient(CreatePatientDTO dto) {
        Address address = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + dto.getAddressId()));

        Patient patient = PatientMapper.toModel(dto, address);
        patient = patientRepository.save(patient);
        return PatientMapper.toDTO(patient);
    }

    @Transactional
    public PatientDTO updatePatient(Long id, UpdatePatientDTO dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        Address address = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + dto.getAddressId()));

        patient = PatientMapper.toModel(dto, patient, address);
        patient = patientRepository.save(patient);
        return PatientMapper.toDTO(patient);
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return PatientMapper.toDTO(patient);
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::toDTO)
                .toList();
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }
}