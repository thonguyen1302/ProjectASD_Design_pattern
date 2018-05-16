package mum.asd.repository;

import mum.asd.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, InternalError> {
}
