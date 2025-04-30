package InoDev.Prontus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import InoDev.Prontus.Models.Scheduling;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long>{

}