package com.testagenciand.utils;

import com.testagenciand.dto.Persona;
import com.testagenciand.entity.PersonaEntity;

public class Utilidades {


    public static PersonaEntity buildPersonatoEntity(Persona persona){
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setId(persona.getCedula());
        personaEntity.setNombre(persona.getNombre());
        personaEntity.setEmail(persona.getEmail());
        personaEntity.setDescripcion(persona.getDescripcion());
        return personaEntity;
    }

    public static Persona buildEntityToPersona(PersonaEntity personaEntity){
        Persona persona = new Persona();
        persona.setCedula(personaEntity.getId());
        persona.setNombre(personaEntity.getNombre());
        persona.setEmail(personaEntity.getEmail());
        persona.setDescripcion(personaEntity.getDescripcion());
        return persona;
    }


}
