package com.oo2.grupo4.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.oo2.grupo4.entities.Persona;

@Repository

public interface IPersonaRepository {
	

    boolean existsByDni (int dni);

    Optional<Persona> findByDni(int dni);

}
