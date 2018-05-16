package mum.asd.repository;

import mum.asd.domain.HotelUser;
<<<<<<< HEAD
import mum.asd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {

	User findByEmail(String email);
=======
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

>>>>>>> e4c8016e07f18ff9494c64f7ae7941a3ab9eab91
}
