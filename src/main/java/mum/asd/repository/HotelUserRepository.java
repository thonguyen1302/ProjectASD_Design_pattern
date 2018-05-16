package mum.asd.repository;

import mum.asd.domain.HotelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Integer> {
//    @Query("SELECT distinct h from HotelUser h WHERE h.firstName = :firstName")
//    List<HotelUser> findHotelUserByFirstName(@Param("firstName") String firstName);

//    List<HotelUser>findHotelUserByFirstName(String fistName);

}
