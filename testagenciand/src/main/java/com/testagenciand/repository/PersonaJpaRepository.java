package com.testagenciand.repository;


import com.testagenciand.dto.Persona;
import com.testagenciand.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("personaJpaRepository")
public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, Serializable> {


}

