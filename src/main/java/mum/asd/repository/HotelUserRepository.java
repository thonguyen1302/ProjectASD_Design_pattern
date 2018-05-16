package mum.asd.repository;

import mum.asd.domain.HotelUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelUserRepository extends JpaRepository<HotelUser, Integer> {
}
