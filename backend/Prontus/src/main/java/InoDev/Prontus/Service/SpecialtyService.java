package InoDev.Prontus.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import InoDev.Prontus.DTO.Specialty.*;
import InoDev.Prontus.Exceptions.ResourceNotFoundException;
import InoDev.Prontus.Mappers.SpecialtyMapper;
import InoDev.Prontus.Models.Specialty;
import InoDev.Prontus.Repository.SpecialtyRepository;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Transactional
    public SpecialtyDTO createSpecialty(CreateSpecialtyDTO dto) {
        Specialty specialty = SpecialtyMapper.toModel(dto);
        specialty = specialtyRepository.save(specialty);
        return SpecialtyMapper.toDTO(specialty);
    }

    @Transactional
    public SpecialtyDTO updateSpecialty(Long id, UpdateSpecialtyDTO dto) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + id));

        specialty = SpecialtyMapper.toModel(dto, specialty);
        specialty = specialtyRepository.save(specialty);
        return SpecialtyMapper.toDTO(specialty);
    }

    public SpecialtyDTO getSpecialtyById(Long id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + id));
        return SpecialtyMapper.toDTO(specialty);
    }

    public List<SpecialtyDTO> getAllSpecialties() {
        return specialtyRepository.findAll().stream()
                .map(SpecialtyMapper::toDTO)
                .toList();
    }

    @Transactional
    public void deleteSpecialty(Long id) {
        if (!specialtyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Specialty not found with id: " + id);
        }
        specialtyRepository.deleteById(id);
    }
}