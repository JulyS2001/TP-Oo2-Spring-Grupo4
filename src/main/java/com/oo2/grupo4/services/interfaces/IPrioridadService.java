package com.oo2.grupo4.services.interfaces;

import java.util.Optional;

import com.oo2.grupo4.entities.Prioridad;

public interface IPrioridadService {
	
	Optional<Prioridad> findById(Integer id);
	Optional<Prioridad> findByTipo(String tipo); 

}
