package com.testagenciand.component;

import com.testagenciand.dto.Persona;

import java.util.List;

public interface PersonaService {

    public List<Persona> listarPersona();
    public Persona consultarPersona(Long cedula);
    public Persona guardarPersona(Persona persona);
    public Persona actualizarPersona(Persona persona);
    public Boolean eliminarPersona(Long id);


}
