package InoDev.Prontus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import InoDev.Prontus.Models.Patient;

@Repository
public class PatientRepository extends JpaRepository<Patient, Long>{

}
