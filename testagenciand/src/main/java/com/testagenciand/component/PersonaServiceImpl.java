package com.testagenciand.component;


import com.testagenciand.controller.PersonaController;
import com.testagenciand.dto.Persona;
import com.testagenciand.entity.PersonaEntity;
import com.testagenciand.repository.PersonaJpaRepository;
import com.testagenciand.utils.Utilidades;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("personaService")
public class PersonaServiceImpl implements  PersonaService {

    private static final Log LOGGER = LogFactory.getLog(PersonaServiceImpl.class);

    @Autowired
    @Qualifier("personaJpaRepository")
    private PersonaJpaRepository personaJpaRepository;


    @Override
    public List<Persona> listarPersona() {
        List<Persona> personas = new ArrayList<Persona>();
        List<PersonaEntity> personasEntity = personaJpaRepository.findAll();
        for (PersonaEntity personaEntity: personasEntity ) {
            LOGGER.info("personas tamano1: "+personaEntity.getNombre());
            personas.add(Utilidades.buildEntityToPersona(personaEntity));
        }

        for (Persona persona :personas ) {
            LOGGER.info("personas tamano: "+persona.getNombre());

        }

        return personas;
    }

    @Override
    public Persona consultarPersona(Long cedula) {
        Optional<PersonaEntity> personaEntity = personaJpaRepository.findById(cedula);
        Persona persona = Utilidades.buildEntityToPersona(personaEntity.get());
        return persona;
    }

    @Override
    public Persona guardarPersona(Persona persona) {
        PersonaEntity personaEntity = Utilidades.buildPersonatoEntity(persona);
        personaJpaRepository.save(personaEntity);
        return  Utilidades.buildEntityToPersona(personaEntity);
    }

    @Override
    public Persona actualizarPersona(Persona persona) {
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setId(persona.getCedula());
        personaEntity.setNombre(persona.getNombre());
        personaEntity.setEmail(persona.getEmail());
        personaEntity.setDescripcion(persona.getDescripcion());

        PersonaEntity resultadoEntity = personaJpaRepository.save(personaEntity);
        return Utilidades.buildEntityToPersona(resultadoEntity);
    }

    @Override
    public Boolean eliminarPersona(Long id) {
        Boolean RESULTADO = Boolean.FALSE;
        personaJpaRepository.deleteById(id);
        RESULTADO = Boolean.TRUE;
        return RESULTADO;
    }

}