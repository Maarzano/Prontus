package InoDev.Prontus.Config;

import InoDev.Prontus.Models.*;
import InoDev.Prontus.Repository.*;
import InoDev.Prontus.Utils.Enums.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
            User adminSuper = new User(0, "11111111111", "Admin Super", "adminsuper@example.com", "password123", AdmRole.ADM_SUPER, "31999999999", true, LocalDateTime.now());
            User admin = new User(0, "22222222222", "Admin", "admin@example.com", "password123", AdmRole.ADM, "31988888888", true, LocalDateTime.now());
            User doctor = new User(0, "33333333333", "Doctor", "doctor@example.com", "password123", AdmRole.DOCTOR, "31977777777", true, LocalDateTime.now());
            User receptionist = new User(0, "44444444444", "Receptionist", "receptionist@example.com", "password123", AdmRole.RECEPCIONIST, "31966666666", true, LocalDateTime.now());
            userRepository.saveAll(List.of(adminSuper, admin, doctor, receptionist));
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
            Address address1 = new Address(0, "Rua A", "12345678", "100", "Bairro A", "Cidade A", "SP");
            Address address2 = new Address(0, "Rua B", "87654321", "200", "Bairro B", "Cidade B", "RJ");
            Address address3 = new Address(0, "Rua C", "11223344", "300", "Bairro C", "Cidade C", "MG");
            Address address4 = new Address(0, "Rua D", "44556677", "400", "Bairro D", "Cidade D", "BA");
            addressRepository.saveAll(List.of(address1, address2, address3, address4));
        }
    }

    private void seedPatients() {
        if (patientRepository.count() == 0) {
            List<Address> addresses = addressRepository.findAll();
            Patient patient1 = new Patient(0, "John Doe", "11122233344", Date.valueOf(LocalDate.of(1990, 1, 1)), "31999999999", "john.doe@example.com", addresses.get(0));
            Patient patient2 = new Patient(0, "Jane Doe", "55566677788", Date.valueOf(LocalDate.of(1985, 5, 15)), "31888888888", "jane.doe@example.com", addresses.get(1));
            Patient patient3 = new Patient(0, "Alice Smith", "99988877766", Date.valueOf(LocalDate.of(2000, 3, 10)), "31777777777", "alice.smith@example.com", addresses.get(2));
            patientRepository.saveAll(List.of(patient1, patient2, patient3));
        }
    }

    private void seedDoctors() {
        if (doctorRepository.count() == 0) {
            Specialty specialty = specialtyRepository.findAll().get(0);
            Address address = addressRepository.findAll().get(3);
            User doctorUser = userRepository.findAll().stream().filter(user -> user.getRole() == AdmRole.DOCTOR).findFirst().orElse(null);
            if (doctorUser != null) {
                Doctor doctor1 = new Doctor(0, "123456", doctorUser, specialty, address);
                doctorRepository.save(doctor1);
            }
        }
    }

    private void seedSchedulings() {
        if (schedulingRepository.count() == 0) {
            Patient patient1 = patientRepository.findAll().get(0);
            Patient patient2 = patientRepository.findAll().get(1);
            Patient patient3 = patientRepository.findAll().get(2);
            Doctor doctor = doctorRepository.findAll().get(0);

            Scheduling scheduling1 = new Scheduling(0, patient1, doctor, LocalDateTime.now().plusDays(1), StatusScheduling.SCHEDULED);
            Scheduling scheduling2 = new Scheduling(0, patient2, doctor, LocalDateTime.now().plusDays(2), StatusScheduling.COMPLETED);
            Scheduling scheduling3 = new Scheduling(0, patient3, doctor, LocalDateTime.now().plusDays(3), StatusScheduling.CANCELED);
            Scheduling scheduling4 = new Scheduling(0, patient1, doctor, LocalDateTime.now().plusDays(4), StatusScheduling.SCHEDULED);

            schedulingRepository.saveAll(List.of(scheduling1, scheduling2, scheduling3, scheduling4));
        }
    }

    private void seedMedicalRecords() {
        if (medicalRecordRepository.count() == 0) {
            Scheduling scheduling2 = schedulingRepository.findAll().get(1);
            Scheduling scheduling4 = schedulingRepository.findAll().get(3);

            MedicalRecord medicalRecord1 = new MedicalRecord(0, scheduling2, LocalDateTime.now(), Diagnostics.GRIPE, "Paracetamol", "Descansar por 3 dias");
            MedicalRecord medicalRecord2 = new MedicalRecord(0, scheduling4, LocalDateTime.now(), Diagnostics.DIABETES, "Insulina", "Monitorar glicose diariamente");

            medicalRecordRepository.saveAll(List.of(medicalRecord1, medicalRecord2));
        }
    }

    private void seedAvailableTimes() {
        if (availableTimeRepository.count() == 0) {
            Doctor doctor = doctorRepository.findAll().get(0);
            AvailableTime availableTime1 = new AvailableTime(0, doctor, WeekDay.SEGUNDA, LocalTime.parse("08:00:00"), LocalTime.parse("12:00:00"));
            AvailableTime availableTime2 = new AvailableTime(0, doctor, WeekDay.QUARTA, LocalTime.parse("14:00:00"), LocalTime.parse("18:00:00"));
            availableTimeRepository.saveAll(List.of(availableTime1, availableTime2));
        }
    }
}