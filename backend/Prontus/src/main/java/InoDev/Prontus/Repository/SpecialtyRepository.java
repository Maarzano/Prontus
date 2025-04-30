package InoDev.Prontus.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import InoDev.Prontus.Models.Specialty;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long>{

}
