package mum.asd.repository;

import mum.asd.domain.HotelUser;
import mum.asd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {

	User findByEmail(String email);
}
