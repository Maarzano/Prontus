package InoDev.Prontus.Config;

import InoDev.Prontus.Models.*;
import InoDev.Prontus.Repository.*;
import InoDev.Prontus.Utils.Enums.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SpecialtyRepository specialtyRepository;
    private final AddressRepository addressRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SchedulingRepository schedulingRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final AvailbleTimeRepository availableTimeRepository;

    public DataSeeder(UserRepository userRepository, SpecialtyRepository specialtyRepository, AddressRepository addressRepository,
                      PatientRepository patientRepository, DoctorRepository doctorRepository, SchedulingRepository schedulingRepository,
                      MedicalRecordRepository medicalRecordRepository, AvailbleTimeRepository availableTimeRepository) {
        this.userRepository = userRepository;
        this.specialtyRepository = specialtyRepository;
        this.addressRepository = addressRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.schedulingRepository = schedulingRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.availableTimeRepository = availableTimeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedSpecialties();
        seedAddresses();
        seedPatients();
        seedDoctors();
        seedSchedulings();
        seedMedicalRecords();
        seedAvailableTimes();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            User admin = new User(0, "123.456.789-00", "Admin User", "admin@example.com", "password123", AdmRole.ADM_SUPER, "99999-9999", true, LocalDateTime.now());
            User doctor = new User(0, "987.654.321-00", "Doctor User", "doctor@example.com", "password123", AdmRole.DOCTOR, "88888-8888", true, LocalDateTime.now());
            userRepository.saveAll(List.of(admin, doctor));
        }
    }

    private void seedSpecialties() {
        if (specialtyRepository.count() == 0) {
            Specialty cardiology = new Specialty(0, Specialties.CARDIOLOGIA);
            Specialty dermatology = new Specialty(0, Specialties.DERMATOLOGIA);
            specialtyRepository.saveAll(List.of(cardiology, dermatology));
        }
    }

    private void seedAddresses() {
        if (addressRepository.count() == 0) {
            Address address1 = new Address(0, "Rua A", "12345-678", "100", "Bairro A", "Cidade A", "SP");
            Address address2 = new Address(0, "Rua B", "87654-321", "200", "Bairro B", "Cidade B", "RJ");
            addressRepository.saveAll(List.of(address1, address2));
        }
    }

    private void seedPatients() {
        if (patientRepository.count() == 0) {
            Address address = addressRepository.findAll().get(0);
            Patient patient1 = new Patient(0, "John Doe", "111.222.333-44", Date.valueOf(LocalDate.of(1990, 1, 1)), "99999-9999", "john.doe@example.com", address);
            Patient patient2 = new Patient(0, "Jane Doe", "555.666.777-88", Date.valueOf(LocalDate.of(1985, 5, 15)), "88888-8888", "jane.doe@example.com", address);
            patientRepository.saveAll(List.of(patient1, patient2));
        }
    }

    private void seedDoctors() {
        if (doctorRepository.count() == 0) {
            Specialty specialty = specialtyRepository.findAll().get(0);
            Address address = addressRepository.findAll().get(1);
            User doctorUser = userRepository.findAll().stream().filter(user -> user.getRole() == AdmRole.DOCTOR).findFirst().orElse(null);
            if (doctorUser != null) {
                Doctor doctor1 = new Doctor(0, "123456", doctorUser, specialty, address);
                doctorRepository.save(doctor1);
            }
        }
    }

    private void seedSchedulings() {
        if (schedulingRepository.count() == 0) {
            Patient patient = patientRepository.findAll().get(0);
            Doctor doctor = doctorRepository.findAll().get(0);
            Scheduling scheduling = new Scheduling(0, patient, doctor, LocalDateTime.now().plusDays(1), StatusScheduling.SCHEDULED);
            schedulingRepository.save(scheduling);
        }
    }

    private void seedMedicalRecords() {
        if (medicalRecordRepository.count() == 0) {
            Scheduling scheduling = schedulingRepository.findAll().get(0);
            MedicalRecord medicalRecord = new MedicalRecord(0, scheduling, LocalDateTime.now(), Diagnostics.GRIPE, "Paracetamol", "Descansar por 3 dias");
            medicalRecordRepository.save(medicalRecord);
        }
    }

    private void seedAvailableTimes() {
        if (availableTimeRepository.count() == 0) {
            Doctor doctor = doctorRepository.findAll().get(0);
            AvailableTime availableTime = new AvailableTime(0, doctor, WeekDay.SEGUNDA, java.sql.Time.valueOf("08:00:00"), java.sql.Time.valueOf("12:00:00"));
            availableTimeRepository.save(availableTime);
        }
    }
}