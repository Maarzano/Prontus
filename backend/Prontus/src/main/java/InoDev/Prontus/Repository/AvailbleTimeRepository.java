package InoDev.Prontus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import InoDev.Prontus.Models.AvailableTime;

@Repository
public interface AvailbleTimeRepository extends JpaRepository<AvailableTime, Long>{

}
