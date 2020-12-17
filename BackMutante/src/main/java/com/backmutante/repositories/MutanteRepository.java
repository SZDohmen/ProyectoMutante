package com.backmutante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backmutante.entities.PersonaEntity;

public interface MutanteRepository extends JpaRepository<PersonaEntity, Long> {

}
