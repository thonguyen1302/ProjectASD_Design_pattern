package mum.asd.testFX;

import mum.asd.domain.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository) {

        this.personaRepository = personaRepository;
    }

    @Override
    public Skill add(Skill persona) {
        Skill persistedPersona;
        persistedPersona = personaRepository.save(persona);
        return persistedPersona;
    }
}