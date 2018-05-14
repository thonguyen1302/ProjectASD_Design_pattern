package mum.asd.testFX;

//package com.unibul.repositories;

//import com.unibul.entities.Persona;
import mum.asd.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Skill, Long> {

}
