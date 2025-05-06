package InoDev.Prontus.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import InoDev.Prontus.DTO.Doctor.CreateDoctorDTO;
import InoDev.Prontus.DTO.Doctor.DoctorDTO;
import InoDev.Prontus.DTO.Doctor.UpdateDoctorDTO;
import InoDev.Prontus.Exceptions.ResourceNotFoundException;
import InoDev.Prontus.Mappers.DoctorMapper;
import InoDev.Prontus.Models.Address;
import InoDev.Prontus.Models.Doctor;
import InoDev.Prontus.Models.Specialty;
import InoDev.Prontus.Models.User;
import InoDev.Prontus.Repository.AddressRepository;
import InoDev.Prontus.Repository.DoctorRepository;
import InoDev.Prontus.Repository.SpecialtyRepository;
import InoDev.Prontus.Repository.UserRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository, AddressRepository addressRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public DoctorDTO createDoctor(CreateDoctorDTO dto) {
        Specialty specialty = specialtyRepository.findById(dto.getSpecialtyId())
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + dto.getSpecialtyId()));
        Address address = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + dto.getAddressId()));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        Doctor doctor = DoctorMapper.toModel(dto, specialty, address, user);
        doctor = doctorRepository.save(doctor);
        return DoctorMapper.toDTO(doctor);
    }

    @Transactional
    public DoctorDTO updateDoctor(Long id, UpdateDoctorDTO dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        Specialty specialty = specialtyRepository.findById(dto.getSpecialtyId())
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with id: " + dto.getSpecialtyId()));
        Address address = addressRepository.findById(dto.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + dto.getAddressId()));

        doctor = DoctorMapper.toModel(dto, doctor, specialty, address);
        doctor = doctorRepository.save(doctor);
        return DoctorMapper.toDTO(doctor);
    }

    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return DoctorMapper.toDTO(doctor);
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(DoctorMapper::toDTO)
                .toList();
    }

    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }
}