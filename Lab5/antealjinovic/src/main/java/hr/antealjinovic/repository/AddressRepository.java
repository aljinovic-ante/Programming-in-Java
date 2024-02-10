package hr.antealjinovic.repository;

import hr.antealjinovic.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCityIgnoreCase(String City);

}
