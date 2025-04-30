package InoDev.Prontus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import InoDev.Prontus.Models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}